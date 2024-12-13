package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    //DataProvider 1

    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException
    {
        String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData

        ExcelUtility excelUtility = new ExcelUtility(path);//creating an object for XLUtility

        int totalRows = excelUtility.getRowCount("Sheet1");
        int totalCols = excelUtility.getCellCount("Sheet1",1);

        String[][] loginData = new String[totalRows][totalCols];//created for two dimension array which can store the data user and password

        for(int i=1;i<=totalRows;i++)  //1   //read the data from xl storing in 2-d array
        {
            for(int j=0;j<totalCols;j++)  //0    i is rows j is col
            {
                loginData[i-1][j]= excelUtility.getCellData("Sheet1",i, j);  //1,0
            }
        }
        return loginData;//returning two dimension array

    }

    //DataProvider 2

    //DataProvider 3

    //DataProvider 4
}
