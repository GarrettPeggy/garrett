package com.campD.portal.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

public class FileUtils {
	
	private final static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * 写文件
	 * @param content:写入文件的内容
	 * 		tarPath：目标路径
	 * @return
	 */
	public static void writerFile(String content, String tarPath) {
		BufferedWriter fw = null;
		try {
			File file = new File(tarPath);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(content);
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			logger.error("写入文件失败：" + e.getMessage());
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					logger.error("流关闭失败：" + e.getMessage());
				}
			}
		}
	}

	// 读本地文件内容
	public static String readFile(String filePath) {
		BufferedReader reader = null;
		String returnStr = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			String str = null;
			while ((str = reader.readLine()) != null) {
				returnStr += str;
				logger.info(str);
			}
			
		} catch (FileNotFoundException e) {
			logger.error("文件没有找到异常：" + e.getMessage());
		} catch (IOException e) {
			logger.error("io异常：" + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("文件关闭异常：" + e.getMessage());
			}
		}
		return returnStr;
	}
	
	/*
	 * 获取所指向url的输出流(指所有内容)
	 * 
	 * @urlString url路径 如:http://www.baidu.com返回的String 则为html代码
	 */
	public static String readRemoteFile(String urlString) {
		try {
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString); // 根据 String 表示形式创建
															// URL 对象。
			HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();// 返回一个 URLConnection 对象，它表示到 URL
										// 所引用的远程对象的连接。
			java.io.InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream(),"UTF-8");// 返回从此打开的连接读取的输入流。
			java.io.BufferedReader br = new java.io.BufferedReader(isr);// 创建一个使用默认大小输入缓冲区的缓冲字符输入流。

			String temp;
			while ((temp = br.readLine()) != null) { // 按行读取输出流
				if (!temp.trim().equals("")) {
					html.append(temp).append("\n"); // 读完每行后换行
				}
			}
			br.close(); // 关闭
			isr.close(); // 关闭
			return html.toString(); // 返回此序列中数据的字符串表示形式。
		} catch (Exception e) {
			logger.error("读取远程资源文件异常" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 判断源文件是否存在且是否为文件
	 * @param sourceFile
	 * @return
	 */
	public static boolean isExistsSource(String sourceFile) {
		File srcFile = new File(sourceFile);
		if (!srcFile.exists()) {
			logger.info("复制文件失败：源文件" + sourceFile + "不存在!");
			return false;
		} else if (!srcFile.isFile()) {
			logger.info("复制文件失败：" + sourceFile + "不是一个文件!");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 判断目标文件所在目录是否存在
	 * @param targetFile
	 * @return
	 */
	public static boolean isExistsTargetDir(String targetFile) {
		File destFile = new File(targetFile);
		
		if (!destFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建目录
			logger.info("目标文件所在的目录不存在：" + destFile.getParentFile());
			//创建目录
			destFile.getParentFile().mkdirs();
			return false;
		}
		
		return true;
	}
	/**
	 * 判断该文件是否存在
	 * @param fileName - 文件名称
	 * @param path - 目录
	 * @return -  是否存在
	 */
	public static boolean isFileExist(String fileName, String path){
		File file = new File(getDoPath(path)+fileName);
		return file.exists();
	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFileName
	 *            待复制的文件名
	 * @param destFileName
	 *            目标文件名
	 * @return 如果复制成功，则返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String destFileName) {
		//源文件是否存在
		isExistsSource(srcFileName);
		//目标文件所在目录是否存在
		isExistsTargetDir(destFileName);
		
		// 准备复制文件
		int byteread = 0;// 读取的位数
		InputStream in = null;
		OutputStream out = null;
		File srcFile = new File(srcFileName);
		File destFile = new File(destFileName);
		try {
			// 打开原文件
			in = new FileInputStream(srcFile);
			// 打开连接到目标文件的输出流
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			// 一次读取1024个字节，当byteread为-1时表示文件已经读完
			while ((byteread = in.read(buffer)) != -1) {
				// 将读取的字节写入输出流
				out.write(buffer, 0, byteread);
			}
			logger.info("复制文件" + srcFileName + "至" + destFileName + "成功!");
			
			return true;
		} catch (Exception e) {
			logger.error("复制文件失败：" + e.getMessage());
			return false;
		} finally {
			// 关闭输入输出流，注意先关闭输出流，再关闭输入流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("复制文件关闭流时出错:" + e.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("复制文件关闭流时出错:" + e.getMessage());
				}
			}
		}
	}
	

	//==============================================================================
	//
	//删除文件夹
	//		此为调用java基础类，利用递归实现方式
	//		除此之外用apache io包FileUtils也很方便，以下为apache io包实现方式：
	//			org.apache.commons.io.FileUtils.deleteDirectory(f);//删除目录 
	//			org.apache.tools.ant.util.FileUtils.delete(f);//删除文件
	//
	//==============================================================================
	/**
	 * 删除文件夹：文件夹下的所有文件
	 * @param filepath 格式形如：d://1//2/3//4
	 * 					即为删除4这个文件夹
	 * @return
	 */
	public static boolean removeFile(String filepath) {
		try {
			File f = new File(filepath);
			if(f.exists() && f.isDirectory()) {  //判断是文件还是目录
				if(f.listFiles().length == 0) {  //若目录下没有文件则直接删除
					f.delete();
				} else {  //若有则把文件放进数组，并判断是否有下级目录
					File childFile[]=f.listFiles();
					int i = f.listFiles().length;
					for(int j = 0; j < i; j++) {
						if(childFile[j].isDirectory()) {
							//递归调用del方法并取得子目录路径
							removeFile(childFile[j].getAbsolutePath());  
						}
						childFile[j].delete();  //删除文件
					}
					//删除文件夹下的子文件后再删除主文件夹
					f.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除文件出错!");
			return false;
		}
		
		return true;
	}
	/**
	 * 传递一个路径和文件名称，删除该文件
	 * @param fileName - 文件名称
	 * @param path - 路径
	 * @return - 是否删除成功
	 */
	public static boolean removeFile(String fileName, String path){
		boolean flag = false;
		if (isFileExist(fileName, path)) {
			File file = new File(getDoPath(path)+fileName);
			flag = file.delete();
		}
		return flag;
	}
	/**
	 * 删除当前文件
	 * @param file - 要删除的文件
	 * @return 是否删除成功
	 */
	public static boolean removeFile(File file){
		 boolean flag = false;
	        if (file != null && file.exists()) {
	            flag = file.delete();
	        }
	        return flag;
	}
	/**
	 * 传入一个文件名，得到这个文件名称的后缀  包含 .
	 * @param fileName - 文件名
	 * @return
	 */
	public static String getSuffixContainPoint(String fileName){
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			String suffix = fileName.substring(index);//后缀
			return suffix;
		}else {
			return null;
		}
	}
	/**
	 * 传入一个文件名，得到这个文件名称的后缀    不包含 .
	 * @param fileName - 文件名
	 * @return
	 */
	public static String getSuffixUnContainPoint(String fileName){
		 int index = fileName.lastIndexOf(".");
	        if (index != -1) {
	            String suffix = fileName.substring(index + 1);//后缀
	            return suffix; 
	        } else {
	            return null;
	        }
	}
	/**
	 * 利用uuid产生一个随机的name
	 * @param fileName 文件名
	 * @return
	 */
	public static String getRandomName(String fileName){
		 String randomName = UUID.randomUUID().toString();
		 //如果文件没有后缀名  默认就是给添加 后缀   txt
		 return getNewFileName(fileName, randomName , "txt");
	}
	/**
	 *  用当前日期、时间和1000以内的随机数组合成的文件名称
	 * @param fileName - 文件名
	 * @return
	 */
	public static String getNumberFileName(String fileName){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		int rand = new Random().nextInt(1000);
		String numberFileName = format.format(new Date()) + rand;
		return getNewFileName(fileName, numberFileName, "txt");
	}
	
	/**
	 * 传递一个文件名称和一个新名称，组合成一个新的带后缀文件名
	 * 当传递的文件名没有后缀，会添加默认的后缀
	 * @param fileName - 文件名称
	 * @param newFileName - 新文件名称
	 * @param nullSuffix - 为没有后缀的文件所添加的后缀 如果没有   可传递一个默认的后缀名 eq .txt
	 * @return
	 */
	public static String getNewFileName(String fileName,String newFileName,String nullSuffix){
		String suffix = getSuffixContainPoint(fileName);
		if (suffix != null) {
			newFileName += suffix;
		}else {
			newFileName = newFileName.concat(".").concat(nullSuffix);
		}
		return newFileName;
	}
	
	
	
	/**
	 * 
	 * @param pathName
	 * @return
	 */
	public static boolean isBlankForPath(String pathName) {
		boolean flag = false;
		String[] letters = new String[] { "/", "\\", ":", "*", "?", "\"", " <",
				">", " ¦", "%20", "#", "$", "%" };
		for (int i = 0; i < letters.length; i++) {
			if (pathName.indexOf(letters[i]) != -1) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;

	}
	/**
	 * 处理后的系统文件路径
	 * @param path
	 * @return
	 */
	public static String getDoPath(String path){
		 path = path.replace("\\", "/");
		 String lastChar = path.substring(path.length() - 1);
		 if (!"/".equals(lastChar)) {
	            path += "/";
	     }
		 return path;
	}
	/**
	 * 创建指定的path路径目录
	 * @param path - 目录、路径
	 * @return
	 */
	public static boolean mkDir(String path){
		File file = null;
		try {   
			file = new File(path);
			if (!file.exists()) {   
                return file.mkdirs();   
            }   
		}catch (Exception e) {
			logger.error("创建文件失败..错误如下：");
			e.printStackTrace();
		}finally{
			 file = null;   
		}
		return false;   
	}
	
	public static void main(String[] args){
		String htmlContent = readRemoteFile("http://camp-images.oss-cn-shanghai.aliyuncs.com//attached/html/20151116/20151116193359.txt");
		System.out.println(htmlContent);
		
		System.out.println("http://camp-images.oss-cn-shanghai.aliyuncs.com//attached/html/20151116/20151116193359.txt".indexOf("attached/html"));
		System.out.println("http://camp-images.oss-cn-shanghai.aliyuncs.com//attached/html/20151116/20151116193359.txt".indexOf("attach/html"));
	}
	
}
