package example.springmovie.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import example.springmovie.dto.LoginRequest;
import example.springmovie.dto.RegisterRequest;
import example.springmovie.entity.User;
import example.springmovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private UserService userService;

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

        @PostMapping("/login")
        public String login(@RequestBody LoginRequest loginRequest) {
            User user = userService.findUserByUsername(loginRequest.getUsername());
            if (user != null) {
                // 使用Sa-Token的BCrypt方法验证密码
                boolean passwordMatch = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
                if (passwordMatch) {
                    // 登录成功，使用Sa-Token生成Token
                    StpUtil.login(user.getId());

                    return "Login successful";
                } else {
                    return "Invalid password";
                }
            } else {
                return "User not found";
            }
        }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        // 使用Sa-Token的BCrypt方法生成盐和密码散列值
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(registerRequest.getPassword(), salt);

        // 查找是否存在用户，如果存在就返回已经存在
        User existingUser = userService.findUserByUsername(registerRequest.getUsername());
        if (existingUser != null) {
            return "User already exists";
        }

        // 将散列密码存储到数据库中
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setType(registerRequest.getType());
        // 存储用户到数据库的逻辑
        userService.registerUser(newUser);
        return "User registered successfully";
    }

    @GetMapping("/check")
    public String checkAuth() {
        if (cn.dev33.satoken.stp.StpUtil.isLogin()) {
            return "User is authenticated";
        } else {
            return "User is not authenticated";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        cn.dev33.satoken.stp.StpUtil.logout();
        return "Logout successful";
    }
}