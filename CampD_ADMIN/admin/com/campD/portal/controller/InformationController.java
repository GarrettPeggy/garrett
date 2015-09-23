/**
 * 
 */
package com.campD.portal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campD.portal.service.InformationService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/information")
public class InformationController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private InformationService informationService;
	
	/**
	 * 去配置首页轮播图
	 */
	@RequestMapping("/toHomePicConfig.do")
    public String toHomePicConfig(){
		
        return "information/homePicConfig";
    }
	
}
