package controller;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.EmployeeModel;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LogingPageController {
    public AnchorPane loadFormContext;
    public JFXPasswordField cashierId;

    public void cashierloginBtnOnAction(ActionEvent actionEvent) throws IOException {
        try {
            List<String> id = EmployeeModel.getID();
            int i=0;
            for (String cId :id){
                if (cId.equals(cashierId.getText())) {
                    i++;
                    Stage window = (Stage) loadFormContext.getScene().getWindow();
                    window.setScene(ViewFactory.getInstance().getScene(SceneTypes.CASHIER_DASHBOARD));
                    window.centerOnScreen();
                }
            }
            if (i==0){
                new Alert(Alert.AlertType.ERROR,"Invelid Input").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void gobtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) loadFormContext.getScene().getWindow();
        window.setScene(ViewFactory.getInstance().getScene(SceneTypes.ADMIN_LOGIN_PAGE));
        window.centerOnScreen();
    }
}
