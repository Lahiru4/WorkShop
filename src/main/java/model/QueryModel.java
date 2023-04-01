package model;

import db.DbConnection;
import dto.OrderCustomerQuotationJoinDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryModel {
    private static Connection connection;

    public static List<OrderCustomerQuotationJoinDto> getAll() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        //connection.setAutoCommit(false);
        List<OrderCustomerQuotationJoinDto> data = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String sql2 = "SELECT * FROM customer";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        while (resultSet.next() && resultSet1.next()) {
            String orderId = resultSet.getString(1);
            String orderDescription = resultSet.getString(2);
            Date orderDate = resultSet.getDate(3);
            Date returnOrderDate = resultSet.getDate(4);
            double workRent = resultSet.getDouble(5);
            double itemCost = resultSet.getDouble(6);
            String cusId = resultSet.getString(7);
            String cusName = resultSet1.getString(2);
            String cusPhoneNumber = resultSet1.getString(3);
            String cusAddress = resultSet1.getString(4);
            String cusGmail = resultSet1.getString(5);
            OrderCustomerQuotationJoinDto newData = new OrderCustomerQuotationJoinDto(orderId, orderDescription, orderDate, returnOrderDate, workRent, itemCost, cusId, cusName, cusPhoneNumber, cusAddress, cusGmail,"ss");
            data.add(newData);
        }

        return data;
    }

}
