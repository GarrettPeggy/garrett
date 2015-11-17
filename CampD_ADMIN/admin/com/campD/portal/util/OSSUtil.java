package com.campD.portal.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.terracotta.agent.repkg.de.schlichtherle.io.File;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.campD.portal.common.JSONView;

/**
 * OSS上传图片
 * @author qrh
 *
 */
public class OSSUtil {
	
	protected static Logger logger = Logger.getLogger(OSSUtil.class);
	
	// 本地图片测试配置
//	public static final String PIC_BUCKET="camp-image";//OSS的命名空间
//	public static final String ACCESS_ID="EKNuX4gneNZoqNt1";
//	public static final String ACCESS_KEY="Gt2PB5uRaSHumEms5wKM0vUWqn3gfY";
	
	// 现网图片测试配置
	public static final String ACCESS_ID="sn0Bqqd3dZ68C2tq";//服务器环境
	public static final String ACCESS_KEY="WSik5OB9sqfWREA6xRzgkGarE61Ewj";//服务器环境
	public static final String PIC_BUCKET=SystemMessage.getString("ossBucket");//OSS的命名空间

	public static final String OSS_ENDPOINT="http://oss-cn-shanghai.aliyuncs.com";//上海节点外网地址
	private static OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
	
	/**
	 * 单个上传文件，这里主要是针对一些富文本的处理
	 * @return
	 */
	public static JSONView uploadFile(String path){
		JSONView jsonView = new JSONView();
		File file=new File(path);
		//F:/wang/hua/images/20150913/1.jpg
		int index = path.lastIndexOf("attached");
		String key = path.substring(index).replace("\\", "/");
		
		//String key = "images/" + file.getName();//整个文件对象,在oss上的路径加上文件名
		logger.info("文件上传的key====="+key);
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		try {
			InputStream input = new FileInputStream(file);
			PutObjectResult result = client.putObject(PIC_BUCKET, key, input, objectMeta);
			if(null!=result){
				jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
				jsonView.setReturnMsg("文件上传成功");
				return jsonView;
			}else{
				jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
				jsonView.setReturnMsg("文件上传失败");
				return jsonView;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("单个文件上传的方法uploadFile执行出错");
			return jsonView;
		}
	}
	/**
	 * 多个文件上传，这里主要是针对一些富文本的处理
	 * @param pathList
	 * @return
	 */
	public static JSONView uploadFile(List<String> pathList){
		JSONView jsonView = new JSONView();
		try {
			if(null != pathList && pathList.size() > 0){
				int size=pathList.size();
				int flag=0;//判断文件是否上传成功的标志位
				for(int i=0; i < size; i++){
					logger.info("多个文件上传第 "+i+"个");
					JSONView uploadMap = uploadFile(pathList.get(i));
					if(JSONView.RETURN_FAIL_CODE.equals(uploadMap.getReturnCode())){
						flag=1;
						break;
					}
				}
				if(flag==0){
					jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
					jsonView.setReturnMsg("文件上传成功");
					return jsonView;
				}else{
					jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
					jsonView.setReturnMsg("文件上传失败");
					return jsonView;
				}
			}else{
				jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
				jsonView.setReturnMsg("文件上传的列表为空");
				return jsonView;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("多个文件上传的方法uploadFile执行出错");
			return jsonView;
		}
	}
	
	/**
	 * 上传单张图片
	 * @return
	 */
	public static JSONView uploadImage(String path){
		JSONView jsonView = new JSONView();
		File file=new File(path);
		//F:/wang/hua/images/20150913/1.jpg
		int index = path.lastIndexOf("images");
		String key = path.substring(index).replace("\\", "/");
		
		//String key = "images/" + file.getName();//整个文件对象,在oss上的路径加上文件名
		logger.info("文件上传的key====="+key);
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		try {
			InputStream input = new FileInputStream(file);
			PutObjectResult result = client.putObject(PIC_BUCKET, key, input, objectMeta);
			if(null!=result){
				jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
				jsonView.setReturnMsg("文件上传成功");
				return jsonView;
			}else{
				jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
				jsonView.setReturnMsg("文件上传失败");
				return jsonView;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("单个文件上传的方法uploadFile执行出错");
			return jsonView;
		}
	}
	/**
	 * 上传多张图片
	 * @param pathList
	 * @return
	 */
	public static JSONView uploadImage(List<String> pathList){
		JSONView jsonView = new JSONView();
		try {
			if(null != pathList && pathList.size() > 0){
				int size=pathList.size();
				int flag=0;//判断文件是否上传成功的标志位
				for(int i=0; i < size; i++){
					logger.info("多个文件上传第 "+i+"个");
					JSONView uploadMap = uploadImage(pathList.get(i));
					if(JSONView.RETURN_FAIL_CODE.equals(uploadMap.getReturnCode())){
						flag=1;
						break;
					}
				}
				if(flag==0){
					jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
					jsonView.setReturnMsg("文件上传成功");
					return jsonView;
				}else{
					jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
					jsonView.setReturnMsg("文件上传失败");
					return jsonView;
				}
			}else{
				jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
				jsonView.setReturnMsg("文件上传的列表为空");
				return jsonView;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("多个文件上传的方法uploadFile执行出错");
			return jsonView;
		}
	}
	
	/**
	 * 删除bucket里面的Object
	 * @param client
	 * @param bucketName
	 * @return
	 */
	public static JSONView deleteFile(String key){
		JSONView jsonView = new JSONView();
		try {
	        client.deleteObject(PIC_BUCKET, key);
	        logger.info("被删除文件的key为=================="+key);
	        logger.info("文件被删除时间为==================================="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
			jsonView.setReturnMsg("文件被删除成功");
			return jsonView;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("文件被删除失败,deleteFile执行出错");
			return jsonView;
		}
	}
	
	/**
	 * 删除bucket和里面的文件
	 * @param bucketName
	 * @return
	 */
	public static JSONView deleteFile(List<String> pathList){
		JSONView jsonView = new JSONView();
		try {
			if(null != pathList && pathList.size() > 0){
				int size=pathList.size();
				for(int i = 0; i < size; i++){
					deleteFile(pathList.get(i));
					logger.info("第 "+i+" 个文件被删除");
				}
				jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
				jsonView.setReturnMsg("文件被删除成功");
				return jsonView;
			}else{
				jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
				jsonView.setReturnMsg("文件列表为空");
				return jsonView;
			}
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("文件被删除失败,deleteFile批量删除执行出错");
			return jsonView;
		}
	}
	
}
