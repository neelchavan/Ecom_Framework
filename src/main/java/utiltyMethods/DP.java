package utiltyMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {
	DataFormatter format = new DataFormatter();

	public static void main(String[] args) throws Exception {
		// Testing if the data prints.
		DP dp = new DP();
		// This is just a demo code
		Object[][] dataTest1 = dp.readCompleteExcel("DatasetsforQTrip.xlsx", "TestCase01");

		System.out.println();
		for (int i = 0; i < dataTest1.length; i++) {
			System.out.println();
			for (int j = 0; j < dataTest1[0].length; j++) {
				System.out.print(dataTest1[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	@DataProvider(name = "data-provider")
	public static Object[][] dpMethod(Method m) throws Exception {
		// the code below is demo
		DP dp = new DP();
		// TestCase 1 data
		Object[][] dataTest1 = dp.readCompleteExcel("DatasetsforQTrip.xlsx", "TestCase01");
		// TestCase 2 data
		Object[][] dataTest2 = dp.readCompleteExcel("DatasetsforQTrip.xlsx", "TestCase02");
		// TestCase 3 data
		Object[][] dataTest3 = dp.readCompleteExcel("DatasetsforQTrip.xlsx", "TestCase03");
		// TestCase 4 data
		Object[][] dataTest4 = dp.readCompleteExcel("DatasetsforQTrip.xlsx", "TestCase04");

		switch (m.getName()) {
		case "TestCase01":
			return dataTest1;
		case "TestCase02":
			return dataTest2;
		case "TestCase03":
			return dataTest3;
		case "TestCase04":
			return dataTest4;
		}
		return null;
	}

	public Object[][] readCompleteExcel(String testDataExcelFileName, String sheetName) throws IOException {

		// set your own path of where is excel file is stored, according to your
		// project.
		String testDataExcelPath = System.getProperty("user.dir") + "/src/test/resources/";

		String fileName = testDataExcelPath + testDataExcelFileName;
		// File fileName = new File(filePath);
		FileInputStream file = new FileInputStream(fileName);

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getPhysicalNumberOfRows();

		int colsCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount - 1][colsCount - 1];
		for (int i = 0; i < rowCount - 1; i++) { // outer for loop to iterate each row
			XSSFRow rows = sheet.getRow(i + 1);
			for (int j = 0; j < colsCount - 1; j++) { // inner for loop to iterate each cell
				data[i][j] = rows.getCell(j + 1).toString();
				// format.formatCellValue(rows.getCell(j + 1)); //to store the data as a string.
			}
		}
		workbook.close();
		return data;
	}
}
