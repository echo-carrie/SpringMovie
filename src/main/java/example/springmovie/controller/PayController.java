package example.springmovie.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import example.springmovie.config.AlipayConfig;
import example.springmovie.entity.User;
import example.springmovie.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("alipay")
public class PayController {
    @Autowired
    private UserService userService;

    private AlipayClient alipayClient = AlipayConfig.getAlipayClient();

    // 使用时间戳生成订单ID
    private String generateOrderId() {
        return String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
    }


    // 支付请求

    @GetMapping("/pay")
    public void pay(@RequestParam(value = "cost", defaultValue = "15") String cost, HttpServletResponse response) {
        // 使用 generateOrderId 方法生成订单ID
        String orderId = generateOrderId();

        try {
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            AlipayTradePayModel model = new AlipayTradePayModel();
            model.setOutTradeNo(orderId); // 使用生成的 orderId
            model.setTotalAmount(cost); // 使用请求参数 cost，带有默认值
            model.setSubject("会员订单");
            model.setBody("这是一笔会员的消费订单");
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            alipayRequest.setBizModel(model);

            // 支付成功后的回调URL
            String url = "http://localhost:8080/alipay/success?orderId=" + orderId;
            alipayRequest.setReturnUrl(url);

            // 执行支付请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(result);
        } catch (Exception e) {
            try {
                response.getWriter().println("支付失败：" + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 支付成功回调
    @RequestMapping("success")
    public ResponseEntity<?> success(@RequestParam("orderId") String orderId, HttpServletResponse response) throws IOException {
        try {
            // 用户未登录
            if (!cn.dev33.satoken.stp.StpUtil.isLogin()) {
                return ResponseEntity.ok("用户未登陆");
            }
            // 用户已登录
            else {
                // 用户已经是VIP
                if (userService.isVip()){
                    return ResponseEntity.ok("用户已经是VIP");
                }
                // 用户不是VIP
                else {
                    userService.setVip();
                    return ResponseEntity.ok("支付成功，订单号为：" + orderId + "，用户已升级为VIP。");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "VIP注册检查失败：" + e.getMessage(),
                    e // 将原始异常作为响应的一部分，有助于调试
            );
        }

    }
}