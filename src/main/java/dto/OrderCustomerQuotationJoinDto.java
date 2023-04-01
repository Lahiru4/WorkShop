package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class OrderCustomerQuotationJoinDto {
    private String orderId;
    private String orderDescription;
    private Date orderDate;
    private Date returnOrderDate;
    private double workRent;
    private double itemCost;
    private String cusId;
    private String cusName;
    private String cusPhoneNumber;
    private String cusAddress;
    private String cusGmail;
    private String qID;

}
