package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    public static final String PATH = ".\\testData\\Opencart_LoginData.xlsx"; // Path to the Excel file
    public static final String SHEET_NAME = "Sheet1"; // Name of the sheet

    //DataProvider 1

    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException {

        ExcelUtility excelUtility = new ExcelUtility(PATH);//creating an object for XLUtility

        int totalRows = excelUtility.getRowCount(SHEET_NAME);
        int totalCols = excelUtility.getCellCount(SHEET_NAME,1);

        String[][] loginData = new String[totalRows][totalCols];//created for two dimension array which can store the data user and password

        for(int i=1;i<=totalRows;i++)  //1   //read the data from xl storing in 2-d array
        {
            for(int j=0;j<totalCols;j++)  //0    i is rows j is col
            {
                loginData[i-1][j]= excelUtility.getCellData(SHEET_NAME,i, j);  //1,0
            }
        }
        return loginData;//returning two dimension array

    }

    //DataProvider 2

    //DataProvider 3

    //DataProvider 4
}
