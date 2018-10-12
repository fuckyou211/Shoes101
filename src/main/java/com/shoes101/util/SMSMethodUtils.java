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

import java.util.HashMap;
import java.util.Map;


@Service
public class SMSMethodUtils {
    private  final Logger logger= LoggerFactory.getLogger(SMSMethodUtils.class) ;

    private Map<String,String> map=new HashMap<>();

    public SMSMethodUtils() {
        map.put("isv.OUT_OF_SERVICE","手机已停机");
        map.put("isv.ACCOUNT_NOT_EXISTS","账户不存在");
        map.put("isv.MOBILE_NUMBER_ILLEGAL","请输入正确手机号");
        map.put("isv.MOBILE_COUNT_OVER_LIMIT","手机号码数量超过限制");
        map.put("isv.INVALID_JSON_PARAM","请输入正确手机号");
        map.put("isv.PARAM_LENGTH_LIMIT","请输入正确手机号");
    }

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private SMSCodeVo sMSCodeVo;

    public void loginCode(String mobile,String code) throws ClientException {
        SendSmsResponse sendSmsResponse=smsUtil.sendVerificationCode(mobile,sMSCodeVo.getSignName(),sMSCodeVo.getLoginCode(),code);
        if(!sendSmsResponse.getCode().equals("OK"))
        {
            String Sendcode=sendSmsResponse.getCode();
            String mssage=map.get(Sendcode);
            logger.error(JSONObject.toJSONString(sendSmsResponse));
            if(mssage!=null)
            {
                throw new GlobalException(new CodeMsg(500220,mssage));
            }
            else
            {
                throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
            }
        }
    }

    public void registerCode(String mobile,String code) throws ClientException {
        SendSmsResponse sendSmsResponse=smsUtil.sendVerificationCode(mobile,sMSCodeVo.getSignName(),sMSCodeVo.getUserRegistration(),code);
        if(!sendSmsResponse.getCode().equals("OK"))
        {
            String Sendcode=sendSmsResponse.getCode();
            String mssage=map.get(Sendcode);
            logger.error(JSONObject.toJSONString(sendSmsResponse));
            if(mssage!=null)
            {
                throw new GlobalException(new CodeMsg(500219,mssage));
            }
            else
            {
                throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
            }
        }
    }

    public void resetPasswordCode(String mobile,String code) throws ClientException {
        SendSmsResponse sendSmsResponse=smsUtil.sendVerificationCode(mobile,sMSCodeVo.getSignName(),sMSCodeVo.getResetPassword(),code);
        if(!sendSmsResponse.getCode().equals("OK"))
        {
            String Sendcode=sendSmsResponse.getCode();
            String mssage=map.get(Sendcode);
            logger.error(JSONObject.toJSONString(sendSmsResponse));
            if(mssage!=null)
            {
                throw new GlobalException(new CodeMsg(500221,mssage));
            }
            else
            {
                throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
            }
        }
    }

}
