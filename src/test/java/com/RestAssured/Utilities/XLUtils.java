package com.RestAssured.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wo;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	//GetRowcount
	public static int getRowCount(String xlfile,String xlsheet) throws IOException {
		
		fi=new FileInputStream(xlfile);
		wo=new XSSFWorkbook(fi);
		ws=wo.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wo.close();
		fi.close();		
		return rowcount;
	}
	
	//CellRowcount
		public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException {
			
			fi=new FileInputStream(xlfile);
			wo=new XSSFWorkbook(fi);
			ws=wo.getSheet(xlsheet);
			row =ws.getRow(rownum);
			int cellcount=row.getLastCellNum();
			
			wo.close();
			fi.close();		
			return cellcount;
		}
		
		//GetCellData
		public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException {
			
			fi=new FileInputStream(xlfile);
			wo=new XSSFWorkbook(fi);
			ws=wo.getSheet(xlsheet);
			row =ws.getRow(rownum);
			cell=row.getCell(colnum);
			
			String data = null;
			try {
				DataFormatter formatter = new DataFormatter();
				String Celldata = formatter.formatCellValue(cell);
				return Celldata;
			}					
			catch(Exception e) {
				data="";
			}					
			wo.close();
			fi.close();		
			return data;
		}
		
		//Set cell data
		public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException {
			
			fi=new FileInputStream(xlfile);
			wo=new XSSFWorkbook(fi);
			ws=wo.getSheet(xlsheet);
			row =ws.getRow(rownum);
			cell=row.createCell(colnum);
			cell.setCellValue(data);	
			fo=new FileOutputStream(xlfile);
			wo.write(fo);
			wo.close();
			fi.close();	
			fo.close();			
		}
}
