package model;

import db.DbConnection;
import dto.Items;
import dto.Order;
import dto.tm.ItemsTMTM;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrder {
    public static boolean placeOrder(Order order, ObservableList<ItemsTMTM> billTableData) {
        double tot=0;
        for (ItemsTMTM temp:billTableData){
            double sallingPrice = temp.getSallingPrice();
            tot+=sallingPrice;
        }
        String date = String.valueOf(LocalDate.now());

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            connection.setAutoCommit(false);

            String i = order.getId();
            boolean b = CrudUtil.execute("INSERT INTO orders(Id, description, order_date, return_date,work_rent,item_cost,customer_Id) VALUES(?, ?, ?,?,?,?,?)", i, null, date, null, null, tot,order.getCustomer_Id());
            List<Items> data = new ArrayList<>();
            System.out.println(billTableData.size());
            for (ItemsTMTM temp : billTableData) {
                data.add(new Items(temp.getItemCode(), temp.getDescription(), temp.getQty(), temp.getSallingPrice(),
                        temp.getPurchase_price(), null, temp.getSuppler_Id()));
                }
            boolean b1 = ItemsModel.updateQty(data);
            OrderDetailModel.save(order, data);
            if (b && b1) {
                connection.commit();
                return true;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public static String orderGetLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getString(1));
        }
        return "";
    }
}
