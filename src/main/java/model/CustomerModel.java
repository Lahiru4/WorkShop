package model;

import dto.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean setData(Customer customer) throws SQLException, ClassNotFoundException {
        boolean execute = CrudUtil.execute("INSERT INTO customer(Id, name, number, address,gmail) VALUES(?, ?, ?, ?,?)",
                customer.getCusID(), customer.getCusName(), customer.getCusNumber(), customer.getCusAddress(),
                customer.getCusGmail());

        return execute;

    }

    public static List<Customer> getAll() {
        List<Customer> getData = new ArrayList<>();
        try {

            ResultSet rs = CrudUtil.execute("SELECT * FROM customer");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String number = rs.getString(3);
                String address = rs.getString(4);
                String gmail = rs.getString(5);
                getData.add(new Customer(id, name, number, address, gmail));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return getData;

    }

    public static boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from customer  where id = ?", cusId);
    }

    public static boolean updateCustomer(String cusID, Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update customer set name = ? ,number = ?,address = ?,gmail = ?where Id = ? ", customer.getCusName(),
                customer.getCusNumber(), customer.getCusAddress(), customer.getCusGmail(), cusID);
    }
    public static String getLastcustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Id FROM customer ORDER BY Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getString(1));
        }
        return "CUS-0001";
    }
}
