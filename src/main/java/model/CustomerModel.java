package model;

import dto.Customer;
import javafx.scene.control.Alert;
import util.CrudUtil;

import java.sql.SQLException;

public class CustomerModel {
    public static void setData(Customer customer){
        try {
            boolean execute = CrudUtil.execute("INSERT INTO customer(Id, name, number, address,gmail) VALUES(?, ?, ?, ?,?)", customer.getCusID(), customer.getCusName(), customer.getCusNumber(), customer.getCusAddress(), customer.getCusGmail());
            if (execute){
                new Alert(Alert.AlertType.INFORMATION,"ADD CUSTOMER SUCCESS").showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"CUSTOMER ADDING FALL").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
