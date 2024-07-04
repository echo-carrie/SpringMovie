package example.springmovie.controller;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.dev33.satoken.stp.StpUtil;
import example.springmovie.dto.LoginRequest;
import example.springmovie.dto.RegisterRequest;
import example.springmovie.entity.User;
import example.springmovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private UserService userService;

    //    GET http://localhost:8080/user/permission
//{
//    "username":"jc",
//        "password":"123"
//}
    @GetMapping("/permission")
    public String Permission() {

        List<String> permissionList = StpUtil.getPermissionList();
        // 将角色列表转换为字符串，例如使用逗号分隔
        String permissions = String.join(", ", permissionList);

        // 构造响应消息
        String message = "当前用户的角色有: " + permissionList;

        // 返回响应消息
        return message;
    }

    //    POST http://localhost:8080/user/login
//{
//    "username":"jc",
//        "password":"123"
//}
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            //用户已登录
            if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
                // 用户已登录
                return ResponseEntity.ok("用户已登录");
            }
            //用户未登录
            else {
                User user = userService.findUserByUsername(loginRequest.getUsername());
                if (user == null){
                    //数据库中不存在该用户
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在");
                }
                //数据库中存在该用户
                else{
                    // 使用 BCrypt 验证密码
                    boolean passwordMatch = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
                    //密码匹配
                    if (passwordMatch) {
                        // 登录成功，使用 Sa-Token 生成 Token
                        StpUtil.login(user.getId());
                        // 在登录时缓存 user 对象
                        StpUtil.getSession().set("user", user);

                        return ResponseEntity.ok("登录成功");
                    }
                    //密码匹配
                    else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
                    }
                }
            }
        } catch (Exception e) {
            // 记录异常信息，实际开发中可以使用日志框架
            // log.error("登录时发生异常", e);

            // 根据异常类型或业务需求返回不同的错误信息和HTTP状态码
            // 这里以500 Internal Server Error为例，表示服务器内部错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("登录时发生错误：" + e.getMessage());
        }
    }

    // POST http://localhost:8080/user/register
//{
//    "username":"jc1",
//        "password":"123",
//        "email":"12345@gmail.com",
//        "isVip":true
//}
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // 使用 BCrypt 生成盐和密码散列值
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(registerRequest.getPassword(), salt);

            // 检查是否存在用户，如果存在就返回用户名已存在
            User existingUser = userService.findUserByUsername(registerRequest.getUsername());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("用户名已存在");
            }
            else {
                // 创建并存储新的用户
                User newUser = new User();
                newUser.setUsername(registerRequest.getUsername());
                newUser.setPassword(hashedPassword);
                newUser.setEmail(registerRequest.getEmail());
                newUser.setIsVip(registerRequest.getIsVip());
                newUser.setCreatedAt(LocalDateTime.now());
                newUser.setUpdatedAt(LocalDateTime.now());
                userService.registerUser(newUser);

                return ResponseEntity.ok("用户注册成功");
            }
        }catch (Exception e) {
            // 记录其他异常信息
            // log.error("注册时发生异常", e);

            // 返回一个通用错误信息和500状态码
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("注册时发生错误：" + e.getMessage());
        }
    }

    // GET http://localhost:8080/user/check
    @GetMapping("/check")
    public ResponseEntity<String> checkAuth() {
        try {
            if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
                // 用户已登录
                return ResponseEntity.ok("用户已登录");
            } else {
                // 用户未登录
                return ResponseEntity.ok("用户未登陆");
            }
        } catch (Exception e) {
            // 日志记录异常信息，例如使用日志框架
            // log.error("在执行认证检查时发生异常", e);

            // 抛出一个ResponseStatusException来表示服务器内部错误
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "登录检查失败：" + e.getMessage(),
                    e // 将原始异常作为响应的一部分，有助于调试
            );
        }
    }

// 其他必要的导入

    @GetMapping("/is_vip")
    public ResponseEntity<?> isVip() {
        if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
            // 假设 userService.isVip() 返回一个布尔值
            boolean isVip = userService.isVip();
            if (isVip) {
                return ResponseEntity.ok(true); // 用户是VIP
            } else {
                return ResponseEntity.ok(false); // 用户不是VIP
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未登录，请先登录！"); // 用户未认证
        }
    }

    //    http://localhost:8080/user/set_vip
    @PostMapping("set_vip")
    public ResponseEntity<?> set_vip() {
        try {
            if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
                userService.setVip();
                return ResponseEntity.ok("VIP设置成功");
            } else {
                // 用户未登录，返回401 Unauthorized状态码
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("用户未登录，请先登录！");
            }
        } catch (Exception e) {
            // 记录异常信息，实际开发中可以使用日志框架
            // log.error("设置VIP时发生异常", e);

            // 根据异常类型或业务需求返回不同的错误信息和HTTP状态码
            // 这里以500 Internal Server Error为例，表示服务器内部错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("设置VIP时发生错误：" + e.getMessage());
        }
    }

    // POST http://localhost:8080/user/logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
                // 如果用户已登录，执行登出操作
                cn.dev33.satoken.stp.StpUtil.logout();
                return ResponseEntity.ok("登退成功！");
            } else {
                // 如果用户未登录，返回相应的信息
                return ResponseEntity.ok("用户未登录，无法登退！");
            }
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("登出过程中发生错误：" + e.getMessage());
        }
    }
}