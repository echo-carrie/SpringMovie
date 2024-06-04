package example.springmovie.security;

import cn.dev33.satoken.stp.StpInterface;
import example.springmovie.entity.User;
import example.springmovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    // 由于Java 17不支持接口中的字段注入，所以UserService的注入需要放在类级别
    @Autowired
    private UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissions = new ArrayList<>();
        User user = userService.getUserById(Long.parseLong(loginId.toString()));

        if (user != null) {
            if (user.getIsVip() != null && user.getIsVip()) {
                permissions.add("VIP");
                // 其他VIP权限
            } else {
                permissions.add("NON-VIP");
                // 其他非VIP权限
            }
        }

        return permissions;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roles = new ArrayList<>();
        User user = userService.getUserById(Long.parseLong(loginId.toString()));

        if (user != null) {
            if (user.getIsVip() != null && user.getIsVip()) {
                roles.add("VIP");
            } else {
                roles.add("NON-VIP");
            }
        }

        return roles;
    }
}