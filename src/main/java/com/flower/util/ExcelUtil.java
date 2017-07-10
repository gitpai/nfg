package com.flower.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.flower.dao.FlowerDao;
import com.flower.dao.impl.FlowerDaoImpl;
import com.flower.models.Flower;

public class ExcelUtil {
	
	public static void analysisXlsx(String path){
		XSSFWorkbook xssfWorkbook=null;
		try {
			//InputStream is = new FileInputStream("e://flower/106.xlsx");
			InputStream is = new FileInputStream(path);
			xssfWorkbook = new XSSFWorkbook(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    XSSFCell one = xssfRow.getCell(0);
                   
                    //读取第一列数据
                    XSSFCell two = xssfRow.getCell(1);
                    //读取第二列数据
                    XSSFCell three = xssfRow.getCell(2);
                    System.out.println(getValueXls(three));
                    Flower flower=new Flower();
                    FlowerDao dao=new FlowerDaoImpl();
                    flower.setFlowerName(getValueXls(three));                   
                    dao.addFlower(flower);
                    //读取第三列数据

                }
            }
        }
        //转换数据格式
	}
	public static void analysisXls(String path){
		HSSFWorkbook hssfWorkbook=null;
		try {
			//InputStream is = new FileInputStream("e://flower/106.xlsx");
			InputStream is = new FileInputStream(path);
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    HSSFCell one = hssfRow.getCell(0);
                    //读取第一列数据
                    HSSFCell two = hssfRow.getCell(1);
                    //读取第二列数据
                    HSSFCell three = hssfRow.getCell(2);
                    //读取第三列数据
                }
            }
        }
	}
	public static String getValueXls(XSSFCell xssfRow) {

	        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(xssfRow.getBooleanCellValue());
	        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	            return String.valueOf(xssfRow.getNumericCellValue());
	        } else {
	            return String.valueOf(xssfRow.getStringCellValue());
	        }
	    }
	public static  String getValuexlsx(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
