package model;

import dto.Items;
import dto.tm.ItemsTM;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemsModel {
    public static boolean setData(Items items) throws SQLException, ClassNotFoundException, IOException {
        boolean b = CrudUtil.execute("INSERT INTO items(code, description, qty, " +
                        "selling_price,Purchase_price,img) VALUES(?, ?, ?, ?,?,?)", items.getItemCode(), items.getDescription(), items.getQTY(), items.getSelling_price()
                , items.getPurchase_price(),items.getImg());
        return b;
    }
    public static List<ItemsTM> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT img FROM items");
        while (rs.next()){

        }
        return  null;
    }
}
