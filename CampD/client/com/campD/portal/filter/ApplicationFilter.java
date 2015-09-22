package com.campD.portal.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.campD.portal.common.SystemConstant;
import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.util.StringUtil;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.WebUtil;

/** 
 * 
* @ClassName: ApplicationFilter 
* @Description: 过滤器 
* @author flag
* @date 2014年9月3日 上午9:47:49 
*  
*/
public class ApplicationFilter implements Filter {
    
    private List<String> excludePathList;
    protected Logger logger = Logger.getLogger(ApplicationFilter.class);
    private Object lock = new Object();
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        
        logger.debug("Init ApplicationFilter");
        
        //将常量放到servletContxt中，供JSP中方便使用，例如:${systemConst.TEST_KEY}
        config.getServletContext().setAttribute("systemConst", SystemConstant.toMap());
        
        String url = config.getInitParameter("session_exclude_url");
        if (url != null) {
            excludePathList = Arrays.asList(url.split(","));
            logger.info("excludePathList=" + excludePathList);
        }
        
        config.getServletContext().setAttribute("ctx", SystemMessage.getString("ctx"));
        config.getServletContext().setAttribute("sysConfig", SystemMessage.getAllConfig());
        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
    	
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest)req;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String requestUrl = request.getRequestURI();
        requestUrl = requestUrl.substring(requestUrl.indexOf(request.getContextPath()) + request.getContextPath().length());
        
        UserInfo userInfo = (UserInfo)WebUtil.getValueFromSession(request,SystemConstant.USER_INFO);
        
        //验证session不允许过去，跳到登陆页面
        if (userInfo == null && (excludePathList == null || !excludePathList.contains(requestUrl)) && !requestUrl.endsWith(".jsp")) {
        	logger.info("request userInfo is null");
        	errorDeal(response, request,SystemConstant.ERROR_CODE_UNLOGIN,"/toLogin.do");
            return;
        }
        
        //防止表单重复提交
        boolean validTokenRes = validateToken(request);
        if(!validTokenRes){
        	errorDeal(response, request, SystemConstant.ERROR_CODE_FORM_RESUBMIT,"/page/common/error500.jsp");
        	return;
        }
        
        UserInfoHolder.set(userInfo);
        
        if (userInfo != null) {
        	MDC.put("userId", userInfo.getId() == null ? "" : userInfo.getId());
        	MDC.put("mdn", userInfo.getMdn() == null ? "" : userInfo.getMdn());
            MDC.put("ip", userInfo.getLoginIp() == null ? "" : userInfo.getLoginIp());
        }else{
        	MDC.put("ip", WebUtil.getIPAddress(request));
        }
        
        try {
            fc.doFilter(request, resp);
        } catch (IOException e) {
            logger.error("an error has occurred!", e);
        } catch (ServletException e) {
            logger.error("an error has occurred!", e);
        } finally {
            UserInfoHolder.remove();
            MDC.remove("ip");
            if (userInfo != null) {
                MDC.remove("userId");
            }
        }
    }

    /**
     * 当filter处理不能继续进入controller时错误处理
     * @param response
     * @param request
     * @param redirectUrl 重定向的url(如果是ajax请求则返回状态码)
     * @throws IOException
     */
	public void errorDeal(HttpServletResponse response, HttpServletRequest request, String errorCode, String redirectUrl) throws IOException {
		
		String ajaxHeader = request.getHeader("X-Requested-With");
		boolean isAjaxReq = (null!=ajaxHeader)&&("XMLHttpRequest".equals(ajaxHeader))?true:false;
		if(isAjaxReq) {
		    response.setHeader("status", errorCode);
		    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}else {
		    response.sendRedirect(SystemMessage.getString("ctx") + redirectUrl);
		}
	}
	/**
	 * 验证表单提交的token
	 * @param request
	 * @return 返回验证是否成功
	 */
	@SuppressWarnings("unchecked")
	private boolean validateToken(HttpServletRequest request) {
		
		String tokenName = request.getParameter(SystemConstant.KEY_FORM_TOKEN_NAME);
        String submitToken = request.getParameter(SystemConstant.KEY_FORM_TOKEN_VALUE);
        //存在token
        if(!StringUtil.isEmpty(tokenName)&&!StringUtil.isEmpty(submitToken)){
        	Map<String, String> formTokenMap = (Map<String, String>) WebUtil.getValueFromSession(request, SystemConstant.SESSION_KEY_FORM_TOKEN);
        	//存在token需要校验
        	if(formTokenMap!=null){
        		synchronized (lock) {
					if(formTokenMap.containsKey(tokenName) && submitToken.equals(formTokenMap.get(tokenName))){
						formTokenMap.remove(tokenName);
						WebUtil.addSession(request, SystemConstant.SESSION_KEY_FORM_TOKEN, formTokenMap);
						return true;
					} else {
						logger.info("has a form resubmited formName:"+tokenName);
		        		return false;
					}
				}
        	} else {
        		logger.info("formTokenMap is null,has a form resubmited formName:"+tokenName);
        		return false;
        	}
        }
        return true;
	}
    
    @Override
    public void destroy() {
        logger.debug("Destroy ApplicationFilter");
    }
    
}
