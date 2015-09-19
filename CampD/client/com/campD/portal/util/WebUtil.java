package com.campD.portal.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campD.portal.security.BASE64;
import com.campD.portal.security.ThreeDES;

/**
 * Web Util 锟�?
 * 
 * @author Rain
 * 
 */
public class WebUtil {

	public static final int COOKIE_DEFAULT_TIME_YEAR = 365 * 24 * 60 * 60;

	public static SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
	static{
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	public static String getString(Double db) {
		if (db == null) {
			return "";
		}
		return db.toString();
	}

	private static String getString(String str) {
		if (str == null || str.equals("null")) {
			return "";
		}
		return str;
	}

	public static Integer getInteger(String str) {
		if (str == null || str.equals("null") || "".equals(str))
			return null;
		return new Integer(str);
	}

	public static int getInt(String str) {
		if (str == null || str.equals("null"))
			return 0;
		return new Integer(str).intValue();
	}

	public static String getParamByRegex(HttpServletRequest request,
			String regex) {

		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			if (name.matches(regex)) {
				return request.getParameter(name);
			}
		}
		return null;
	}

	/**
	 * 值
	 * 
	 * @param request
	 * @param paramName
	 * @param def
	 * @return
	 */
	public static int getInt(HttpServletRequest request, String paramName,
			int def) {
		String str = getString(request.getParameter(paramName));
		if (str.equals("")) {
			return def;
		} else {
			return new Integer(str).intValue();
		}
	}

	public static Integer getInteger(HttpServletRequest request,
			String paramName) {
		String str = getString(request.getParameter(paramName));
		return getInteger(str);
	}

	public static String getString(HttpServletRequest request, String paramName) {
		return getString(request.getParameter(paramName));
	}

	public static String[] getStrings(HttpServletRequest request,
			String paramName) {
		return request.getParameterValues(paramName);
	}

	/**
	 * 鑾峰彇璁块棶IP鍦板潃
	 * 
	 * @param request
	 * @return
	 */
	public static String getIPAddress(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 璁剧疆Session
	 * 
	 * @param request
	 * @param SessionName
	 * @param value
	 */
	public static void addSession(HttpServletRequest request,
			String sessionName, Object value) {
		if (value != null) {
			request.getSession().setAttribute(sessionName, value);
		}
	}
	
	/**
	 * 绉婚櫎Session
	 * 
	 * @param request
	 * @param SessionName
	 * @param value
	 */
	public static void removeSession(HttpServletRequest request,
			String sessionName) {
		if (sessionName != null) {
			request.getSession().removeAttribute(sessionName);
		}
	}

	/**
	 * 鍙朣ession涓拷??
	 * 
	 * @param request
	 * @param SessionName
	 * @param value
	 */
	public static Object getValueFromSession(HttpServletRequest request,
			String key) {
		if (key != null || !"".equals(key)) {
		    return request.getSession().getAttribute(key);
		}
		return null;
	}

	/**
	 * 璁剧疆cookie锛寁alue锟�?(URLEncoder.encode)
	 * 
	 * @param response
	 * @param cookieName
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response,
			String cookieName, String value, int maxAge) {

		String val = value;

		try {

			val = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		Cookie cookie = new Cookie(cookieName, val);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 璁剧疆cookie 榛樿涓轰竴锟�?
	 * 
	 * @param response
	 * @param cookieName
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response,
			String cookieName, String value) {
		addCookie(response, cookieName, value, COOKIE_DEFAULT_TIME_YEAR);
	}

	/**
	 * 鑾峰彇cookie鐨勶拷?锟斤紝value锟�?(URLEncoder.encode)
	 * 
	 * @param response
	 * @param cookieName
	 * @param value
	 */
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {

		Cookie cookie = getCookie(request, cookieName);
		if (cookie == null) {
			return null;
		}

		String cookieVal = "";

		try {
			cookieVal = URLDecoder.decode(cookie.getValue(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return cookieVal;

	}

    /**
     * 璁剧疆鍔犲瘑鐨刢ookie
     * 
     * @param response
     * @param cookieName
     * @param value
     * @param maxAge
     */
    public static void addSecurityCookie(HttpServletResponse response, String cookieName, String value, int maxAge) {
        addSecurityCookie(response, cookieName, value, null, maxAge, false);
    }

    /**
     * 璁剧疆鍔犲瘑鐨刢ookie,鏃犵敓鍛藉懆锟�?
     * 
     * @param response
     * @param cookieName
     * @param value
     * @param maxAge
     */
    public static void addSecurityCookie(HttpServletResponse response, String cookieName, String value, String domain,
            int maxAge, boolean ishttpOnly) {
        try {
            if (value == null) {
                return;
            }
            String ssValue = BASE64.encryptBASE64(ThreeDES.encryptMode(value));
            String str = cookieName + "=" + URLEncoder.encode(ssValue, "UTF-8") + ";Path=/;";
            if (!StringUtil.isEmpty(domain)) {
                str += "Domain=" + domain + ";";
            }
            if(maxAge>0){
            	Date d = new Date();
            	long l = d.getTime()+ maxAge * 1000;
            	d.setTime(l);
            	String expires = sdf.format(d);
            	str +="Expires="+expires+";";
                str += "Max-Age=" + maxAge + ";";
            }
            if (ishttpOnly) {
                str += "HTTPOnly";
            }
            response.addHeader("Set-Cookie", str);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	/**
	 * 鑾峰彇鍔犲瘑鍓嶇殑cookie锟�?
	 * 
	 * @param response
	 * @param cookieName
	 * @param value
	 * @param maxAge
	 */
	public static String getSecurityCookieValue(HttpServletRequest request,
			String cookieName) {
		try {
			String sValue = getCookieValue(request, cookieName);
			if (sValue == null) {
				return null;
			}
			return new String(
					ThreeDES.decryptMode(BASE64.decryptBASE64(sValue)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

    /**
     * 鍒犻櫎Cookie淇℃伅锟�?(鎸囧畾浜哻ookie鍩熷悕)
     * 
     * @param HttpServletResponse
     *            response
     * @param HttpServletRequest
     *            request
     * @param String
     *            cookieName
     * @param String
     *            cookieDomain
     * @return
     * 
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieDomain) {
        if (!StringUtil.isEmpty(cookieName)) {
            Cookie cookie = getCookie(request, cookieName);
            if (cookie != null) {
                cookie.setMaxAge(0);
                if (!StringUtil.isEmpty(cookieDomain)) {
                    cookie.setDomain(cookieDomain);
                }
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

	/**
	 * 杩斿洖Cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {

		Cookie[] cookie = request.getCookies();
		if (cookie != null) {
			for (Cookie c : cookie) {
				if (c.getName().equals(cookieName)) {
					return c;
				}
			}
		}
		return null;
	}

}
