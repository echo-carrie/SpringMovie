package example.springmovie.security.interceptors;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            //只有登录才可以访问网站
            SaRouter
                    .match("/**")                               // 拦截的 path 列表，可以写多个 */
                    .notMatch("/user/login","/user/register")   // 排除掉的 path 列表，可以写多个
                .check(r -> StpUtil.checkLogin());                        // 要执行的校验动作，可以写完整的 lambda 表达式

        })).addPathPatterns("/**");
    }
}

