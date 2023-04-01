package model;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
