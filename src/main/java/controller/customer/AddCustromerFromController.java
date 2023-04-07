package controller.customer;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import model.CustomerModel;

public class AddCustromerFromController {

    public JFXTextField cusID;
    public JFXTextField cusName;
    public JFXTextField cusNumber;
    public JFXTextField cusAddress;
    public JFXTextField cusGmail;

    public void on(KeyEvent keyEvent) {

    }
    public Customer getTexData(){
        String id = cusID.getText();
        String name = cusName.getText();
        String number = cusNumber.getText();
        String cusAddressText = cusAddress.getText();
        String gmail = cusGmail.getText();
        return new Customer(id,name,number,cusAddressText,gmail);
    }

    public void saveCustomer(ActionEvent actionEvent) {
        CustomerModel.setData(getTexData());
    }
}
