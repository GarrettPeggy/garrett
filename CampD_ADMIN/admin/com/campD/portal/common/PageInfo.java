package com.campD.portal.common;

import javax.servlet.http.HttpServletRequest;

import com.campD.portal.util.WebUtil;


/**
 * @ClassName: PageInfo
 * @Description: 封装分页处理类
 * @author li_xiaodong
 * @date 2014年9月5日 下午2:25:16
 * 
 */
public class PageInfo {

	/**
	 * 分页数显示为5个
	 */
	public static Integer ORDER_NUM = 5;

	/**
	 * 每一页大小
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

	public String getNoDataHtml() {
		Integer count = getDataCount();
		if (count > 0) {
			return "";
		} else {
			String noDataHtml = "<tr><td colspan=\"20\"><div class=\"nodata\"><i class=\"icon-hdd\"></i><p>暂无数据...</p></div></td></tr>";
			return noDataHtml;
		}
	}

	/**
	 * 
	 * 生成分页的主方法
	 * 
	 */
	public String getHtml() {

		// 获取总页数
		int pageSize = getPageSize();
		int count = getDataCount();
		int curpage = getCurPage();
		int prepage = getPrePage();
		int halfPageNum = ORDER_NUM/2;
		// 页数小于等于1时，不显示分页
		if (pageSize <= 1) {
			return "";
		}
		StringBuffer ellipsis = new StringBuffer("<li><span>...</span></li>");
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"row table-page\"><div class=\"col-xs-6\"></div><div class=\"col-xs-6\">");
		html.append("<div class=\"dataTables_paginate paging_simple_numbers\"><ul class=\"pagination pull-right\">");
		html.append("<input id=\"curPage\" name = \"curPage\" type=\"hidden\" value=\"").append(curpage).append("\"/>");
		//上一页
		if(curpage>1){
			html.append("<li><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"").append(prepage).append("\"><i class=\"ace-icon fa fa-angle-double-left\"></i></a></li>");
		}
		//第一页
		if(curpage==1){
			html.append("<li class=\"disabled\"><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"").append(prepage).append("\"><i class=\"ace-icon fa fa-angle-double-left\"></i></a></li>");
			html.append("<li class=\"active\"><span>1</span></li>");
		}else{
			html.append("<li><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"1\" >1</a></li>");
		}
		//前端省略号
		if((curpage - halfPageNum) > halfPageNum && pageSize>7){
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
		// 拼接处页号
		for (int i = startPage; i <= endPage; i++) {
			if (i == curPage) {
				html.append("<li class=\"active\"><span>").append(i).append("</span></li>");
			} else {
				html.append("<li><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"")
						.append(i).append("\">").append(i).append("</a></li>");
			}
		}
		
		//后端省略号
		if((curpage + halfPageNum) < pageSize-1){
			html.append(ellipsis);
		}
		
		if(curpage==pageSize){
			html.append("<li class=\"active\"><span>").append(pageSize).append("</span></li>");
			html.append("<li class=\"disabled\" ><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"").append(getNextPage()).append("\"><i class=\"ace-icon fa fa-angle-double-right\"></i></a></li>");
		}else{
			html.append("<li><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"").append(pageSize).append("\" >").append(pageSize).append("</a></li>");
		}
		// 显示最后一页
		if(curpage<pageSize){
			html.append("<li><a href=\"javascript:void(0);\" name=\"sysPageBtn\" value=\"").append(getNextPage()).append("\"><i class=\"ace-icon fa fa-angle-double-right\"></i></a></li>");
		}
		html.append("</ul><div class=\"total-page\">共 "+ pageSize + "页</div></div></div></div></div>");
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

	// 总页数：总条数除于显示条数限制
	public Integer getPageSize() {
		return dataCount % pageLimit == 0 ? dataCount / pageLimit : dataCount / pageLimit + 1;
	}

	/**
	 * 获取上一页
	 * 
	 * @return
	 */
	public Integer getPrePage() {
		return getCurPage() == 1 ? 1 : getCurPage() - 1;
	}

	/**
	 * 获取下一页
	 * 
	 * @return
	 */
	public Integer getNextPage() {
		return getCurPage() + 1 > getPageSize() ? getCurPage() : getCurPage() + 1;
	}

	public static void main(String[] args) {
		PageInfo p = new PageInfo();
		p.setCurPage(2);
		p.setDataCount(1000);
		String str = p.getHtml();
		System.out.println(str);
	}
}
