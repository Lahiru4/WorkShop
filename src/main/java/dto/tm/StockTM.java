package dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class StockTM {
    private String itemCode;
    private String description;
    private int QTY;
    private double selling_price;
    private double Purchase_price;
    private String suppler_Id;
    private JFXButton button;
}
