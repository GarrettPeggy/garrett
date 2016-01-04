/**
 * 
 */
package com.campD.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campD.portal.service.WxService;
import com.campD.portal.util.wx.DeveloperSignUtil;
import com.campD.portal.util.wx.JSSDKSignUtil;

/**
 * @author Garrett
 *
 * 微信服务
 */
@Controller
@RequestMapping("/wx")
public class WeixinController extends BaseController {

	// 微信接口签名参数
	public static String token = null;
    public static String time = null;
    public static String jsapi_ticket = null;

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private WxService wxService;
	
	/**
	 * 微信服务器开发者验证
	 * @throws IOException 
	 */
	@RequestMapping("/validateServer.do")
	public void validateServer(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		PrintWriter writer = response.getWriter();
		
        String signature = request.getParameter("signature");  // 微信加密签名  
        String timestamp = request.getParameter("timestamp");  // 时间戳  
        String nonce = request.getParameter("nonce");  // 随机数  
        String echostr = request.getParameter("echostr");  // 随机字符串  
        logger.info("signature->"+signature);
        logger.info("timestamp->"+timestamp);
        logger.info("nonce->"+nonce);
        logger.info("echostr->"+echostr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (DeveloperSignUtil.checkSignature(signature, timestamp, nonce)) {
            logger.info("return echostr->"+echostr);  
            writer.print(echostr);   
        } else{
        	writer.print("error");
        }
        
        writer.flush();
        writer.close();
	}
	
	/**
	 * 测试微信JSSDK签名接口
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getSignParam.do")
	public String getSignParam(HttpServletResponse response, HttpServletRequest request) {
		
		//获取微信接口调用凭证（签名参数） 
		Map signParam = getSignParam(request);
		logger.info("signParam->"+signParam);
		request.setAttribute("signParam", signParam);
	    
		return "custom/aosika_test";
	}

    /**
     * 
     * @param appId   公账号appId
     * @param appSecret
     * @param url    当前网页的URL，不包含#及其后面部分
     * @return
     */
    private Map<String, String> getSignParam(HttpServletRequest request){
    	
        if(token == null){
            token = wxService.getAccess_token();
            jsapi_ticket = wxService.getJsApiTicket(token);
            time = JSSDKSignUtil.getTime();
        }else{
            if(!time.substring(0, 13).equals(JSSDKSignUtil.getTime().substring(0, 13))){ //每小时刷新一次，每次请求token的有效期是两个小时
                token = null;
                token = wxService.getAccess_token();
                jsapi_ticket = wxService.getJsApiTicket(token);
                time = JSSDKSignUtil.getTime();
            }
        }
        
        String url = JSSDKSignUtil.getUrl(request);// 根据当前请求动态获取URL
        Map<String, String> params = JSSDKSignUtil.sign(jsapi_ticket, url);
        params.put("appid", WxService.APPID);
        
        return params;
    }
}
