package model;

import db.DbConnection;
import dto.Employee;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static void searchEmployee(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String SUQ="SELECT id, FROM employee WHERE id";
        PreparedStatement preparedStatement=connection.prepareStatement(SUQ);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String employeeId=resultSet.getString(1);
            String employeeGmail=resultSet.getString(2);
            String employeeNumber=resultSet.getString(3);
            resultSet.getInt(4);
            resultSet.getDate(5);
        }
    }
    public static boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        boolean execute = CrudUtil.execute("INSERT INTO employee(employee_id,name,gmail,phone_number,id_number,register_date,position,address) VALUES(?,?,?,?,?,?,?,?)",employee.getEmpId(),
                employee.getEmpName(),employee.getGmail(),employee.getNumber(),
                employee.getID_number(),employee.getRegister_date(),
                employee.getPosition(), employee.getAddress());
        return execute;
    }
    public static List<Employee> getAll(){
        List<Employee> getData=new ArrayList<>();
        try {

            ResultSet rs=CrudUtil.execute("SELECT * FROM employee");
            while (rs.next()){
                String idText= rs.getString(1);
                String empNameText=rs.getString(2);
                String gmailText=rs.getString(3);
                String empNumberText=rs.getString(4);
                String idNumberText=rs.getString(5);
                String dateText=rs.getString(6);
                String empAddressText=rs.getString(7);
                String empPositionText=rs.getString(8);
                getData.add(new Employee(idText,empNameText,gmailText,empNumberText,idNumberText,
                        dateText,empAddressText,empPositionText));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return getData;
    }
    public static boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from employee  where employee_id = ?",empId);
    }
    public static List<String> getID() throws SQLException, ClassNotFoundException {
        List<String> idData=new ArrayList<>();
        String polished="Cashier";
        ResultSet rs=CrudUtil.execute("SELECT employee_id FROM employee WHERE position = ?", polished);
        while (rs.next()){
            String id = rs.getString(1);

            idData.add(id);
        }
        return idData;
    }

    public static boolean UpdateEmploy(Employee employee, String updateEmpId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update employee set name = ? ,gmail = ?,phone_number = ?,id_number = ?,register_date = ?, position=?, address=? where employee_id = ? ",
                employee.getEmpName(),employee.getGmail(),employee.getNumber(),employee.getID_number(),employee.getRegister_date(),employee.getPosition(),employee.getAddress(),updateEmpId);
    }
}
