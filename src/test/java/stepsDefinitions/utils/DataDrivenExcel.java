package stepsDefinitions.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataDrivenExcel {

	private DecimalFormat df = new DecimalFormat("0.###");
	Logger logger = Logger.getLogger( DataDrivenExcel.class.getName());
	
	public Map<String, String> readExcel(Excel excel) {
		// Variable wich storage all the rows
		Map<String, String> excelData = new HashMap<>();
		try {
			// Invoking and using excel
			FileInputStream arcExcel = new FileInputStream(new File(excel.getExcelPath()));
			Workbook excelBook = new XSSFWorkbook(arcExcel);
			// Excel sheet to use
			Sheet arcExcelSheet = excelBook.getSheet(excel.getExcelSheet());
			Iterator<Row> iterator = arcExcelSheet.iterator();
			ArrayList<String> header = new ArrayList<>();
			// iteration cycle for each row
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> iteratorCell = currentRow.iterator();
				// Variable who storage each row
				int rowNum = currentRow.getRowNum();
				if ((excel.isContainsHeader() && rowNum == 0) || rowNum == excel.getFilaLeer()) {
					// Cycle of cells or columns in the excel sheetl
					while (iteratorCell.hasNext()) {
						Cell currentCell = iteratorCell.next();
						String cellValue;
						// Validate cell type to process
						switch (currentCell.getCellType()) {
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(currentCell)) {
								cellValue = "" + currentCell.getDateCellValue().getTime();
							} else {
								cellValue = df.format(currentCell.getNumericCellValue());
							}
							break;
						default:
							cellValue = currentCell.getStringCellValue();
							break;
						}
						// Validate whether it has a header or not
						if (excel.isContainsHeader()) {
							if (rowNum == 0) {
								header.add(cellValue);
							} else {
								excelData.put(header.get(currentCell.getColumnIndex()), cellValue);
							}
						} else {
							if (rowNum == excel.getFilaLeer()) {
								excelData.put("" + currentCell.getColumnIndex() + "", cellValue);
							}
						}
					}
				}
			}
			excelBook.close();
		} catch (IOException e) {
			logger.log(Level.INFO, e.getMessage());
		}
		return excelData;
	}

}
