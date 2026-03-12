package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;

    String path;

    public ExcelUtility(String path)
    {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        String data;

        try
        {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }

        wb.close();
        fi.close();
        return data;
    }
    
    public String setCellData(String sheetName,int rownum,int colnum, String data) throws IOException
    {
    	File xlfile = new File(path);
    	if(!xlfile.exists())  //if file not exist then create new file
    	{
    		wb = new XSSFWorkbook();
    		fo = new FileOutputStream(path);
            wb.write(fo);

    	}
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        
        if(wb.getSheetIndex(sheetName)==-1) //if sheet not exist then create new sheet
        wb.createSheet(sheetName);
        sheet = wb.getSheet(sheetName);
        
        if(sheet.getRow(rownum)==null)//if row not exist then create new row
        sheet.createRow(rownum);
        row = sheet.getRow(rownum);
        
        cell = row.createCell(colnum);
        cell.setCellValue(data);
		fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
        return data;
    }

    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        FileOutputStream fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }
    
    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        FileOutputStream fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }
}