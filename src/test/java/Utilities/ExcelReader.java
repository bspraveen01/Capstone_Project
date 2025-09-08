package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    private static Workbook workbook;
    private static Sheet sheet;

    // Load Excel file and sheet
    public static void setExcelFile(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
        fis.close();
    }

    // Get cell value by row/column indexes
    public static String getCellData(int rowIndex, int colIndex) {
        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
        if (cell == null) return "";

        String cellValue = "";
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = String.valueOf((long) cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = "";
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }

    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static int getColCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    // Convert Excel sheet to Object[][] for TestNG DataProvider
    public static Object[][] getData() {
        int rowCount = getRowCount();
        int colCount = getColCount();

        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }
}
