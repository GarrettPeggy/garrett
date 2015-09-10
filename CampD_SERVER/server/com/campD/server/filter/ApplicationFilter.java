package com.campD.server.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.util.StringUtils;

import com.campD.server.common.JSONView;
import com.campD.server.common.SystemConstant;
import com.campD.server.common.UserInfoHolder;
import com.campD.server.model.UserInfo;
import com.campD.server.util.JsonHelper;

/**
 * 全局过滤 获取企业
 * 
 * @author Rain
 * 
 */
public class ApplicationFilter implements Filter {

	private List<String> excludePathList = new ArrayList<String>();
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void init(FilterConfig config) throws ServletException {
		String url = config.getInitParameter("auth_exclude_url");
		if (url != null) {
			excludePathList.addAll(Arrays.asList(url.split(",")));
			logger.info("auth_exclude_url=" + excludePathList);
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		
		// 设置字符集
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		JSONView jsonError = new JSONView();
		String reuqestUrl = request.getRequestURI();
		long requestStart = System.currentTimeMillis();
		String serviceName = null;
		try {
			reuqestUrl = reuqestUrl.substring(reuqestUrl.indexOf(request.getContextPath()) + request.getContextPath().length());
			serviceName = reuqestUrl.split("/", 3)[1]; // 服务名称
			//这个指的是当前服务的根路径名
			if (serviceName == null || "".equals(serviceName)) {
				jsonError.setReturnCode(SystemConstant.RETURN_NULL_ERROR);
				jsonError.setReturnMsg("权限不足");
				response.setContentType("text/html");
				response.getWriter().write(jsonError.toString());
				return;
			}
			
			logger.info("当前服务名是->" + serviceName);

			MDC.put("serviceName", serviceName);
			String sessionUser = request.getHeader("userInfo");
			UserInfo userInfo = null;
			if (sessionUser != null) {
				sessionUser = URLDecoder.decode(sessionUser, "UTF-8");
				userInfo = JsonHelper.parseToObject(sessionUser, UserInfo.class);
				UserInfoHolder.set(userInfo);
				MDC.put("ip", userInfo.getLoginIp()==null ? "unkownIp" : userInfo.getLoginIp());
				String user = userInfo.getMdn()+"-"+(userInfo.getUserame() == null ? "unkownUser": userInfo.getUserame());
				MDC.put("userName", user);
			}

			// 保证是不根服务，没有controller跟路径这样的请求
			if (!reuqestUrl.replaceAll("/","").equals(serviceName) && !isLegalVisit(userInfo) && !excludePathList.contains(reuqestUrl)) {
				jsonError.setReturnCode(SystemConstant.RETURN_DATA_ERROR);
				jsonError.setReturnMsg("请求非法");
				response.setContentType("text/html");
				response.getWriter().write(jsonError.toString());
				return;
			}
			fc.doFilter(request, response);

		} catch (Exception e) {
			logger.info("请求"+serviceName+"服务发生异常", e);
			try {
				jsonError.setReturnCode(SystemConstant.RETURN_EXE_FAIL);
				jsonError.setReturnMsg("操作异常," + e.getMessage());
				response.setContentType("text/html");
				response.getWriter().write(jsonError.toString());
				return;
			} catch (Exception e1) {
				logger.info("请求"+serviceName+"服务发生异常", e1);
			}
			throw e;
		} finally {
			//1s 就认为是比较慢的，需要处理
			if(System.currentTimeMillis()-requestStart>1000){ 
				logger.info("request url=" + reuqestUrl + " cost time:" + (System.currentTimeMillis() - requestStart) + "ms. the request is slowly;serviceName="+serviceName);
			}
			MDC.remove("ip");
			MDC.remove("userName");
			MDC.remove("serviceName");
			UserInfoHolder.remove();
		}

	}

	/**
	 * 验证是否合法访问
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean isLegalVisit(UserInfo userInfo) {
		if (userInfo == null || StringUtils.isEmpty(userInfo.getMdn())) {
			return false;
		}
		return true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
