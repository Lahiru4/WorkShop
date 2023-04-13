package dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTM {
    private String empId;
    private String empName;
    private String gmail;
    private String number;
    private String iD_number;
    private String register_date;
    private String address;
    private String position;
    private JFXButton button;
}
