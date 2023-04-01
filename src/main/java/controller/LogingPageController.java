package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;

public class LogingPageController {
    public AnchorPane loadFormContext;
    public JFXTextField cashierId;

    public void cashierloginBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) loadFormContext.getScene().getWindow();
        window.setScene(ViewFactory.getInstance().getScene(SceneTypes.CASHIER_DASHBOARD));
        window.centerOnScreen();
    }
    public void gobtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) loadFormContext.getScene().getWindow();
        window.setScene(ViewFactory.getInstance().getScene(SceneTypes.ADMIN_LOGIN_PAGE));
        window.centerOnScreen();
    }
}
