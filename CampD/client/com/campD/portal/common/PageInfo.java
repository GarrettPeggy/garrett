package com.campD.portal.common;

import javax.servlet.http.HttpServletRequest;

import com.campD.portal.util.WebUtil;

/**
 * @ClassName: PageInfo
 * @Description: 封装分页处理�?
 * @author li_xiaodong
 * @date 2014�?9�?5�? 下午2:25:16
 * 
 */
public class PageInfo {

	/**
	 * 分页数显示为5�?
	 */
	public static Integer ORDER_NUM = 5;

	/**
	 * 每一页大�?
	 */
	private Integer pageLimit = 10;

	/**
	 * 数据数量
	 */
	private Integer dataCount = 0;

	/**
	 * 当前页码
	 */
	private Integer curPage = 1;
	


	public PageInfo() {

	}

	public PageInfo(HttpServletRequest request) {
		Integer reqPageLimit = WebUtil.getInteger(request, "pageLimit");
		if (reqPageLimit != null && reqPageLimit > 0) {
			pageLimit = reqPageLimit;
		}
		// 自定义当前页
		Integer dCurPage = WebUtil.getInteger(request, "dCurPage");
		if (dCurPage != null && dCurPage > 0) {
			curPage = dCurPage;
		}
		Integer reqCurPage = WebUtil.getInteger(request, "curPage");
		if (reqCurPage != null && reqCurPage > 0) {
			curPage = reqCurPage;
		}
		request.setAttribute("pageInfo", this);
	}

	
	public String getNoDataHtml(){
		Integer count = getDataCount();
		if(count>0){
			return "";
		}else{
			String noDataHtml = "<div class=\"nodata\"></div>";
			return noDataHtml;
		}
	}
	/**
	 * 
	 * 生成分页的主方法
	 * 
	 */
	public String getHtml() {

		// 获取总页�?
		int pageSize = getPageSize();
		int count = getDataCount();
		int curpage = getCurPage();
		//int pageLimit =getPageLimit();
		int prepage = getPrePage();

		int halfPageNum = ORDER_NUM/2;
		// 页数小于等于1时，不显示分�?
		StringBuffer html = new StringBuffer();
		StringBuffer totalDiv = new StringBuffer("<div class=\"pages_total\">�?").append(count).append("�?</div>");
		StringBuffer ellipsis = new StringBuffer("<li><span class=\"dian\">...</span></li>");
		
		StringBuffer selectPage = new StringBuffer();
		selectPage.append("<span class=\"selectPage\">");
		selectPage.append("<span class=\"text\">显示�?</span>");
		selectPage.append("<select name=\"pageLimit\" id=\"pageLimit\">");
		int pageLimits[] = {10,50,100};
		for(Integer limit:pageLimits){
			selectPage.append("<option value='").append(limit).append("'").append(getPageLimit()==limit.intValue()?"selected":"").append(">").append(limit).append("</option>");
		}

		selectPage.append("</select></span>");		
		
		if (pageSize <= 1) {
			if(count>0){
				html.append(totalDiv);
				html.append(selectPage);
				return html.toString();
			}else{
				return "";
			}
		}
		
		
		
		html.append("<div class=\"pages\">");
		html.append("<input id=\"curPage\" name = \"curPage\" type=\"hidden\" value=\"").append(curpage).append("\"/><ul>");
		//上一�?
		if(curpage>1){
			html.append("<li><a href=\"javascript:void(0);\" class=\"prev\" value=\"").append(prepage).append("\">上一�?</a></li>");
		}
		//第一�?
		if(curpage==1){
			html.append("<li><span class=\"current\">1</span></li>");
		}else{
			html.append("<li><a href=\"javascript:void(0);\" value=\"1\" >1</a></li>");
		}
		//前端省略�?
		if((curpage - halfPageNum) > halfPageNum){
			html.append(ellipsis);
		}

		int startPage = 0;
		int endPage = 1;
		if (pageSize > ORDER_NUM) {
			startPage = curpage - halfPageNum;
			startPage = startPage <= 0 ? 1 : startPage;

			endPage = curpage + halfPageNum;
			endPage = endPage > pageSize ? pageSize : endPage;

		} else {
			startPage = 1;
			endPage = pageSize;
		}
		//显示的页数小于规定页数从末端页数补齐页码
		if (endPage - startPage+1 < ORDER_NUM) {
			int showNum = endPage - startPage + 1;
			endPage = endPage + (ORDER_NUM - showNum);
		}
		endPage = endPage > pageSize-1 ? pageSize-1 : endPage;
		//显示的页数小于规定页数从首端页数补齐页码
		if (endPage - startPage+1 < ORDER_NUM) {
			int showNum = endPage - startPage + 1;
			startPage = startPage-(ORDER_NUM - showNum);
		}
		startPage = startPage <= 1 ? 2 : startPage;
		// 拼接处页�?
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				html.append("<li><span class=\"current\">").append(i).append("</span></li>");
			} else {
				html.append("<li><a href=\"javascript:void(0);\" value=\"")
						.append(i).append("\">").append(i).append("</a></li>");
			}
		}
		
		//后端省略�?
		if((curpage + halfPageNum) < pageSize-1){
			html.append(ellipsis);
		}
		
		if(curpage==pageSize){
			html.append("<li><span class=\"current\">").append(pageSize).append("</span></li>");
		}else{
			html.append("<li><a href=\"javascript:void(0);\" value=\"").append(pageSize).append("\" >").append(pageSize).append("</a></li>");
		}
		// 显示�?后一�?
		if(curpage<pageSize){
			html.append("<li><a href=\"javascript:void(0);\" class=\"next\" value=\"").append(getNextPage()).append("\">下一�?</a></li>");
		}
		html.append("</div>");
		html.append(totalDiv);
		html.append(selectPage);
		html.append("<div class=\"clr\"></div>");
		return html.toString();
	}

	public Integer getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	



	// 总页数：总条数除于显示条数限�?
	public Integer getPageSize() {
		return dataCount % pageLimit == 0 ? dataCount / pageLimit : dataCount
				/ pageLimit + 1;
	}

	/**
	 * 获取上一�?
	 * 
	 * @return
	 */
	public Integer getPrePage() {
		return getCurPage() == 1 ? 1 : getCurPage() - 1;
	}

	/**
	 * 获取下一�?
	 * 
	 * @return
	 */
	public Integer getNextPage() {
		return getCurPage() + 1 > getPageSize() ? getCurPage()
				: getCurPage() + 1;
	}

	public static void main(String[] args) {
		PageInfo p = new PageInfo();
		p.setCurPage(2);
		p.setDataCount(1000);
		String str = p.getHtml();
		System.out.println(str);
	}
}
