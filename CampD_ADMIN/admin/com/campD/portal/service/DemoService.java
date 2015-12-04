package com.campD.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
 
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("学生表");
        sheet.setDefaultRowHeight((short)300); 
        sheet.setDefaultColumnWidth(30); 

        String[] excelHeader = { "上海行健学院学生学号", "上海行健学院学生姓名", "上海行健学院学生年龄"};
        // 生成表头部样式
        HSSFCellStyle headerStyle = getHeaderStyle(wb);
        // 构造表的头部
        buildTableHeader(sheet, excelHeader, headerStyle);
        
        List list = new ArrayList();
        list.add(getMapValue("0950420033-15800837196-076779","zhangsan","20"));    
        list.add(getMapValue("1001","lisi","23"));    
        list.add(getMapValue("1002","wangwu","25"));
        // 生成表内容样式
        HSSFCellStyle bodyStyle = getContentStyle(wb);
        // 构造表体
        buildTableBody(sheet, list, bodyStyle);
        
        return wb;    
    } 
    
    /**
     * 获取表头部样式
     * @param wb
     * @return
     */
    private HSSFCellStyle getHeaderStyle(HSSFWorkbook wb){
    	// 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        
        return style;
    }
    
    /**
     * 获取表内容样式
     * @param wb
     * @return
     */
    private HSSFCellStyle getContentStyle(HSSFWorkbook wb){
    	// 生成并设置另一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = wb.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font2); 
        
        return style;
    }
    
    /**
     * 构造表头
     * @param sheet
     * @param excelHeader
     * @param headerStyle
     */
    private void buildTableHeader(HSSFSheet sheet, String[] excelHeader, HSSFCellStyle headerStyle){
    	HSSFRow row = sheet.createRow((int) 0); 
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(headerStyle);    
            sheet.autoSizeColumn(i);    
        }
    }
    
    /**
     * 构造表体
     * @param sheet
     * @param excelHeader
     * @param headerStyle
     */
    private void buildTableBody(HSSFSheet sheet, List list, HSSFCellStyle bodyStyle){
    	HSSFRow row = null; 
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            Map student = (Map) list.get(i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue((String)student.get("Sno")); 
            cell0.setCellStyle(bodyStyle); 
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue((String)student.get("Name"));
            cell1.setCellStyle(bodyStyle); 
            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue((String)student.get("Age")); 
            cell2.setCellStyle(bodyStyle); 
        }
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
