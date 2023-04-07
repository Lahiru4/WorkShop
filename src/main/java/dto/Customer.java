package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String cusID;
    private String cusName;
    private String cusNumber;
    private String cusAddress;
    private String cusGmail;
}
