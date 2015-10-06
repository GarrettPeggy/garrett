/**
 * 
 */
package com.campD.portal.controller.common;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.controller.BaseController;
import com.campD.portal.util.ImageUtil;
import com.campD.portal.util.OSSUtil;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.UploadFileUtil;

/**
 * 文件上传公共方法
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{
	
	protected Logger logger = Logger.getLogger(getClass());
	
	public static final long LOGO_IMG_MAX_SIZE = 100*1024;

	/**
	 * 图片上传公共方法
	 */
	@RequestMapping("/uploadCropImg.do")
	@ResponseBody
	public JSONView uploadImages(MultipartFile cropImg, HttpServletRequest request, ModelMap model) throws Exception{
		
		if(cropImg!=null){
			
			// 判断文件类型是否正确
			String fileName = cropImg.getOriginalFilename();
			if(!UploadFileUtil.isAvaliableFileFmt(fileName,SystemMessage.getString("fileFormatter"))){
				return errorCodeSearchView(SystemConstant.ERROR_CODE_FILE_FORMAT);
			}

			String tmpPath = UploadFileUtil.getTmpRealPath(fileName, getUserInfo(), request);
			File tempFile = new File(tmpPath);
			cropImg.transferTo(tempFile);
			// 判断文件大小是否符合要求
			long size = tempFile.length();
			logger.info("开始大小->"+size);
			
			if(LOGO_IMG_MAX_SIZE < size){// 如果图片大于100k，就一直压缩到100k以内
				//return errorCodeSearchView(SystemConstant.ERROR_CODE_FILE_TOO_BIG);
				ImageUtil image  = new ImageUtil(tempFile);
		        image.saveAs(tmpPath);
				size = tempFile.length();
				logger.info("压缩之后大小->"+size);
			} 
			
			Map<String, String> resMap = new HashMap<String, String>();
			resMap.put("realPath", tmpPath);
			resMap.put("tmpPath", request.getScheme() + "://" + SystemMessage.getString("rmtResUrl") + UploadFileUtil.getRelativePath(tmpPath, request).replace("\\","/"));
			JSONView view = getSearchJSONView(resMap);
			view.setSuccess();
			
			logger.info("文件路径->"+resMap);
			
			return view;
		}
		return errorCodeSearchView(SystemConstant.ERROR_CODE_PARAM_NULL);
	}
	
	/**
	 * 图片上传至OSS
	 */
	@RequestMapping("/uploadImageToOSS.do")
	@ResponseBody
	public JSONView uploadImageToOSS(HttpServletRequest request, ModelMap model) throws Exception{
		
		JSONView view = new JSONView();
		Map<String, Object> resMap = bindParamToMap(request);
		logger.info("文件参数->"+resMap);
		
		List<String> uploadKey = Arrays.asList(((String)resMap.get("realPath")).split(","));
		view = OSSUtil.uploadFile(uploadKey);
		
		// 此处上传文件至OSS
		logger.info("文件路径->"+view);
		
		return view;
	}
	
	/**
	 * 清楚oss中的文件
	 */
	@RequestMapping("/deleteImageToOSS.do")
	@ResponseBody
	public JSONView deleteImageToOSS(HttpServletRequest request, ModelMap model) throws Exception{
		
		JSONView view = new JSONView();
		Map<String, Object> resMap = bindParamToMap(request);
		logger.info("文件参数->"+resMap);
		
		List<String> uploadKey = Arrays.asList(((String)resMap.get("oldPath")).split(","));
		view = OSSUtil.deleteFile(uploadKey);
		
		// 此处删除文件至OSS
		logger.info("文件路径->"+view);
		
		return view;
	}
}
