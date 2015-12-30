/**
 * 
 */
package com.campD.portal.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

/**
 * @author Garrett
 * 微信公共服务类
 */
@Service("wxService")
public class WxService extends JsonClientService{

	public static Logger logger = Logger.getLogger(WxService.class);
	
	public static String APPID = SystemMessage.getString("wx_AppID");
	public static String APPSECRET = SystemMessage.getString("wx_AppSecret");
	
	/**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public String getAccess_token() {
        //凭证获取(GET)
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = token_url.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        // 发起GET请求获取凭证
        @SuppressWarnings("rawtypes")
		Map jsonMap = postForObject(requestUrl, null, Map.class, false);
        String access_token = null;
        if (null != jsonMap) {
            try {
                access_token = (String) jsonMap.get("access_token");
            } catch (Exception e) {
                // 获取token失败
            	logger.error("获取token失败 errcode:{"+jsonMap.get("errcode")+"} errmsg:{"+jsonMap.get("errmsg")+"}");
            }
        }
        return access_token;
    }
    
    /**
     * 调用微信JS接口的临时票据
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    public String getJsApiTicket(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
        // 发起GET请求获取凭证
        @SuppressWarnings("rawtypes")
		Map jsonMap = postForObject(requestUrl, null, Map.class, false);
        String ticket = null;
        if (null != jsonMap) {
            try {
                ticket = (String) jsonMap.get("ticket");
            } catch (Exception e) {
                // 获取token失败
            	logger.error("获取ticket失败 errcode:{"+jsonMap.get("errcode")+"} errmsg:{"+jsonMap.get("errmsg")+"}");
            }
        }
        return ticket;
    }
}
