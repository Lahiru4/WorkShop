package dto;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private String itemCode;
    private String description;
    private int QTY;
    private double selling_price;
    private double Purchase_price;
    private Image img;
    private String suppler_Id;
}
