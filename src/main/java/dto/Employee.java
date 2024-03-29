package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String empId;
    private String empName;
    private String gmail;
    private String number;
    private String iD_number;
    private String register_date;
    private String address;
    private String position;
}
