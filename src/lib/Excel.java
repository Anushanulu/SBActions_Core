package lib;




import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {


	public static int getRowCount(String xlpath,String sheet)
	{
		int rc=1;
		try{
			System.out.println(sheet+""+xlpath);


			FileInputStream fis=new FileInputStream(xlpath);

			Workbook wb= new HSSFWorkbook(fis);
					//WorkbookFactory.create(fis);


			rc=wb.getSheet(sheet).getLastRowNum();

		}
		catch(Exception e)
		{

		}
		return rc;
	}

	public static String getCellValue(String xlpath,String sheet, int row, int cell)
	{
	String value="";
	try{
		FileInputStream fis=new FileInputStream(new File(xlpath));
		HSSFWorkbook wb = new HSSFWorkbook(fis);

		value = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
	}
	catch(Exception e)	//getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();

	{

	}
	return value;
	}
	public static void setCellValue(String xlpath,String sheet1, int row, int cell1, String textTobeSet) 
	{
		try{
		FileInputStream file = new FileInputStream(new File(xlpath));

		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(sheet1);
		//Cell cell = null;

		Row row1 = sheet.getRow(row);
		//Update the value of cell
        Cell cell = row1.createCell(cell1);
		//cell = sheet.getRow(row).getCell(cell1);
		System.out.println("isha"+textTobeSet);
		cell.setCellValue(textTobeSet);

		file.close();

		FileOutputStream outFile =new FileOutputStream(new File(xlpath));
		workbook.write(outFile);
		outFile.close();}
				catch(IOException e){
				}	
	}
}                    