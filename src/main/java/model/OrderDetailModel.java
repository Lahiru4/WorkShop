package model;

import db.DbConnection;
import dto.Items;
import dto.Order;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(Order oId, List<Items> cartDTOList) throws SQLException, ClassNotFoundException {
        for (Items dto : cartDTOList) {
            if (!save(oId, dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(Order oId, Items dto) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO orderdetails(order_Id, item_code, qty) VALUES (?, ?, ?)";

        return  CrudUtil.execute(sql,oId.getId(),dto.getItemCode(),dto.getQTY());

    }
}
