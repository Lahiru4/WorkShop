package controller.employee;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManagementController {

    public JFXButton employRegisterBtn;

    public void empRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee/AddEmployee.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(employRegisterBtn.getScene().getWindow());
        stage.showAndWait();

    }
}
