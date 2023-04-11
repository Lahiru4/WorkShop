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
    private String empNumber;
    private String cusAddress;
    private String empPosition;
    private String iDnumber;
}
