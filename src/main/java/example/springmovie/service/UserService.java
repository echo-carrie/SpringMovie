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

//获取用户信息
    public Boolean isVipUser(Long userId) {
        User user = userMapper.selectUserById(userId);
        return user != null && user.isVip(); // 如果用户对象不为空且isVip为true，则返回 true，否则返回 false
    }
    public void updateUserVipStatus(Long userId) {
        User user = getUserById(userId); // 获取用户对象
        if (user != null) {
            user.setIsVip(true); // 设置VIP状态为true
            userMapper.updateUser(user); // 调用 UserMapper 更新用户状态
        }
    }

    // 你可能还需要一个方法来更新用户信息
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
