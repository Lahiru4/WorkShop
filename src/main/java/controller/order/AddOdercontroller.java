package controller.order;

import dto.Order;
import javafx.event.ActionEvent;
import model.PlaceOrder;

public class AddOdercontroller {


    public static Order collect(){
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
        PlaceOrder.place(collect(),"001");
    }
}
