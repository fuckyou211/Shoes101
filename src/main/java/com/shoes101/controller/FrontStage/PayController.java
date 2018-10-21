package com.shoes101.controller.FrontStage;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.service.OrderService;
import com.shoes101.service.ShoesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 	支付控制器
 */
@RestController("/pay")
public class PayController {

	private Logger logger = LoggerFactory.getLogger(PayController.class);
	private static final String APP_ID = "2018102161800193";

	@Autowired
	ShoesOrderService shoesOrderService;

	@PostMapping("/orderId")
	public void doPay(@PathVariable("orderId") String orderId, HttpServletResponse response, HttpServletRequest request){
		logger.info("订单编号为的 {}",orderId);


		// 根据orderiD获取订单信息
		Shoesorder shoesorder = shoesOrderService.getOrderById(Integer.parseInt(orderId));

		doPayForAliPay(shoesorder,response,request);


	}

	/**
	 * 	处理订单
	 * @param shoesorder
	 */
	private void doPayForAliPay(Shoesorder shoesorder, HttpServletResponse response, HttpServletRequest request){
//		logger.info("订单信息：{}", shoesorder);
//		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "JSON", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
//		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
//		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
//		alipayRequest.setBizContent("{" +
//				"    \"out_trade_no\":\"20150320010101001\"," +
//				"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//				"    \"total_amount\":88.88," +
//				"    \"subject\":\"Iphone6 16G\"," +
//				"    \"body\":\"Iphone6 16G\"," +
//				"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//				"    \"extend_params\":{" +
//				"    \"sys_service_provider_id\":\"2088511833207846\"" +
//				"    }"+
//				"  }");//填充业务参数
//		String form="";
//		try {
//			form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}
//		httpResponse.setContentType("text/html;charset=" + CHARSET);
//		httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//		httpResponse.getWriter().flush();
//		httpResponse.getWriter().close();
	}

}
