package util.excel;

import dto.Employee;
import model.EmployeeModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Employexcel {
    public static String[] row_heading = {"Employee Id", "Name", "Gmail", "Phone Number", "Id Number", "Register Date", "position", "Address"};
    public static List<Employee> users = EmployeeModel.getAll();

    public static void writeToExcelSheet(String path) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Employee Details ");
        Row headerRow = spreadsheet.createRow(0);


        // Creating header
        for (int i = 0; i < row_heading.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(row_heading[i]);
        }

        // Creating data rows for each user
        for (int i = 0; i < users.size(); i++) {
            Row dataRow = spreadsheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(users.get(i).getEmpId());
            dataRow.createCell(1).setCellValue(users.get(i).getEmpName());
            dataRow.createCell(2).setCellValue(users.get(i).getGmail());
            dataRow.createCell(3).setCellValue(users.get(i).getNumber());
            dataRow.createCell(3).setCellValue(users.get(i).getID_number());
            dataRow.createCell(3).setCellValue(users.get(i).getRegister_date());
            dataRow.createCell(3).setCellValue(users.get(i).getPosition());
            dataRow.createCell(3).setCellValue(users.get(i).getPosition());
        }
        //Write the workbook in file system
        FileOutputStream out;
        try {
            out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Write to excel sheet done  successfully...........");

    }
}
