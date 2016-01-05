/**
 * 
 */
package com.campD.portal.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.campD.portal.util.SystemMessage;

/**
 * @author Garrett
 * 微信公共服务类
 */
@Service("wxService")
public class WxService{

	public static Logger logger = Logger.getLogger(WxService.class);
	
	public static String APPID = SystemMessage.getString("wx_AppID");
	public static String APPSECRET = SystemMessage.getString("wx_AppSecret");
	
	public static Random random = new Random();
	
	@Autowired
	private RestTemplate restTemplate;
	
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
		Map jsonMap = restTemplate.getForObject(requestUrl, Map.class);//postForObject(requestUrl, null, Map.class, false);
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
		Map jsonMap = restTemplate.getForObject(requestUrl, Map.class);//postForObject(requestUrl, null, Map.class, false);
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
    
    /**
     * 获取公众号永久二维码
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getQrLimitCode(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        String requestUrl = url.replace("TOKEN", access_token);
        // 构造二维码参数：{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}
        Map paramMap = new HashMap();
        Map sceneIdMap = new HashMap();
        sceneIdMap.put("scene_id", random.nextInt(10000));
        Map sceneMap = new HashMap();
        sceneMap.put("scene", sceneIdMap);
        paramMap.put("action_name", "QR_LIMIT_SCENE");
        paramMap.put("action_info", sceneMap);
        logger.info("微信二位码生成参数 paramMap -> "+paramMap);
        // 发起POST请求获取二维码凭证
		Map jsonMap = restTemplate.postForObject(requestUrl, paramMap, Map.class, paramMap);
		logger.info("微信二位码返回参数 jsonMap -> "+jsonMap);
        String ticket = null;
        if (null != jsonMap) {
            try {
                ticket = (String) jsonMap.get("ticket");
            } catch (Exception e) {// 获取token失败
            	logger.error("获取ticket失败 errcode:{"+jsonMap.get("errcode")+"} errmsg:{"+jsonMap.get("errmsg")+"}");
            }
        }

        String returnUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        returnUrl = returnUrl.replace("TICKET", ticket);
        return returnUrl;
    }
}
