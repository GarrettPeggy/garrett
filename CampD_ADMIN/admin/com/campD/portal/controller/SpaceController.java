/**
 * 
 */
package com.campD.portal.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/space")
public class SpaceController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());
	
	/**
	 * @return
	 * 去添加场地信息
	 */
	@RequestMapping("/toAddSpace.do")
    public String toAddSpace(){
		
        return "space/addSpaceInfo";
    }
	
}
