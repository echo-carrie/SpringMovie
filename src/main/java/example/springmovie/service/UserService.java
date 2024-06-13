package example.springmovie.service;

import cn.dev33.satoken.stp.StpUtil;
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

    //判断是否为vip，返回true 或者 false
    public Boolean isVip() {
        //获取缓存中对象
        User user=(User) StpUtil.getSession().get("user");
        return user.getIsVip();
    }

    //设置vip，会在缓存和数据库中进行更新
    public void setVip() {
        //获取局部变量
        User user=(User) StpUtil.getSession().get("user");
        //设置缓存中对象
        user.setIsVip(true);
        //更新缓存对象
        StpUtil.getSession().set("user",user);
        //更新数据库中对象
        userMapper.updateUser(user);
    }
}
