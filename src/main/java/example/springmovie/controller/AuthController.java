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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private UserService userService;
//    GET http://localhost:8080/user/role
//{
//    "username":"jc",
//        "password":"123"
//}
    @GetMapping("/role")
    public String Role(){
        List<String> roleList= StpUtil.getRoleList();
        // 将角色列表转换为字符串，例如使用逗号分隔
        String roles = String.join(", ", roleList);

        // 构造响应消息
        String message = "当前用户的角色有: " + roles;

        // 返回响应消息
        return message;
    }
//    GET http://localhost:8080/user/permission
//{
//    "username":"jc",
//        "password":"123"
//}
    @GetMapping("/permission")
    public String Permission(){
        List<String> permissionList= StpUtil.getPermissionList();
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
        User user = userService.findUserByUsername(loginRequest.getUsername());
        if (user != null) {
            // 使用 BCrypt 验证密码
            boolean passwordMatch = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
            if (passwordMatch) {
                // 登录成功，使用 Sa-Token 生成 Token
                StpUtil.login(user.getId());
                // 获取accountsession，将用户信息添加到session中
                // account session的含义是同一个账户使用同一个session
                // 这样可以在任何地方使用到这个session
                // 在登录时缓存 user 对象
                StpUtil.getSession().set("user", user);

                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
// POST http://localhost:8080/user/register
//    {
//        "username":"jc",
//            "password":"123",
//            "isVip":"VIP"
//    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        // 使用 BCrypt 生成盐和密码散列值
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(registerRequest.getPassword(), salt);

        // 检查是否存在用户，如果存在就返回已经存在
        User existingUser = userService.findUserByUsername(registerRequest.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(409).body("User already exists");
        }

        // 创建并存储新的用户
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(registerRequest.getEmail());
        newUser.setIsVip(registerRequest.getIsVip());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        userService.registerUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    // POST http://localhost:8080/user/check
    @GetMapping("/check")
    public String checkAuth() {
        if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
            return "User is authenticated";
        } else {
            return "User is not authenticated";
        }
    }

    // GET   http://localhost:8080/user/is_vip
    @GetMapping("/is_vip")
    public boolean is_vip(){
//        Session 是会话中专业的数据缓存组件，通过 Session 我们可以很方便的缓存一些高频读写数据
        // 然后我们就可以在任意处使用这个 user 对象
        return userService.isVip();
    }
//    http://localhost:8080/user/set_vip
    @GetMapping("set_vip")
    public void set_vip(){
        userService.setVip();
    }

    // POST http://localhost:8080/user/logout
    @PostMapping("/logout")
    public String logout() {
        cn.dev33.satoken.stp.StpUtil.logout();
        return "Logout successful";
    }
}