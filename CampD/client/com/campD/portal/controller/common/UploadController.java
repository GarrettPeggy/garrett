/**
 * 
 */
package com.campD.portal.controller.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.controller.BaseController;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.UploadFileUtil;

/**
 * 文件上传公共方法
 * @author Administrator
 *
 */
public class UploadController extends BaseController{
	
	public static final long LOGO_IMG_MAX_SIZE = 2*1024*1024;

	/**
	 * 发布轻应用-上传截图到临时文件
	 */
	@RequestMapping("/uploadCropImg.do")
	@ResponseBody
	public JSONView uploadCropImg(MultipartFile cropImg, HttpServletRequest request, ModelMap model) throws Exception{
		
		if(cropImg!=null){
			
			// 判断文件类型是否正确
			String fileName = cropImg.getOriginalFilename();
			if(!UploadFileUtil.isAvaliableFileFmt(fileName,SystemMessage.getString("fileFormatter"))){
				return errorCodeSearchView(SystemConstant.ERROR_CODE_FILE_FORMAT);
			}
			// 判断文件大小是否符合要求
			long size = cropImg.getSize();
			if(LOGO_IMG_MAX_SIZE < size){
				return errorCodeSearchView(SystemConstant.ERROR_CODE_FILE_TOO_BIG);
			}
			
			String tmpPath = UploadFileUtil.getTmpRealPath(fileName, getUserInfo(), request);
			cropImg.transferTo(new File(tmpPath));
			Map<String, String> resMap = new HashMap<String, String>();
			resMap.put("tmpPath", UploadFileUtil.getRelativePath(tmpPath, request));
			JSONView view = getSearchJSONView(resMap);
			view.setSuccess();
			return view;
		}
		return errorCodeSearchView(SystemConstant.ERROR_CODE_PARAM_NULL);
	}
}
