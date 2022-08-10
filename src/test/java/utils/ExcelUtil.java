/**
 * 
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Mallesh
 *
 */
public class ExcelUtil 
{	
	public static int rows,cols;
	static XSSFWorkbook wb;
	static XSSFSheet sheet1;
	
	//This function opens an excel sheet and get the no. of rows and columns
	public static void ExcelOpen() throws Exception
	{
		File src = new File("src\\test\\resources\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheet1 = wb.getSheetAt(0);
		rows = sheet1.getPhysicalNumberOfRows();
		cols = sheet1.getRow(0).getPhysicalNumberOfCells();
		System.out.println("the no. of row and columns are - "+rows+":"+cols);
	}
	
	//This function reads the data from excel sheet at the specified row and column
	public static String ExcelRead(int row, int col)
	{
		return sheet1.getRow(row).getCell(col).getStringCellValue();
	}
	
	//This function writes the data into excel sheet at the specified row and column
	public static void ExcelWrite(int row, int col, String srcRes) throws Exception
	{
		FileOutputStream fos = new FileOutputStream("src\\test\\resources\\TestData.xlsx");
		sheet1.getRow(row).createCell(col).setCellValue(srcRes);
		wb.write(fos);
	}
	
	//This function closes the excel
	public static void ExcelQuit() throws Exception
	{
		wb.close();
	}
}
