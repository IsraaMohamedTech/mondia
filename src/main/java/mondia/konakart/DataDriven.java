package mondia.konakart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static String getCellValue(String xl, String Sheet, int r, int c) {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\" + xl);
			Workbook wb = WorkbookFactory.create(fis);
			Cell cell = wb.getSheet(Sheet).getRow(r).getCell(c);
			if(cell.getCellTypeEnum()==CellType.STRING)
				return cell.getStringCellValue();
			else
				return String.valueOf( cell.getNumericCellValue());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}

	}

	public static int getRowCount(String xl, String Sheet) {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\" + xl);
			Workbook wb = WorkbookFactory.create(fis);
			return wb.getSheet(Sheet).getLastRowNum();
		}

		catch (Exception e) {
			return 0;
		}

	}

	public void createWork() throws IOException {
		Workbook wb = new XSSFWorkbook();
		createNewSheet(wb);

		FileOutputStream fileOut = new FileOutputStream(
				new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\CartItems.xlsx"));
		wb.write(fileOut);
		fileOut.close();

	}

	public void createNewSheet(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet1 = wb.createSheet("Cart Products");
		createCell(sheet1, createHelper);
	}

	public void createCell(Sheet sheet, CreationHelper createHelper) {
		Row row1 = sheet.createRow((short) 0);
		row1.createCell(0).setCellValue(createHelper.createRichTextString("ProductName"));
		row1.createCell(1).setCellValue(createHelper.createRichTextString("ProductPrice"));
	}

	public void addAddedProductsToSheet(String fileName, String productName, String price, int rowIndex)
			throws IOException {
		FileInputStream fis = new FileInputStream(
				new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\" + fileName));
		Workbook wb = new XSSFWorkbook(fis);
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.createRow((short) rowIndex);
		row.createCell(0).setCellValue(createHelper.createRichTextString(productName));
		row.createCell(1).setCellValue(createHelper.createRichTextString(price));

		FileOutputStream fos = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\" + fileName);
		wb.write(fos);
		fos.close();
		System.out.println("END OF WRITING DATA IN EXCEL");

	}

}
