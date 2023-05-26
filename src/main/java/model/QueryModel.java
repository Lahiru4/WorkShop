package model;

import dto.Order;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryModel {
    private static Connection connection;

    /*public static List<OrderCustomerQuotationJoinDto> getAll() throws SQLException {
        connection = DbConnection.getInstance().getConnection();
        //connection.setAutoCommit(false);
        List<OrderCustomerQuotationJoinDto> data = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        String sql2 = "SELECT * FROM customer";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        while (resultSet.next() || resultSet1.next()) {

            String orderId = resultSet.getString(1);
            //String orderDescription = resultSet.getString(2);
            Date orderDate = resultSet.getDate(2);
            Date returnOrderDate = resultSet.getDate(3);
            double workRent = resultSet.getDouble(4);
            double itemCost = resultSet.getDouble(5);
            String cusId = resultSet.getString(6);
            String cusName = resultSet1.getString(2);
            String cusPhoneNumber = resultSet1.getString(3);
            String cusAddress = resultSet1.getString(4);
            String cusGmail = resultSet1.getString(5);
            OrderCustomerQuotationJoinDto newData = new OrderCustomerQuotationJoinDto(orderId, "orderDescription", orderDate, returnOrderDate, workRent, itemCost, cusId, cusName, cusPhoneNumber, cusAddress, cusGmail,"ss");
            data.add(newData);
        }

        return data;
    }*/
    public static List<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders");
        List<Order> data = new ArrayList<>();
        while (resultSet.next()){
            double workRent = Double.parseDouble(resultSet.getString(4));
            double itemCost = Double.parseDouble(resultSet.getString(5));

            data.add(new Order(resultSet.getString(1),"not",resultSet.getString(2),resultSet.getString(3),workRent
                    ,itemCost,resultSet.getString(6)));
        }

        return data;

    }


}
