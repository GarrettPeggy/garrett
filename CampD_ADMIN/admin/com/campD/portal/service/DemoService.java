package com.campD.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

@Service("demoService")
public class DemoService extends JsonClientService{

	@SuppressWarnings("rawtypes")
	public Map greeting() {
		
		return postForMap("http://localhost:8080/campD_server/demo/greeting", null);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map register(Map reqMap) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/add", reqMap);
    }
       
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public HSSFWorkbook export() {    

        String[] excelHeader = { "Sno", "Name", "Age"}; 
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("test");
        sheet.setDefaultRowHeight((short)300); 
        sheet.setDefaultColumnWidth(15);
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }    
    
        List<Map> list = new ArrayList();
        list.add(getMapValue("1000","zhangsan","20"));    
        list.add(getMapValue("1001","lisi","23"));    
        list.add(getMapValue("1002","wangwu","25"));
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            Map student = list.get(i);    
            row.createCell(0).setCellValue((String)student.get("Sno"));    
            row.createCell(1).setCellValue((String)student.get("Name"));    
            row.createCell(2).setCellValue((String)student.get("Age"));    
        }    
        return wb;    
    } 
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getMapValue(String Sno, String Name, String Age){
    	Map map = new HashMap();
    	map.put("Sno", Sno);
    	map.put("Name", Name);
    	map.put("Age", Age);
    	return map;
    }
}
