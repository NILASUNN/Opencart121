package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getLoginData() throws Exception {

    	//Taking XL file from testData
        String path = ".//testData//opencard_login_data.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path); //Creating an object for XLUtility

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        //Created for two dimensional array which ca stored
        String logindata[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++)//1  //read the data from xl and store in two dimensional array 
        {
            for (int j = 0; j < totalcols; j++) //0     i is rows, j is col
            {

                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return logindata; //returning two dimension array
    }
}