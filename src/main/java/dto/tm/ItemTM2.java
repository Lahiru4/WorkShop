package dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemTM2  {
    private ImageView img;
    private String description;
    private int qty;
    private double sallingPrice;
    private String itemCode;
    private double purchase_price;
    private String suppler_Id;
    private JFXButton button;
    public ItemTM2(String description, int qty, double sallingPrice,String itemCode,double purchase_price,String suppler_Id,JFXButton button) {
        this.description = description;
        this.qty = qty;
        this.sallingPrice = sallingPrice;
        this.itemCode =itemCode;
        this.purchase_price =purchase_price;
        this.suppler_Id =suppler_Id;
        this.button=button;
    }
}
