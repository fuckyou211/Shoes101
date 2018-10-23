package com.shoes101.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


public class AlipayConfig {
	private String app_id = "2018102161800193";
	private String app_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC3LvBthKHrQJc7+Tz+7/paaiEmnvmBrTR/g8mTNFVuBqCbK1/PRpPDCl69pGc1I7icC0qvjmATnR98PPi602i81RfzQf6uIVExymvtIMFw96EfMRyrNRPVicdt3KEDLfldIZ+JknN+TTCJgg6FwXFqY/8USso6qXedSYJg/EYwWODHfrfnrqg2S2piicdhYV0SQl9AO9iNTwR/zIurySY71gE/NB37Vy8Db1v5WbajKffOt/6GuAcUX2iHB9z3mERJHpREDOItej1CbQI91XqTOeDFCm4fzerXJysSGGfiAPr0bInm/gdFeXaciScs7es2uhHYrMCEO6iECLW4/hPHAgMBAAECggEANLRkqJ4ImE81NCQ50H/rYCg48Yu77fuPp0CbtqbQDaAKhZqtLk34JZ3ONHIg38EvVHZLPseQDRSgAnboeZhqAiqtgWfzhtY/Ljw3YTqTbZVR3k+bGAdldYL2xj08dlW78XfDFynz4NsEpAuvcfx+TUYIFiaOmOI5cB++S3lKGI5802OIy9dfOAi3XwQ2hXABX59CEHzrOZm+jr2wWRWqvIVzlSzksDwEt4wqSOjVVGJGO/CIY/OHZpEm6IwjzXvfbfGY5o0vCm2PQwYkr+dB516xe2NAC3xU+EhSNYX5Uv7kYIsJ8872K+krNlk4RDf+0/L+XD9LgBQRkf6Wb9DlAQKBgQDrREsQnxjqpYTtZtS0VNGtmDTKUwNek//DKXj1BXRXZHMSq7SL8qyn0Sh+OyK0TDunr65ejVrOTYHBPLwKjCmoMUthFWQN2Go4PANyGFxixQzyFcUtCeDcCriDjM5xW0lQLcCNKeXgTax+fCKwid4IpihwggrF2c8L/y/TmHQUMQKBgQDHU5/iKPnyyzW3NAdT3k/yK4R9XHQpuq5l0xCWO1/xdYYe+1NW/NFYHmOD2fo1wEphhgTlURHoYACNatatr8D64yJJHrgtLsS6ZnYOtOWSjmlrBJNoo0e+QQKo2nBRo5sAeqahoNe30XgnmMqfgpVUHrfUnSd3r/goAP1DP5yBdwKBgQCZDlj3e0muffg4qkl++B9hHEBb0B3FDeu6f+SdIutJX6QtZV+UHFCO7Ne3J6mwOlglA3ohMTz+MhZULLr48a64KTms80mgaEbSRtjHBWo1J5UAeYFJh7+t55uamaREwuqzKtC2BafzgRTTRNccmtTEM7TjXPa9OLImT+JBelEqQQKBgQCExVkshmrnpSBkpg1zehP6la4s6tadrTHZtZvI1kf0SHpMTNCYktLHRYa4HwwhnbJt/51nOncZOEll6ZKSIw4CkCUYW5QG1K5KE1H0Pd7lnmLt3CBshMaXHiqtWsOSPc5TpV81LjRCxw6oIYhmc88QN13wC7ZsbRR5YR9SGh8fxQKBgQCDYpQ2uGRIaynAvVUrdNuqZShKcJgYpf/hT9XmFAUbz2BOm9BqgVKnOi7tKx3d8stJ44h8N/cRxUckd25PhQw/iWDCjVAY4f9YI6gUeK/9q/QWbUAyPf3LSzpwpL6ZyqMX8FWX8KsmMR9vuPC3oQ27BSSaLB4vJ91fXmt/EaDdag==";
	private String app_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAty7wbYSh60CXO/k8/u/6WmohJp75ga00f4PJkzRVbgagmytfz0aTwwpevaRnNSO4nAtKr45gE50ffDz4utNovNUX80H+riFRMcpr7SDBcPehHzEcqzUT1YnHbdyhAy35XSGfiZJzfk0wiYIOhcFxamP/FErKOql3nUmCYPxGMFjgx363566oNktqYonHYWFdEkJfQDvYjU8Ef8yLq8kmO9YBPzQd+1cvA29b+Vm2oyn3zrf+hrgHFF9ohwfc95hESR6URAziLXo9Qm0CPdV6kzngxQpuH83q1ycrEhhn4gD69GyJ5v4HRXl2nIknLO3rNroR2KzAhDuohAi1uP4TxwIDAQAB";
	private String sign_type = "RSA2";
	private String charset = "UTF-8";
	private String format = "JSON";
	private String return_url= "http://123.207.109.158:8888/alipay/alipayReturnNotice"; //  # 同步通知
	private String notify_url= "http://123.207.109.158:8888/alipay/alipayNotifyNotice"; //  # 异步通知

	public String getApp_id() {
		return app_id;
	}

	public String getApp_private_key() {
		return app_private_key;
	}

	public String getApp_public_key() {
		return app_public_key;
	}

	public String getCharset() {
		return charset;
	}

	public String getFormat() {
		return format;
	}

	public String getSign_type() {
		return sign_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}
}
