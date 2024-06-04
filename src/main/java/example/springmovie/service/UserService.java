package example.springmovie.service;

import example.springmovie.entity.User;
import example.springmovie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void registerUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }
}
