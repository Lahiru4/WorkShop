package model;

import dto.WekLineChartData;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static List<WekLineChartData> getSellsLineChartData(){
        ArrayList<WekLineChartData> all = new ArrayList<>();

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
            double tot = getTot(s);
            System.out.println(tot);
            all.add(new WekLineChartData(s,tot));
        }
        System.out.println(all.size());
        return all;
    }

    private static double getTot(String s) {
        double tot=0;
        String SUQ="SELECT item_cost FROM orders WHERE order_date=?";
        try {
            ResultSet resultSet= CrudUtil.execute(SUQ,s);
            while (resultSet.next()){
                String string = resultSet.getString(1);
                double v = Double.parseDouble(string);
                tot+=v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tot;
    }
}
