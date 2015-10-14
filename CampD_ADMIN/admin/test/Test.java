package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

public class Test {
	
	public static final String ACCESS_ID="EKNuX4gneNZoqNt1";
	public static final String ACCESS_KEY="Gt2PB5uRaSHumEms5wKM0vUWqn3gfY";
	public static final String OSS_ENDPOINT="http://oss-cn-shanghai.aliyuncs.com";//上海节点外网地址
	public static final String PIC_BUKET="camp-image";
	public static OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgFileId = formatter.format(new Date()) + new Random().nextInt();
		File imgFile=new File("http://192.168.1.111:8080/campD_admin/ext/20151005/191241521_018ffd2c-adab-4131-8ef6-af00650ccee2.jpg");
		//String key = "images111222/";
		ObjectMetadata objectMeta = new ObjectMetadata();
		
		String key = "images111222/" + imgFile.getName();
		
		System.out.println("imgFile.getName()==============="+imgFile.getName());
		
        objectMeta.setContentLength(imgFile.length());
        System.out.println("imgFile.length()============="+imgFile.length());
        // 可以在metadata中标记文件类型
        //objectMeta.setContentType("image/jpeg");
        InputStream input=null;
		try {
			input = new FileInputStream(imgFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("input======================="+input);
        PutObjectResult result = client.putObject(PIC_BUKET, key, input, objectMeta);
        System.out.println(result.getETag());
		*/
		// 删除Object
//	    client.deleteObject(PIC_BUKET, "images111222/Chrysanthemum.jpg");
//	    System.out.println("删除成功!!!!!");
	      
	    ObjectListing ObjectListing = client.listObjects(PIC_BUKET);
        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            System.out.println("删除文件的key为============"+objectName);
            System.out.println("文件被删除时间为==================================="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            // 如果不为空，先删除bucket下的文件
            client.deleteObject(PIC_BUKET, objectName);
        }
	    
	}

}
