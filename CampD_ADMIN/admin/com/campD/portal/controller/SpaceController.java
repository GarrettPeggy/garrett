/**
 * 
 */
package com.campD.portal.controller;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.SpaceService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.OSSUtil;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.UploadFileUtil;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/space")
public class SpaceController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SpaceService spaceService;
	
	@RequestMapping("/toList.do")
    public String toList(){
		
        return "space/spaceList";
    }
	
	/**
	 * 场地列表--查询
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/list.do")
	public String querySpaceList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map spaceListMap = spaceService.getSpaceList(reqMap, pageInfo);
		mop.addAttribute("spaceListMap", spaceListMap);
		
		return "space/spaceListCtx"; 
	}
	
	/**
	 * @return
	 * 去添加场地信息
	 */
	@RequestMapping("/toAdd.do")
    public String toAdd(){
		
        return "space/addSpaceInfo";
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/add.do")
    @ResponseBody
    public Map addSpaceInfo(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		Map<?, ?> returnMap = null;
		String description = (String) reqMap.get("description");
		returnMap = uploadDescriptionToOSS(description, request);
		
		// 假如场地描述保存文件成功则将场地信息保存到数据库
		if(JSONView.RETURN_SUCCESS_CODE.equals(returnMap.get("returnCode"))){
			UserInfo userInfo = getUserInfo();
			reqMap.put("creatorId", userInfo.getId());
			reqMap.put("description", returnMap.get("descriptionURL"));
			returnMap = spaceService.addSpace(reqMap);
		}
		
		return getOperateJSONView(returnMap);
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping("/update.do")
    @ResponseBody
    public Map updateSpaceInfo(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map<?, ?> returnMap = null;
		String description = (String) reqMap.get("description");
		// 重新生成活动需求的文件
		returnMap = uploadDescriptionToOSS(description, request);
		
		// 假如需求保存文件成功则将活动保存到数据库
		if(JSONView.RETURN_SUCCESS_CODE.equals(returnMap.get("returnCode"))){
			reqMap.put("description", returnMap.get("descriptionURL"));
			returnMap = spaceService.updateSpace(reqMap);
		}

		// 删除原有的文件
		if(null!=reqMap.get("originalDiscriptionURL")){
			OSSUtil.deleteFile((String) reqMap.get("originalDiscriptionURL"));
		}
		
		return getOperateJSONView(returnMap);
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map uploadDescriptionToOSS(String description, HttpServletRequest request){
		
		Map returnMap = null;
		String fileName = FileUtils.getNumberFileName(UUID.randomUUID().toString() + "_" + new Random().nextInt(1000));// 随机生成一个文件名
		String tmpPath = UploadFileUtil.getTmpFileRealPath(fileName, getUserInfo(), request);//文件保存目录路径
		String relativePath = UploadFileUtil.getRelativePath(tmpPath, request).replace("\\", "/");// 相对路径
		FileUtils.writerFile(description, tmpPath);
		
		// 往阿里OOS上面上传文件
		returnMap = OSSUtil.uploadFile(tmpPath);
		returnMap.put("descriptionURL", relativePath);
		
		return returnMap;
	}
	
	/**
	 * 去编辑场地信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toEditSpace.do")
	public String toEditSpace(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map spaceMap = spaceService.getSpaceById(reqMap);
		// 读取远程活动需求的内容
		readDscriptionFromRemoteFile(spaceMap);
		mop.addAttribute("spaceMap", spaceMap);
		
		bindParamToAttrbute(request);
		return "space/editSpaceInfo"; 
	}
	
	/**
	 * 去预览场地信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toViewSpace.do")
	public String toViewSpace(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map spaceMap = spaceService.getSpaceById(reqMap);
		// 读取远程活动需求的内容
		readDscriptionFromRemoteFile(spaceMap);
		mop.addAttribute("spaceMap", spaceMap);
		
		return "space/viewSpaceInfo"; 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readDscriptionFromRemoteFile(Map spaceMap){
		String description = (String) ((Map)spaceMap.get("spaceInfo")).get("description");
		description = description.replace("\\", "/");
		int index = description.indexOf("attached/html");
		if(null!=description && index>-1){ // 将连接更新为内容
			((Map)spaceMap.get("spaceInfo")).put("originalDiscriptionURL", description.substring(index));
			((Map)spaceMap.get("spaceInfo")).put("description",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + description));
		}
	}
	
	/**
	 * 更新用户级别
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/updateLevel.do")
    @ResponseBody
    public Map updateLevel(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map returnMap = spaceService.updateSpaceLevel(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
}
