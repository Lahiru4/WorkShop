package dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTM {
    private String cusID;
    private String cusName;
    private String cusNumber;
    private String cusAddress;
    private String cusGmail;
    private Button button;
}
