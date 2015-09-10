package com.campD.portal.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.campD.portal.common.SystemConstant;
import com.campD.portal.util.StringUtil;
import com.campD.portal.util.WebUtil;

public class TokenTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	
	private static final Random random = new Random();
	/**
	 * 当前form的token名称
	 */
	private String tokenName;
	
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		try {
			if(StringUtil.isEmpty(tokenName)){
				return SKIP_BODY;
			}
			String token = random.nextDouble()+""+System.currentTimeMillis();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Map<String, String> tokenMap = (Map<String, String>) WebUtil.getValueFromSession(request, SystemConstant.SESSION_KEY_FORM_TOKEN);
			if(tokenMap==null){
				tokenMap = new HashMap<String, String>();
			}
			tokenMap.put(tokenName, token);
			WebUtil.addSession(request, SystemConstant.SESSION_KEY_FORM_TOKEN, tokenMap);
			StringBuffer tokenHtml = new StringBuffer();
			
			tokenHtml.append("<input type=\"hidden\" name=\"").append(SystemConstant.KEY_FORM_TOKEN_NAME).append("\" ");
			tokenHtml.append(" value=\"").append(tokenName).append("\"/>");
			
			tokenHtml.append("<input type=\"hidden\" name=\"").append(SystemConstant.KEY_FORM_TOKEN_VALUE).append("\" ");
			tokenHtml.append(" value=\"").append(token).append("\"/>");
			pageContext.getOut().write(tokenHtml.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	public static void main(String[] args) {
		System.out.println(new Random().nextDouble());
		
		System.out.println();
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	
}
