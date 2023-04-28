package model;

import db.DbConnection;
import dto.DaySalesQty;
import dto.Items;
import dto.Order;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        String s = String.valueOf(LocalDate.now());
        String sql = "INSERT INTO orderdetails(order_Id, item_code,qty,order_date) VALUES (?, ?,?, ?)";
        System.out.println(oId.getId());
        return  CrudUtil.execute(sql,oId.getId(),dto.getItemCode(),dto.getQTY(),s);

        //"SELECT i.description,od.qty,o.item_cost FROM orders o INNER JOIN orderdetails od ON o.Id=od.order_Id INNER JOIN items i ON i.code=od.item_code; WHERE o.Id=?;",oId
    }
    public static List<DaySalesQty> getSellsQty(){
        ArrayList<DaySalesQty> all = new ArrayList<>();

        LocalDate date = LocalDate.now();
        LocalDate y1 = date.minusDays(1);
        LocalDate y2 = date.minusDays(2);
        LocalDate y3 = date.minusDays(3);
        LocalDate y4 = date.minusDays(4);
        LocalDate y5 = date.minusDays(5);
        LocalDate y6 = date.minusDays(6);


        ArrayList<LocalDate> dates=new ArrayList<>();
        dates.add(date);
        dates.add(y1);
        dates.add(y2);
        dates.add(y3);
        dates.add(y4);
        dates.add(y5);
        dates.add(y6);


        for (LocalDate temp:dates){
            String s = String.valueOf(temp);
            int tot = getTot(s);

            all.add(new DaySalesQty(s,tot));
        }
        return all;
    }

    private static int getTot(String s) {
        int tot=00;
        String SUQ="SELECT qty FROM orderdetails WHERE order_date=?";
        try {
            ResultSet resultSet= CrudUtil.execute(SUQ,s);
            while (resultSet.next()){
                String string = resultSet.getString(1);
                int v = Integer.parseInt(string);
                tot+=v;
                System.out.println(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tot;

    }

}
