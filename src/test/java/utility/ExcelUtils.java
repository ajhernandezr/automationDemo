package utility;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utility.Constant;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;
	private static XSSFRow Row;

	// Open the excel file

	public static void setExcelFile(String Path, String SheetName) throws Exception {

		try {

			FileInputStream ExcelFile = new FileInputStream(Constant.Path_TestData);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {

			throw (e);

		}

	}

	// Get the value for each cell

	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {

		try {
			DataFormatter formatter = new DataFormatter();
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getRawValue();
			CellData = formatter.formatCellValue(Cell);
			return CellData.toString();
		} catch (Exception e) {

			return "";

		}

	}


	
	

	// Input the results in the excel and update the formulas
	public static void inputResults(String result, int row, int counter) throws Exception {

		try {
			// Cell column for know where to put the result
			int cellColumn = 0;
			// Open the Results excel file

			FileInputStream ExcelFile = new FileInputStream(Constant.ResultPath_TestResults);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet("RegressionTests");

			switch (result) {
			case "Pass":
				cellColumn = 3;
				break;
			case "Failed":
				cellColumn = 4;
				break;
			}


				/// We get the row for input the result
				Cell = ExcelWSheet.getRow(row).getCell(cellColumn);

				Cell.setCellValue(counter);

				// Stop reading Excel
				ExcelFile.close();
				// Start to write Excel
				FileOutputStream fileOut = new FileOutputStream(Constant.ResultPath_TestResults);
				FormulaEvaluator formulaEvaluator = ExcelWBook.getCreationHelper().createFormulaEvaluator();
				formulaEvaluator.evaluateAll();
				ExcelWBook.write(fileOut);

				fileOut.close();

				ExcelWBook.close();
			
		} catch (Exception e) {

			throw (e);

		}

	}

	// Delete the Excel results for send a new Report
	public static void eraseExcel() throws Exception {

		try {
			// Cell column for know where to put the result
			int cellColumn = 0;
			// Open the excel
			int[] CellsRows = { 6 };

			FileInputStream ExcelFile = new FileInputStream(Constant.ResultPath_TestResults);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet("RegressionTests");

			/// Start to remove passed cells

			for (int index = 0; index < CellsRows.length; index++) {
				cellColumn = 3;
				int i = CellsRows[index];
				Cell = ExcelWSheet.getRow(i).getCell(cellColumn);
				Cell.setCellType(CellType.BLANK);
			}
			/// Start to remove failed cells

			for (int index = 0; index < CellsRows.length; index++) {
				cellColumn = 4;
				int i = CellsRows[index];
				Cell = ExcelWSheet.getRow(i).getCell(cellColumn);
				Cell.setCellType(CellType.BLANK);
			}

			// Stop reading Excel
			ExcelFile.close();
			// Start to write Excel
			FileOutputStream fileOut = new FileOutputStream(Constant.ResultPath_TestResults);
			FormulaEvaluator formulaEvaluator = ExcelWBook.getCreationHelper().createFormulaEvaluator();
			formulaEvaluator.evaluateAll();
			ExcelWBook.write(fileOut);

			fileOut.close();

			ExcelWBook.close();

		} catch (Exception e) {

			throw (e);

		}

	}

}
