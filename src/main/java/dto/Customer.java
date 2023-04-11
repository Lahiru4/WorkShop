package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    public String cusID;
    public String cusName;
    public String cusNumber;
    public String cusAddress;
    public String cusGmail;
}
