package com.campD.portal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.campD.portal.model.UserInfo;

/**
 * 上传文件工具类,包括本地的上传和远程上传
 * 
 * @author wangguanghua
 */
public class UploadFileUtil {
    
    public static final Logger log = Logger.getLogger(UploadFileUtil.class);
    
    private static final SimpleDateFormat DATE_DIR_SDF = new SimpleDateFormat("yyyyMMdd");
    
    private static final String DATE_FORMAT = "HHmmssSSS";
    
    /**
     * 通过绝对路径得到相对路径
     * @param absolutePath
     * @param request
     * @return
     */
    public static String getRelativePath(String absolutePath, HttpServletRequest request) {
        if (absolutePath == null || absolutePath.length() == 0) {
            return null;
        }
        String realBasePath = request.getSession().getServletContext().getRealPath("/");
        return absolutePath.replace(realBasePath, File.separator);
    }
    
    /**
     * 通过相对路径得到绝对路径
     * @param relativePath
     * @param request
     * @return
     */
    public static String getAbsolutePath(String relativePath, HttpServletRequest request) {
        if (relativePath == null || relativePath.length() == 0) {
            return null;
        }
        return request.getSession().getServletContext().getRealPath(relativePath);
    }
    /**
     * 得到上传文件的格式化相对路径
     * @return 返回相对路径
     */
    public static String getUploadFileFmtPath(String functionName, String fileName, UserInfo userInfo, HttpServletRequest request) {
        Date date = new Date();
        String uploadDirPath = MessageFormat.format(SystemMessage.getString("extPathFmt"), functionName, DATE_DIR_SDF.format(date));
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            fileName = DateUtil.fmtDate(date, DATE_FORMAT) + "_" + userInfo.getId() + "_" + userInfo.getMdn() + fileName.substring(index);
        }
        // 创建目录
        File uploadDir = new File(request.getSession().getServletContext().getRealPath(uploadDirPath));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String uploadFilePath = uploadDirPath + File.separator + fileName;
        return uploadFilePath;
    }
    
    /**
     * 得到一个临时文件绝对路径
     * @param fileName 文件名 如 a.txt
     * @param request
     * @return
     */
    public static String getTmpRealPath(String fileName, UserInfo userInfo, HttpServletRequest request) {
        Date date = new Date();
        String tempFilePath = SystemMessage.getString("tempFilePath");
        tempFilePath = MessageFormat.format(tempFilePath, DATE_DIR_SDF.format(date));
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            fileName = DateUtil.fmtDate(new Date(), DATE_FORMAT) + "_" + userInfo.getId() + fileName.substring(index);
        }
        // 创建目录
        File uploadDir = new File(request.getSession().getServletContext().getRealPath(tempFilePath));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir + File.separator + fileName;
    }
    
    
    public static String writeByteToFile(byte[] imageBytes, String desRealPath) throws Exception {
        if (imageBytes != null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File(desRealPath));
                fileOutputStream.write(imageBytes);
                fileOutputStream.flush();
            } catch (Exception e) {
                throw e;
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 格式是否正确 并且文件名字是否存在一个"."以上
     * @param fileName  文件的名字
     * @param trueFormat 正确的格式
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static boolean isAvaliableFileFmt(String fileName,String trueFormat){
    	int appointStrNum=StringUtil.getSpecifyStrCountInStr(fileName, ".");
    	if(!StringUtil.isEmpty(fileName) && appointStrNum==1 && !StringUtil.isDigital(trueFormat)){//文件名不为空并且存在一个“.”
    		String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//文件后缀
    		List allowedFmt=Arrays.asList(trueFormat.split(","));
    		if(!StringUtil.isEmpty(fileType.toLowerCase())&&allowedFmt.contains(fileType.toLowerCase())){//文件后缀都转换为小写
    			return true;
    		}
    	}
    	return false;
    }
}
