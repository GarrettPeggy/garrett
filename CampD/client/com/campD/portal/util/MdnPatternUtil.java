package com.campD.portal.util;

import java.util.regex.Pattern;

public class MdnPatternUtil {
	public static String lt_regexp = SystemMessage.getString("lt_regexp");
	public static String dx_regexp = SystemMessage.getString("dx_regexp");
	public static String yd_regexp = SystemMessage.getString("yd_regexp");
	public static String xl_regexp = SystemMessage.getString("xl_regexp");
	
	public static Pattern lt = Pattern.compile(lt_regexp);
	public static Pattern dx = Pattern.compile(dx_regexp);
	public static Pattern yd = Pattern.compile(yd_regexp);
	public static Pattern xl = Pattern.compile(xl_regexp);
	
	/**
	 * 判断是否是手机号
	 * @param mdn
	 * @return
	 */
	public static int validateMdn(String mdn) {
		if (lt.matcher(mdn).matches()) {
			return 1;
		} else if (dx.matcher(mdn).matches()) {
			return 2;
		} else if (yd.matcher(mdn).matches()) {
			return 3;
		} else {
			return -1;
		}
	}
}
