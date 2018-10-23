package com.shoes101.rabbitmq;

import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.RushbuyMapper;
import com.shoes101.pojo.Rushsku;
import com.shoes101.pojo.User;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.RushKey;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.OrderService;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.RushOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

	private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		
	@Autowired
	RedisService redisService;

	@Autowired
	RushbuyMapper rushbuyMapper;


	@Autowired
	private OrderService orderService;

		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receive(String message) {

			log.info("receive message:"+message);
			MiaoshaMessage mm  = RedisService.stringToBean(message, MiaoshaMessage.class);
			User user = mm.getUser();
			RushOrderVo rushOrderVo=mm.getRushOrderVo();

			Rushsku rushsku=rushbuyMapper.getRushbuyByrushskuid(rushOrderVo.getRushbuyid(),rushOrderVo.getShoessku());
			if(rushsku.getQuantity()<=0||rushsku.getQuantity()<rushOrderVo.getQuantity())
			{
				redisService.set(RushKey.orderState,user.getUserid()+":"+rushOrderVo.getRushbuyid(),-1);
				throw new GlobalException(CodeMsg.MIAOSHA_NULLGOOD);
			}
			int out=rushbuyMapper.updateRushbuyByrushskuid(rushOrderVo.getRushbuyid(),rushOrderVo.getShoessku(),rushOrderVo.getQuantity());

			if(out==0)
			{
				redisService.set(RushKey.orderState,user.getUserid()+":"+rushOrderVo.getRushbuyid(),-1);
				throw new GlobalException(CodeMsg.MIAOSHA_NULLGOOD);
			}

			OrderVo orderVo=new OrderVo();
			orderVo.setContactName(rushOrderVo.getContactName());
			orderVo.setContactPhone(rushOrderVo.getContactPhone());
			orderVo.setReceiptaddress(rushOrderVo.getReceiptaddress());
			orderVo.setRemark(rushOrderVo.getRemark());
			orderVo.setSkuidandqty(rushOrderVo.getSkuidandqty());
			orderVo.setUserid(rushOrderVo.getUserid());
			orderVo.setToken(rushOrderVo.getToken());
			Integer orderid=orderService.add(orderVo);
			redisService.set(RushKey.orderId,user.getUserid()+":"+rushOrderVo.getRushbuyid(),orderid);
			redisService.set(RushKey.orderState,user.getUserid()+":"+rushOrderVo.getRushbuyid(),1);

		}

		@RabbitListener(queues=MQConfig.QUEUE)
		public void receive2(String message) {
			log.info("receive message:"+message);
		}
//		
//		@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
//		public void receiveTopic1(String message) {
//			log.info(" topic  queue1 message:"+message);
//		}
//		
//		@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
//		public void receiveTopic2(String message) {
//			log.info(" topic  queue2 message:"+message);
//		}
//		
//		@RabbitListener(queues=MQConfig.HEADER_QUEUE)
//		public void receiveHeaderQueue(byte[] message) {
//			log.info(" header  queue message:"+new String(message));
//		}
//		
		
}
