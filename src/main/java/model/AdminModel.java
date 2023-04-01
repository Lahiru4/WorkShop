package model;

import db.DbConnection;
import dto.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    public static List getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Admin> data=new ArrayList<>();
        while (resultSet.next()){
            data.add(new Admin(resultSet.getString(1),resultSet.getString(2)));
        }
        return data;
    }
}
