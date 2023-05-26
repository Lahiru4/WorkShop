package dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTM {
    private String id;
    private String order_date;
    private String return_date;
    private double work_rent;
    private double item_cost;
    private String customer_Id;
    private String qID;

}
