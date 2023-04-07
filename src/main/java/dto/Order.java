package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String id;
    private String description;
    private String order_date;
    private String return_date;
    private double work_rent;
    private double item_cost;
    private String customer_Id;


}
