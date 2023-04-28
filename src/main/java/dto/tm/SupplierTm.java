package dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class SupplierTm {
    private String id;
    private String name;
    private String Address;
    private String number;
    private String gmail;
    private JFXButton button;
}
