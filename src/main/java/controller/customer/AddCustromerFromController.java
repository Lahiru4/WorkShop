package controller.customer;

import com.jfoenix.controls.JFXButton;
import dto.Customer;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.CustomerModel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class AddCustromerFromController {
    public JFXButton update;
    public TextField cusID;
    public TextField cusName;
    public TextField cusNumber;
    public TextField cusAddress;
    public TextField cusGmail;
    public static String update_cus_ID;
    public JFXButton save;

    public void initialize() {
        cusID.setText(update_cus_ID);
        if (update_cus_ID.equals("")) {
            save.setVisible(true);
            update.setVisible(false);
        } else {
            save.setVisible(false);
            update.setVisible(true);
        }
    }

    static {
        update_cus_ID = "";
    }

    public static void setUpdate_cus_ID(String update_cus_ID) {
        AddCustromerFromController.update_cus_ID = update_cus_ID;
    }

    public Customer getTexData() {
        String id = cusID.getText();
        String name = cusName.getText();
        String number = cusNumber.getText();
        String cusAddressText = cusAddress.getText();
        String gmail = cusGmail.getText();
        return new Customer(id, name, number, cusAddressText, gmail);
    }

    public void saveCustomer(ActionEvent actionEvent) {
        try {
            boolean b = CustomerModel.setData(getTexData());
            if (b){
                System.out.println("okay");
                Notifications.create()
                .graphic(new ImageView(new Image("/img/icons8-cancel-50.png")))
                .text("Add ")
                .title("Okay")
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .showConfirm();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        update.getScene().getWindow().hide();
        texClear();
        update_cus_ID="";


    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            boolean b = CustomerModel.updateCustomer(update_cus_ID, getTexData());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Update Okay").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Update Fall").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        update.getScene().getWindow().hide();
        texClear();
        update_cus_ID="";

    }
    public void texClear(){
        cusID.clear();
        cusName.clear();
        cusNumber.clear();
        cusAddress.clear();
        cusGmail.clear();
    }
}
