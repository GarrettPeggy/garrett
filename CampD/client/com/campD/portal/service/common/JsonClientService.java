/**
 * 
 */
package com.campD.portal.service.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.campD.portal.common.PageInfo;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.util.JsonHelper;

/**
 * @author Administrator
 *
 */
public abstract class JsonClientService {
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	protected boolean isSucc(Map map) {
		if(map==null || map.size()<=0){
			return false;
		}
		String code = (String) map.get(SystemConstant.RETURN_CODE_KEY);
		return SystemConstant.RETURN_SUCC.equals(code);
	}
	
	/**
	 * 请求jsonServer服务，返回List
	 * @param url 请求地址，包括url地址
	 * @param requestMap 请求参数
	 * @param pageInfo 分页信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List postForList(String url,Map<String,Object> requestMap,PageInfo pageInfo,List<Map<String, String>> orderBy){
		return (List)(postForMap(url,requestMap,pageInfo,orderBy).get("list"));
	}
	
	@SuppressWarnings("rawtypes")
	public List postForList(String url,Map<String,Object> requestMap,PageInfo pageInfo){
		return postForList(url, requestMap, pageInfo,null);
	}
	
	/**
	 * 无分页的请求
	 * @param url 请求地址
	 * @param requestMap 请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List postForList(String url,Map<String,Object> requestMap){
		return postForList(url, requestMap, null);
	}
	
	@SuppressWarnings("rawtypes")
	public Map postForMap(String url,Map<String,Object> requestMap,PageInfo pageInfo,List<Map<String, String>> orderBy){
		if(pageInfo!=null){
			Map<String,Object> pageInfoMap = new HashMap<String,Object>();
			pageInfoMap.put("curPage",pageInfo.getCurPage());
			pageInfoMap.put("pageLimit",pageInfo.getPageLimit());
			requestMap.put("pageInfo", pageInfoMap);
		}
		if(orderBy!=null){
			requestMap.put("orderBy", orderBy);
		}
		Map resultMap = postForObject(url,requestMap,Map.class,true);
		if(requestMap!=null && resultMap.get("dataCount")!=null && pageInfo!=null){
			pageInfo.setDataCount((Integer)resultMap.get("dataCount"));
		}
		return resultMap;
	}
	
	/**
	 * 请求服务，带有分页的
	 * @param url
	 * @param requestMap
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map postForMap(String url,Map<String,Object> requestMap,PageInfo pageInfo){
		return postForMap(url, requestMap, pageInfo, null);
	}
	
	@SuppressWarnings("rawtypes")
	public Map postForMap(String url,Map<String,Object> requestMap){
		return postForMap(url, requestMap,null);
	}
	
	/**
	 * 导出post请求
	 * @param url
	 * @param requestMap
	 * @param pageInfo
	 * @param orderBy
	 * @return
	 */
	public Map postForExportMap(String url,Map<String,Object> requestMap,PageInfo pageInfo,List<Map<String, String>> orderBy){
		Map<String, Object> reqMap = new HashMap<String, Object>(); 
		if(pageInfo!=null){
			Map<String,Object> pageInfoMap = new HashMap<String,Object>();
			pageInfoMap.put("curPage",pageInfo.getCurPage());
			pageInfoMap.put("pageLimit",pageInfo.getPageLimit());
			requestMap.put("pageInfo", pageInfoMap);
		}
		if(orderBy!=null){
			requestMap.put("orderBy", orderBy);
		}
		reqMap.put("fileName",requestMap.remove("exportFileName"));
		reqMap.put("fileType",requestMap.remove("exportFileType"));
		reqMap.put("params", requestMap);
		Map resultMap = postForObject(url,reqMap,Map.class,true);
		if(requestMap!=null && resultMap.get("dataCount")!=null && pageInfo!=null){
			pageInfo.setDataCount((Integer)resultMap.get("dataCount"));
		}
		return resultMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map postForExportMap(String url,Map<String,Object> requestMap,PageInfo pageInfo){
		return postForExportMap(url, requestMap, pageInfo, null);
	}
	
	/**
	 * 通用的参数
	 * @param url
	 * @param requestMap
	 * @param responseClass
	 * @return
	 */
	public <T> T postForObject(String url,Map<String,Object> requestMap,Class<T> responseClass){
		return postForObject(url,requestMap,responseClass,true);
	}
	
	/**
	 * post server
	 * @param url
	 * @param requestMap
	 * @param clazz
	 * @param isUserAuth 是否加入用户信息
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T postForObject(String url,Map<String,Object> requestMap,Class<T> responseClass,boolean isUserAuth){
		long startTime = System.currentTimeMillis();
		T result = null;
		logger.info("request url="+url+";request params:"+JsonHelper.parseToJson(requestMap));

		try {
			HttpHeaders headers = new HttpHeaders();
			String requestMapJson = JsonHelper.parseToJson(requestMap);
			if(requestMapJson != null){
				headers.add(SystemConstant.REQUEST_PAREMS_NAME, URLEncoder.encode(requestMapJson,"UTF-8"));
			}
			
			if(isUserAuth){
				UserInfo userInfo = getUserInfo();
				Map<String,Object> userMap = new HashMap<String,Object>(); //����Ҫ�����е��ֶζ�������̨
				userMap.put("userId",userInfo.getId());
				userMap.put("userName",userInfo.getUserName());
				userMap.put("loginIp",userInfo.getLoginIp());
				userMap.put("mdn",userInfo.getMdn());
				userMap.put("roleName",userInfo.getRoleName());
				headers.add("userInfo",URLEncoder.encode(JsonHelper.parseToJson(userMap),"UTF-8"));
				
				HttpEntity request= new HttpEntity(requestMap, headers);
				result = restTemplate.postForObject(url, request, responseClass);
			}else{
				HttpEntity request= new HttpEntity(requestMap, headers);
				result = restTemplate.postForObject(url, request, responseClass);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info("response url="+url+";response data:"+JsonHelper.parseToJson(result)+"; cost time:("+(endTime-startTime)+") ms");
		return result;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UserInfo getUserInfo(){
		return UserInfoHolder.get();
	}
}
