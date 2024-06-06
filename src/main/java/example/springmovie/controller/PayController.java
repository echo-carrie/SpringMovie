package example.springmovie.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import example.springmovie.config.AlipayConfig;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("alipay")
public class PayController {
    @RequestMapping("pay/{orderId}/{cost}")
    public void pay(@PathVariable String orderId, @PathVariable String cost, HttpServletResponse response){
        try {
            AlipayClient alipayClient = AlipayConfig.getAlipayClient();
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            AlipayTradePayModel model = new AlipayTradePayModel();
            model.setOutTradeNo(orderId);
            model.setTotalAmount(cost);
            model.setSubject("书籍订单");
            model.setBody("这是一本书籍的消费订单");
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            alipayRequest.setBizModel(model);
            String url = "http://localhost:8080/alipay/success?orderId="+orderId+"";
            System.out.println(url);
            alipayRequest.setReturnUrl(url);
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("success")
    public void success(@RequestParam("orderId") String orderId, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("支付成功，订单号为："+orderId);
    }
}
