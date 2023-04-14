package model;

import dto.Items;
import util.CrudUtil;

import java.sql.SQLException;

public class ItemsModel {
    public static boolean setData(Items items) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.execute("INSERT INTO items(code, description, qty, " +
                        "selling_price,Purchase_price,img) VALUES(?, ?, ?, ?,?,?)", items.getItemCode(), items.getDescription(), items.getQTY(), items.getSelling_price()
                , items.getPurchase_price(), items.getImg());
        return b;
    }
}
