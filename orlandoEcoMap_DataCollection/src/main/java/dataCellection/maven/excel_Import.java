package dataCellection.maven;

import java.io.*;
import java.sql.*;
import java.util.*;
//import java.util.Date; //uncomment this when it becomes relevant

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class excel_Import {
 
    public static void main(String[] args) {
    	 String url = "jdbc:mysql://localhost:3306/orlando eco-map";
         String user = "root";
         String password = "root";
 
         String excelFilePath = "C:\\Users\\MariadellyE\\eclipse-workspace3\\orlandoEcoMap_DataCollection\\locations.xlsx";
         int batchSize = 20;
  
         Connection connection = null;
  
         try {
             long start = System.currentTimeMillis();
              
             FileInputStream inputStream = new FileInputStream(excelFilePath);
  
             Workbook workbook = new XSSFWorkbook(inputStream);
  
             Sheet firstSheet = workbook.getSheetAt(0);
             Iterator<Row> rowIterator = firstSheet.iterator();
  
             connection = DriverManager.getConnection(url, user, password);
             connection.setAutoCommit(false);
            
             String sql = "INSERT INTO locations (Loc_ID, Street_address, Description, Zip ) VALUES (?, ?, ?, ?)";
             PreparedStatement statement = connection.prepareStatement(sql);    
              
             int count = 0;
              
             rowIterator.next(); // skip the header row
              
             while (rowIterator.hasNext()) {
                 Row nextRow = rowIterator.next();
                 Iterator<Cell> cellIterator = nextRow.cellIterator();
  
                 while (cellIterator.hasNext()) {
                     Cell nextCell = cellIterator.next();
  
                     int columnIndex = nextCell.getColumnIndex();
  
                     switch (columnIndex) {
                     case 0:
                    	 String Loc_ID = nextCell.getStringCellValue();
                    	 statement.setString(1, Loc_ID);
                         break;
                     case 1:
                    	 String Street_address = nextCell.getStringCellValue();
                         statement.setString(2, Street_address);
                         break;
                     case 2:
                    	 String Description = nextCell.getStringCellValue();
                         statement.setString(3, Description);
                         break;
                     case 3:
                    	 int Zip = (int) nextCell.getNumericCellValue();
                    	 statement.setInt(4, Zip);          
                     }
  
                 }
                  
                 statement.addBatch();
                  
                 if (count % batchSize == 0) {
                     statement.executeBatch();
                 }              
  
             }
  
             workbook.close();
              
             // execute the remaining queries
             statement.executeBatch();
   
             connection.commit();
             connection.close();
              
             long end = System.currentTimeMillis();
             System.out.printf("Import done in %d ms\n", (end - start));
              
         } catch (IOException ex1) {
             System.out.println("Error reading file");
             ex1.printStackTrace();
         } catch (SQLException ex2) {
             System.out.println("Database error");
             ex2.printStackTrace();
         }
  
     }
 }
