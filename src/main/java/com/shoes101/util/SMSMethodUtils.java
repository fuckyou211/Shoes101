package com.shoes101.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.shoes101.exception.GlobalException;
import com.shoes101.result.CodeMsg;
import com.shoes101.vo.SMSCodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SMSMethodUtils {
    private  final Logger logger= LoggerFactory.getLogger(SMSMethodUtils.class) ;

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private SMSCodeVo sMSCodeVo;

    public void loginCode(String mobile,String code) throws ClientException {
        SendSmsResponse sendSmsResponse=smsUtil.sendVerificationCode(mobile,sMSCodeVo.getSignName(),sMSCodeVo.getLoginCode(),code);
        if(!sendSmsResponse.getCode().equals("OK"))
        {
            logger.error(JSONObject.toJSONString(sendSmsResponse));
            throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
        }
    }

}
