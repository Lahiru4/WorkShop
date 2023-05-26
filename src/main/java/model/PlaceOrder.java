package model;

import controller.cashier.PlayBillController;
import db.DbConnection;
import dto.Items;
import dto.Order;
import dto.tm.ItemTM2;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrder {
    public static boolean placeOrder(Order order, ObservableList<ItemTM2> billTableData) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        double tot = 0;
        for (ItemTM2 temp : billTableData) {
            double sallingPrice = temp.getSallingPrice();
            tot += sallingPrice;
        }
        String date = String.valueOf(LocalDate.now());
        PlayBillController.setTot(tot);
        try {
            connection.setAutoCommit(false);
            String i = order.getId();
            boolean b = CrudUtil.execute("INSERT INTO orders(Id,order_date, return_date,work_rent,item_cost,customer_Id) " +
                    "VALUES(?, ?, ?,?,?,?)", i, date, order.getReturn_date(), order.getWork_rent(), tot, order.getCustomer_Id());

            List<Items> data = new ArrayList<>();

            for (ItemTM2 temp : billTableData) {
                data.add(new Items(temp.getItemCode(), temp.getDescription(), temp.getQty(), temp.getSallingPrice(),
                        temp.getPurchase_price(), null, temp.getSuppler_Id()));
            }

            boolean b1 = ItemsModel.updateQty(data);
            boolean save = OrderDetailModel.save(order, data);
            if (b && b1 && save) {
                return true;
            }

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
            System.out.println(" yes it is run ");
        }
        return false;
    }

    public static String orderGetLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getString(1));
        }
        return null;
    }
}
