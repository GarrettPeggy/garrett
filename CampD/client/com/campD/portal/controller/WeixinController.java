/**
 * 
 */
package com.campD.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campD.portal.util.wx.DeveloperSignUtil;

/**
 * @author Garrett
 *
 * 微信服务
 */
@Controller
@RequestMapping("/wx")
public class WeixinController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 微信服务器验证
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
}
