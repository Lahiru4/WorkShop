package controller.order;

import dto.Order;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddOdercontroller {



    public DatePicker date;
    public Label orderDate;
    public TextField orderId;

    public void initialize(){
        orderDate.setText(String.valueOf(LocalDate.now()));
        try {
            String s = PlaceOrder.orderGetLastId();
            orderId.setText(s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order collectData(){
         String id="";
         String description="";
         String order_date="2002.02.12";
         String return_date="2002.12.02";
         double work_rent=122.2;
         double item_cost=111.25;
         String customer_Id="";
         return  new Order(id,description,order_date,return_date,work_rent,item_cost,customer_Id);
    }

    public void saveOrder(ActionEvent actionEvent) {

    }
}
