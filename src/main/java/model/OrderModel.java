package model;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderModel {
    public static void getSellsLineChartData(){

        LocalDate date = LocalDate.now();
        LocalDate y1 = date.minusDays(1);
        LocalDate y2 = date.minusDays(2);
        LocalDate y3 = date.minusDays(3);
        LocalDate y4 = date.minusDays(4);
        LocalDate y5 = date.minusDays(5);
        LocalDate y6 = date.minusDays(6);





        String SUQ="SELECT item_cost FROM orders WHERE order_date=?";
        try {
            ResultSet resultSet=CrudUtil.execute(SUQ,String.valueOf(date));
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
