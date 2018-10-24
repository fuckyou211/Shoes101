package com.shoes101.controller.FrontStage;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.shoes101.config.AlipayConfig;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.service.OrderService;
import com.shoes101.service.ShoesOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 	支付控制器
 */
@RestController
@RequestMapping("/pay")
public class PayController {
	private Logger logger = LoggerFactory.getLogger(PayController.class);

	@Autowired
	ShoesOrderService shoesOrderService;

	@PostMapping(value = "/{orderId}")
	@ResponseBody
	public String doPay(@PathVariable("orderId") String orderId, HttpServletResponse response, HttpServletRequest request){
		logger.info("订单编号为的 {}",orderId);

		// 根据orderiD获取订单信息
		Shoesorder shoesorder = shoesOrderService.getOrderById(Integer.parseInt(orderId));

		return shoesorder.getTotalprice()+"";	// 返回总价格

		//return doPayForAliPay(shoesorder,response, request);
	}

	/**
	 * 	处理订单
	 * @param shoesorder
	 */
	private String doPayForAliPay(Shoesorder shoesorder,HttpServletResponse response, HttpServletRequest request){
		logger.info("订单信息：{}", shoesorder);
		AlipayConfig alipayConfig = new AlipayConfig();
		logger.info("私钥-->{}",alipayConfig.getApp_private_key().substring(0,20));
		AlipayClient alipayClient = new DefaultAlipayClient(
									"https://openapi.alipay.com/gateway.do",
											alipayConfig.getApp_id(),
											alipayConfig.getApp_private_key(),
											alipayConfig.getFormat(),
											alipayConfig.getCharset(),
											alipayConfig.getApp_public_key(),
											alipayConfig.getSign_type());
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
		alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = Integer.toString(shoesorder.getOrderid());
		//付款金额，必填
		String total_amount = (shoesorder.getTotalprice()/100)+"";
		//订单名称，必填
		String subject = shoesorder.getUserid()+" buy "+ shoesorder.getOrderid();

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		String timeout_express = "1c";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			logger.error("支付失败{}",e);
		}
		return result;
	}

}
