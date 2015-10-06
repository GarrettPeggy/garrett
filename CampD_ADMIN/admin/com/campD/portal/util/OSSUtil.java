package com.campD.portal.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.terracotta.agent.repkg.de.schlichtherle.io.File;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.campD.portal.common.JSONView;

/**
 * OSS上传图片
 * @author qrh
 *
 */
public class OSSUtil {
	
	public static final String ACCESS_ID="EKNuX4gneNZoqNt1";
	public static final String ACCESS_KEY="Gt2PB5uRaSHumEms5wKM0vUWqn3gfY";
	public static final String OSS_ENDPOINT="http://oss-cn-shanghai.aliyuncs.com";//上海节点外网地址
	public static final String PIC_BUCKET="camp-image";//OSS的命名空间
	private static OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
	
	/**
	 * 单个上传文件
	 * @return
	 */
	public static Map uploadFile(String path){
		JSONView jsonView = new JSONView();
		File file=new File(path);
		String key = "images/" + file.getName();//整个文件对象,在oss上的路径加上文件名
		System.out.println("单个文件上传的key====="+key);
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
	 * 多个文件上传
	 * @param pathList
	 * @return
	 */
	public Map uploadFile(List<String> pathList){
		JSONView jsonView = new JSONView();
		try {
			if(null != pathList && pathList.size() > 0){
				int size=pathList.size();
				int flag=0;//判断文件是否上传成功的标志位
				for(int i=0; i < size; i++){
					File file=new File(pathList.get(i));
					String key = "images/" + file.getName();//这个文件名要修改
					System.out.println("多个文件上传第 "+i+" 个key====="+key);
					ObjectMetadata objectMeta = new ObjectMetadata();
					objectMeta.setContentLength(file.length());
					InputStream input = new FileInputStream(file);
					PutObjectResult result = client.putObject(PIC_BUCKET, key, input, objectMeta);
					if(null==result){
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
	public static Map deleteFile(String bucketName){
		JSONView jsonView = new JSONView();
		try {
			ObjectListing ObjectListing = client.listObjects(bucketName);
	        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
	        for (int i = 0; i < listDeletes.size(); i++) {
	            String objectName = listDeletes.get(i).getKey();
	            // 如果不为空，先删除bucket下的文件
	            client.deleteObject(bucketName, objectName);
	        }
	        System.out.println("文件被删除时间为==================================="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
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
	public static Map deleteBucketAndFile(String bucketName){
		JSONView jsonView = new JSONView();
		try {
			ObjectListing ObjectListing = client.listObjects(bucketName);
	        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
	        for (int i = 0; i < listDeletes.size(); i++) {
	            String objectName = listDeletes.get(i).getKey();
	            // 如果不为空，先删除bucket下的文件
	            client.deleteObject(bucketName, objectName);
	        }
	        client.deleteBucket(bucketName);
	        System.out.println("Bucket和文件被删除时间为==================================="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
			jsonView.setReturnMsg("Bucket和文件被删除成功");
			return jsonView;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonView.setReturnCode(JSONView.RETURN_FAIL_CODE);
			jsonView.setReturnMsg("Bucket和文件被删除失败,deleteBucketAndFile执行出错");
			return jsonView;
		}
	}
	
}
