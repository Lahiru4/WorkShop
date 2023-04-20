package dto.tm;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemsTM {
    private String itemCode;
    private String description;
    private int QTY;
    private double selling_price;
    private double Purchase_price;
    private Image img;
    private String supplier_id;
}
