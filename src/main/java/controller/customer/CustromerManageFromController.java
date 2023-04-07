package controller.customer;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CustromerManageFromController {
    public JFXButton addCustomer;

    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer/AddCustromerFrom.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addCustomer.getScene().getWindow());
        stage.showAndWait();
    }
    private void setCellValueFactory() {
       /* orderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        cusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        placeDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnOrderDate"));
        itemCost.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
        workRent.setCellValueFactory(new PropertyValueFactory<>("workRent"));
        qID.setCellValueFactory(new PropertyValueFactory<>("qID"));*/
    }

}