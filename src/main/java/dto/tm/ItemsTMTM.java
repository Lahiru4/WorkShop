package dto.tm;

import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemsTMTM {
    private ImageView img;
    private String description;
    private int qty;
    private double sallingPrice;
    private String itemCode;
    private double purchase_price;
    private String suppler_Id;

    public ItemsTMTM(ImageView img,String description, int qty, double sallingPrice,String itemCode) {
        this.description = description;
        this.qty = qty;
        this.sallingPrice = sallingPrice;
        this.itemCode =itemCode;
        this.img=img;
    }
    public ItemsTMTM(String description, int qty, double sallingPrice,String itemCode,double purchase_price,String suppler_Id) {
        this.description = description;
        this.qty = qty;
        this.sallingPrice = sallingPrice;
        this.itemCode =itemCode;
        this.purchase_price =purchase_price;
        this.suppler_Id =suppler_Id;
    }
    public ItemsTMTM(String description, int qty, double sallingPrice) {
        this.description = description;
        this.qty = qty;
        this.sallingPrice = sallingPrice;
    }
}
