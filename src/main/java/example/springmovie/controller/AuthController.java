package example.springmovie.controller;

import example.springmovie.entity.User;
import example.springmovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/view")
        public String view(){
        return "view";
        }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 登录逻辑，使用Sa-Token生成Token
            cn.dev33.satoken.stp.StpUtil.login(user.getId());
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }


    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String type) {
        User existingUser = userService.findUserByUsername(username);
        if (existingUser != null) {
            return "User already exists";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // 在实际应用中应该加密存储
        newUser.setType(type);
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