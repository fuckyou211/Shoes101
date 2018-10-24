package com.shoes101.rabbitmq;


import com.shoes101.pojo.User;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.RushOrderVo;

public class MiaoshaMessage {
	private User user;
	private RushOrderVo rushOrderVo;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RushOrderVo getRushOrderVo() {
		return rushOrderVo;
	}

	public void setRushOrderVo(RushOrderVo rushOrderVo) {
		this.rushOrderVo = rushOrderVo;
	}

	public MiaoshaMessage() {
	}
}
