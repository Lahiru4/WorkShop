package model;

import dto.Order;
import util.CrudUtil;

import java.sql.SQLException;

public class PlaceOrder {
    public static void place(Order order,String id){

        try {
            CrudUtil.execute("INSERT INTO customer(Id, name, number, address,gmail) VALUES(?, ?, ?, ?,?,?,?)",order.getCustomer_Id(),order.getDescription(),order.getOrder_date(),order.getReturn_date(),order.getWork_rent(),order.getItem_cost(),order.getCustomer_Id());
            CrudUtil.execute("UPDATE appointment SET status = 'Canceled' WHERE appointmentId = ?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
