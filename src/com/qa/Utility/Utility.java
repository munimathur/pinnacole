package com.qa.Utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility{

	public FileInputStream file;
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public void createNewWorkBook(String wbName, String sheetName, String sheetName1)</h1>
	 * <p>
	 * This function will create a workbook with two sheets
	 * @author Rocky
	 * @param wbName Path of XLSX file(including Excelsheet name with extension)
	 * @param sheetName1 Name of first sheet
	 * @param sheetName2 Name of second sheet
	 * <p><h1>Example:</h1> 
	 * 	<p>String outputFile = "D:\\Results\\Sample.xlsx";
	 * 	<p>createNewWorkBook(outputFile, "TestResultSummary", "TestResultDescription"); 
	 */	
	public void createNewWorkBook(String wbName, String sheetName1, String sheetName2) throws IOException, InvalidFormatException
	{
		//Create a new work book	
		XSSFWorkbook workbook = new XSSFWorkbook();
		//Add sheets
		workbook.createSheet(sheetName1);
		workbook.createSheet(sheetName2);
		//Write the sheet to a file system and save the sheet 
		FileOutputStream fileOut = new FileOutputStream(new File(wbName));
		//write the output stream to file
		workbook.write(fileOut);		
		//close the stream handle
		fileOut.close();

	}

	//--------------------------------************************************------------------------------------//
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static String getDateTime()</h1>
	 * <p>
	 * This function generates a unique appender with cuurent time
	 * @author Rachna
	 * @return String current time in ddmmyyy-hhmmss formate
	 * 
	 */	
	//
	public static String getDateTime()
	{
		String timeStamp = new SimpleDateFormat("ddMMYYY-HHmmss").format(Calendar.getInstance().getTime());

		return timeStamp;

	}
	//--------------------------------************************************-----------------------------------//
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public void addLabelToSummarySheet(String workbookPath)</h1>
	 * <p>
	 * This function adds the header row to the sheet at index '0'.
	 * <br><br>Labels are :<b><br>Test Case Id <br>Title  <br>Result</b>
	 * @author Rocky
	 * @param workbookPath Path of XLSX file(including Excelsheet name with extension)
	 * 
	 * <p><h1>Example:</h1> 
	 * 	<p>String workbookPath = "D:\\Results\\Sample.xlsx";
	 * 	<p>addLabelToSummarySheet(workbookPath); 
	 */
	public void addLabelToSummarySheet(String workbookPath) throws IOException 
	{
		//Initialize an input stream to open the workbook
		FileInputStream file	=	new FileInputStream(workbookPath);
		//Initialize the workbook
		XSSFWorkbook workbook 	=	new XSSFWorkbook(file);
		//GEt the sheet at inex 0
		XSSFSheet workSheet	=	workbook.getSheetAt(0);

		//Intialize the array of header elements
		String[] array	=	{"Test Case Id","Title","Result"};

		//create row 0
		XSSFRow row =workSheet.createRow(0);

		//Write the array elements
		for(int i=0;i<array.length;i++)
		{
			//create cell on i'th position
			XSSFCell cell = row.createCell(i);

			//Initialize object of font
			XSSFFont font = workbook.createFont();

			//Set the font to BOLD
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			//Set Font color
			font.setColor(IndexedColors.GREY_80_PERCENT.index);
			cell.setCellValue(array[i]);//Set the value to the set 
			CellStyle cellStyle = workbook.createCellStyle();//Create the object of CellStyle class
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
			cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
			cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
			cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
			cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setFont(font);

			//Apply the font and cell style to the cell
			((XSSFCell) cell).setCellStyle(cellStyle);
		}
		//Close the input file stream
		file.close();
		//Create an output stream for writing to file
		FileOutputStream outFile = new FileOutputStream(new File(workbookPath));
		//Flush the contents to a file
		workbook.write(outFile);
		//Close the output stream
		outFile.close();

	}
	//--------------------------------************************************-----------------------------------//
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public void addLabelToDescriptionSheet(String workbookPath)</h1>
	 * <p>
	 * This function adds header row to the sheet at index '1'.
	 * <br><br>Labels are:<b><br>Test Case Id<br>Test Script Name<br>Test Step <br>Expected output <br>Actual output <br>Test Step Result <br>Test Data <br>Output <br>Link</b>
	 * @author Rocky
	 * @param workbookPath Path of XLSX file(including Excelsheet name with extension)
	 * 
	 * <p><h1>Example:</h1> 
	 * 	<p>String outputFile = "D:\\Results\\Sample.xlsx";
	 * 	<p>addLabelToDescriptionSheet(outputFile); 
	 */	
	public void addLabelToDescriptionSheet(String workbookPath) throws IOException 
	{
		//Initialize the file input stream to open the workbook
		FileInputStream file	=	new FileInputStream(workbookPath);

		//Points the workbook object to existing workbook
		XSSFWorkbook workbook 	=	new XSSFWorkbook(file);

		//Get the sheet at ondex 1
		XSSFSheet workSheet	=	workbook.getSheetAt(1);

		String[] array	=	{"Type","Utterances/User Examples","Expected output","Actual output","Status(For Question)","Link 1","Link 2","Status(For Link)","Screenshot Link"};

		XSSFRow row =workSheet.createRow(0);
		//Write the header elements
		for(int i=0;i<array.length;i++)
		{
			XSSFCell cell = row.createCell(i);
			XSSFFont font = workbook.createFont();

			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setColor(IndexedColors.GREY_80_PERCENT.index);
			cell.setCellValue(array[i]);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
			cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
			cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
			cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
			cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setFont(font);

			//Apply the font and cell style to the cell
			((XSSFCell) cell).setCellStyle(cellStyle);
		}
		//Close the input file stream
		file.close();
		//Create an output stream for writing to file
		FileOutputStream outFile = new FileOutputStream(new File(workbookPath));
		//Flush the contents to a file
		workbook.write(outFile);
		//Close the output stream
		outFile.close();

	}
	//--------------------------------************************************-----------------------------------//
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static void addCellData(String wbName, String sheetName, String [] dataArray)</h1>
	 * <p>
	 * This function adds the array elements into the sheet at last row, in case of result summary sheet the data is overwritten at 1st row
	 * @author Rocky
	 * @param wbName Path of XLSX file(including Excelsheet name with extension)
	 * @param sheetName name of the sheet to which the data is to be written
	 * @param dataArray String array containing the result
	 * <p><h1>Example:</h1> 
	 * 	<br>String wbName = "D:\\Results\\Sample.xlsx";
	 * 	<br>String sheetName	=	"TestResultDescription";
	 * 	<br>String[] resArray = {"TC00","LogIn Script","Log In","Log In successful","Logged In","Pass","UserName:xxx;Password:***",NA","Yes"};
	 *	<br>addCellData(wbName, sheetName,resArray);
	 */	

	public static void addCellData(String wbName, String sheetName, String [] dataArray) throws IOException, URISyntaxException
	{
		//Create an input stream for file reading
		FileInputStream file = new FileInputStream(new File(wbName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		//Get the required sheet from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);


		//Get the last of the sheet
		int rowIndex	=	sheet.getLastRowNum();
		rowIndex	=	rowIndex+1;//Row index is first empty row of the sheet
		//In case of result summary sheet, we need to overwrite the first row itself
		if(sheet.getSheetName().equals(workbook.getSheetAt(0).getSheetName()))
			rowIndex	=	1;

		//Create a row
		XSSFRow row = sheet.createRow(rowIndex);

		//Input all the data in the row column wise
		for(int i=0; i<(dataArray.length);i++)
		{
			//Create cell
			XSSFCell cell = row.createCell(i);
			XSSFFont font = workbook.createFont();	
			//Format the font
			font.setBoldweight(XSSFFont.DEFAULT_FONT_SIZE);
			font.setFontName("Courier New");

			//Create helper class object for hyperlink
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			//Create the hyperLink object
			XSSFHyperlink file_link=createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
			//if the status is pass, a green PASS value is set in the cell
			if(dataArray[i].toString().equalsIgnoreCase("PASS"))
			{
				//set the text in the cell to green
				cell.setCellValue(dataArray[i]);
				sheet.autoSizeColumn(i);
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				font.setColor(HSSFColor.BRIGHT_GREEN.index);
				cellStyle.setFont(font);
				((XSSFCell) cell).setCellStyle(cellStyle);
			}

			//If the result is fail, set the cell value with a RED cell style
			else if(dataArray[i].toString().equalsIgnoreCase("FAIL"))
			{
				//set the text in the cell to red
				cell.setCellValue(dataArray[i]);
				sheet.autoSizeColumn(i);
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				font.setColor(HSSFColor.RED.index);

				cellStyle.setFont(font);
				((XSSFCell) cell).setCellStyle(cellStyle);
			}

			//If the result is PASS WITH ISSUE, set the cell value with a BLUE cell style
			else if(dataArray[i].toString().equalsIgnoreCase("PASS WITH ISSUE")){

				//set the text in the cell to blue
				cell.setCellValue(dataArray[i]);
				sheet.autoSizeColumn(i);
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				font.setColor(HSSFColor.BLUE.index);
				cellStyle.setFont(font);
				((XSSFCell) cell).setCellStyle(cellStyle);	
			}
			//If the form data ends with a .jpeg name,convert the cell into a link to the file
			else if(dataArray[i].toString().endsWith(".jpeg"))
			{   
				//Replace the slashes
				dataArray[i] = dataArray[i].replace("\\","/");
				//This is the link address
				file_link.setAddress(dataArray[i].toString());
				file_link.setTooltip("Click to open the screenshot");
				cell.setCellValue(dataArray[i].toString());//Link text
				cell.setHyperlink(file_link);
				sheet.autoSizeColumn(i);				
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_FILL);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				font.setUnderline(Font.U_SINGLE);
				font.setColor(IndexedColors.BLUE.getIndex());
				cellStyle.setFont(font);
				((XSSFCell) cell).setCellStyle(cellStyle);
			}
			//Else write data in a normal way defined by cell style
			else
			{
				cell.setCellValue(dataArray[i]);
				sheet.autoSizeColumn(i);
				font.setColor(IndexedColors.GREY_80_PERCENT.index);
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
				cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
				cellStyle.setFont(font);
				((XSSFCell) cell).setCellStyle(cellStyle);
			}
		}
		//Close the input file stream
		file.close();
		//Create an output stream for writing to file
		FileOutputStream outFile = new FileOutputStream(new File(wbName));
		//Flush the contents to a file
		workbook.write(outFile);
		//Close the output stream
		outFile.close();
	}
	//--------------------------------************************************-----------------------------------//
	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static boolean getTestResult(String workbookPath,String descriptionSheetName)</h1>
	 * <p>
	 * This function reads the Test Step Result column from testDescription sheet and return the final result boolean true or false
	 * @author Rocky
	 * @param workbookPath Path of XLSX file(including Excelsheet name with extension)
	 * @param descriptionSheetName name of the sheet from which the result column would be read 
	 * @return boolean true if all the Test Step Result column contains Pass or Pass with issue else returns false
	 */		

	public static boolean getTestResult(String workbookPath,String descriptionSheetName) throws IOException
	{
		FileInputStream file =  new FileInputStream(workbookPath);
		//Connect to the result sheet			
		XSSFWorkbook workbook = new XSSFWorkbook(file);//For Input data			
		//Initialize the worksheet,TC00 is for logIn details-InputDataSheet
		XSSFSheet descriptionSheet = workbook.getSheetAt(1);// Connect to description sheet

		//Get the last row number in the sheet
		int lastRowNum = descriptionSheet.getLastRowNum();

		//Check for all the steps resultS
		for(int i=1;i<=lastRowNum;i++)
		{
			XSSFRow resultRow= descriptionSheet.getRow(i);//Get the result row
			String data=getValueFromExcel(descriptionSheet, resultRow, "Test Step Result");//get the result
			if(!(data.equalsIgnoreCase("PASS")||data.equalsIgnoreCase("PASS WITH ISSUE")||data.isEmpty()||data.equals(null)))
			{
				//Returns false if encounter any value other than Pass or Pass with Issue
				return false;
			}

		}
		//Returns true if values found are not other that Pass or Pass with Issue
		return true;
	}
	//--------------------------------************************************-----------------------------------//

	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static String getValueFromExcel(XSSFSheet sheet, XSSFRow row, String label)</h1>
	 * <p>
	 * This function reads the data from excel
	 * @author Rocky
	 * @param sheet excel sheet name
	 * @param row row index from where the data is to be read
	 * @param label column name
	 *  
	 * @return String data read from particular cell, null if the data could not be read
	 */	
	public static String getValueFromExcel(XSSFSheet sheet, XSSFRow row, String label) throws IOException
	{

		System.out.println(sheet.toString());

		int dataColIndex;
		//Get the index of the column
		dataColIndex= getColumnIndex(sheet,label); 

		//Modified by ny895 BOLT Project
		try{
			XSSFCell cell = row.getCell(dataColIndex, XSSFRow.CREATE_NULL_AS_BLANK); 
			int cellType = cell.getCellType();
			String strCellValue = "";
			//System.out.println("Cell type"+cellType);
			//System.out.println(cellType);
			switch (cellType)
			{
			case 0: strCellValue	=	Integer.toString((int) cell.getNumericCellValue());
			break;
			case 1: strCellValue	=	cell.getStringCellValue();
			break;
			case 3: strCellValue	=	cell.getStringCellValue();
			break;
			case 4: strCellValue	=	Boolean.toString(cell.getBooleanCellValue());
			break;
			case 5: strCellValue	=	cell.getErrorCellString();
			break;
			case 6: strCellValue	=	cell.getDateCellValue().toGMTString();
			break;
			default: strCellValue	=	cell.getDateCellValue().toString();

			}

			//System.out.println(strCellValue);
			return strCellValue;
		}catch(NullPointerException e){return null;}

	}
	//--------------------------------************************************-----------------------------------//

	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static int getColumnIndex(XSSFSheet shtName, String searchCol)</h1>
	 * <p>
	 * This function return the index of the column 
	 * @author Rocky
	 * @param sheet excel sheet nameF
	 * @param searchCol name of the column whose index is needed  
	 * @return int index of the column if column found else -1
	 */	
	public static int getColumnIndex(XSSFSheet shtName, String searchCol)
	{
		//Get the row from the sheet in the workbook
		XSSFRow firstRow = shtName.getRow(0);
		int cellCounter = 0;
		while(firstRow.getCell(cellCounter) != null)
		{
			//Modified by ny895 - BOLT Project
			if(firstRow.getCell(cellCounter).toString().trim().equalsIgnoreCase(searchCol))
			{
				return cellCounter;
			}
			cellCounter++;
		}
		return -1;
	}
	//--------------------------------************************************-----------------------------------//

	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static void printScreen(String filename)</h1>
	 * <p>
	 * This function captures the screen shot of the desktop screen
	 * @author Rocky
	 * @param fileName name or location of file where the screenshot is to be stored
	 * 
	 */	
	public static void printScreen(String filename)
	{
		try
		{
			Robot robot = new Robot();			
			BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenshot,"JPG",new File(filename));
		}
		catch(Exception e)
		{
			System.err.println("Unhandled exception: " + e);
			e.printStackTrace();
		}
	}
	//--------------------------------************************************-----------------------------------//

	//--------------------------------************************************-----------------------------------//
	/**
	 * <h1>public static void logResult(File dir, String outputExcelFile, String [] resArray)</h1>
	 * <p>
	 * This function is used to log the result in the excel sheets
	 * @param dir The File input where the test result excel resides
	 * @param outputExcelFile the path of the excel file with its name and extension
	 * @param resArray String[] containing the log data cellwise to be entered in a row
	 * @author Rocky
	 * <p><h1>Example:</h1> 
	 * 	<br>String dir = "D:\\Results";
	 * 	<br>String sheetName	=	"D:\\Results\\Sample.xlsx";
	 * 	<br>String[] resArray = {"TC00","LogIn Script","Log In","Log In successful","Logged In","Pass","UserName:xxx;Password:***",NA","Yes"};
	 *	<br>logResult(dir, sheetName,resArray);
	 *@description : If the testResult step is Fail the screenshot would be captured and if the testStepResult is pass than depending on last entry in the array "Yes/No" the screenshot would be captured
	 *	
	 */	


	public static void logResult(File dir, String outputExcelFile, String [] resArray) throws Exception
	{

		//Captures image and writes the result into TestResult  excel

		String dirName = dir.getName();
		String [] appender = dirName.split("_");
		String resultImage;
		//Code changed 			
		//If the result is pass
		if(resArray[5].toString().equalsIgnoreCase("PASS"))
		{
			if(resArray[8].equalsIgnoreCase("Yes"))//If the flag is set to yes then print the screen shot
			{
				System.out.println("result is Pass");
				resultImage = dir.getPath()+"\\"+ getDateTime()+".jpeg";
				System.out.println("Result Image path :"+resultImage);

				String[] imageNameArray =	resultImage.split(dir.getName());
				String imageName = imageNameArray[imageNameArray.length-1];
				System.out.println("Image Name : "+imageName);
				printScreen(resultImage);

				resArray[8] = "..\\"+dir.getName()+"\\"+imageName;
			}
			else
				resArray[8] = "";

		}
		else//if the result is anything other than pass then print the screen shot
		{
			resultImage = dir.getPath()+"\\"+ getDateTime()+".jpeg";

			System.out.println(resultImage);
			String[] imageNameArray =	resultImage.split(dir.getName());
			String imageName = imageNameArray[imageNameArray.length-1];
			printScreen(resultImage);

			resArray[8] = "..\\"+dir.getName()+"\\"+imageName;


		}		
		addCellData(outputExcelFile, "TestResultDescription",resArray);
		

	}


	public XSSFSheet getWorkSheet(Object testCaseID,Object fileInpStream,Object workBook)
	{
		XSSFWorkbook workbook1= (XSSFWorkbook)workBook;
		String testCase_ID= testCaseID.toString();
		XSSFSheet worksheet1 = workbook1.getSheet(testCase_ID);
		return worksheet1;
	}


	/**
	 * @author kd171
	 * @name   addCellData_result
	 * @description This method writes test case result in description sheet
	 * @param  	wbName: Name or location of the work book
	 * 			Sheet: Name of the sheet where results are added
	 * 			rowIndex: Index of row in the sheet where the result is added
	 * 			testResult: Test result to be written. PASS or FAIL.
	 */	
	public  static void addCellData_result(String wbName, String Sheet, int rowIndex, String testResult) throws IOException
	{
		//Create an input stream for file reading
		FileInputStream file = new FileInputStream(new File(wbName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		//Get the required sheet from the workbook
		XSSFSheet sheet = workbook.getSheet(Sheet);
		//Get a row
		XSSFRow row = sheet.getRow(rowIndex);
		int columnNumber;
		columnNumber = getColumnIndex(sheet,"TestCaseResult");
		XSSFCell cell = row.createCell(columnNumber);
		XSSFFont font = workbook.createFont();			
		font.setBoldweight(XSSFFont.DEFAULT_FONT_SIZE);
		font.setFontName("Courier New");
		if(testResult == "PASS")
		{
			//set the text in the cell to green
			cell.setCellValue(testResult);
			sheet.autoSizeColumn(columnNumber);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			font.setColor(HSSFColor.BRIGHT_GREEN.index);
			cellStyle.setFont(font);
			((XSSFCell) cell).setCellStyle(cellStyle);
		}
		//If the result is PASS WITH ISSUE, set the cell value with a BLUE cell style
		else if(testResult.equalsIgnoreCase("PASS WITH ISSUE")){
			//set the text in the cell to green
			cell.setCellValue(testResult);
			sheet.autoSizeColumn(columnNumber);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			font.setColor(HSSFColor.BLUE.index);
			cellStyle.setFont(font);
			((XSSFCell) cell).setCellStyle(cellStyle);
		}
		else 
		{
			//set the text in the cell to red
			cell.setCellValue(testResult);				
			sheet.autoSizeColumn(columnNumber);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			font.setColor(HSSFColor.RED.index);
			cellStyle.setFont(font);
			((XSSFCell) cell).setCellStyle(cellStyle);
		}
		//Close the input file stream
		file.close();
		//Create an output stream for writing to file
		FileOutputStream outFile = new FileOutputStream(new File(wbName));
		//Flush the contents to a file
		workbook.write(outFile);
		//Close the output stream
		outFile.close();
	}



}