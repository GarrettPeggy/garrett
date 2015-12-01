/**
 * 
 */
package com.campD.portal.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.DemoCacheService;
import com.campD.portal.service.DemoService;
import com.campD.portal.util.DateUtil;

/**
 * @author Garrett Wang
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private DemoCacheService demoCacheService;
	
	@RequestMapping("/index.do")
    public String index(){
		
		UserInfoHolder.set(new UserInfo());
		
        return "user/login";
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/greeting.do")
    @ResponseBody
    public Map greeting(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		
		return demoCacheService.getGreetingContent();
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/register.do")
    @ResponseBody
    public Map register(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		Map reqMap = bindParamToMap(request);
		
		return demoService.register(reqMap);
    }
	
	@RequestMapping(value = "/excel/export.do")    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {    
        HSSFWorkbook wb = demoService.export();    
        response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=student_"+DateUtil.fmtDate(new Date())+".xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();    
   } 
}
