package controller.admin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;

public class AdminLogPagecontroller {

    public AnchorPane loginfromAnchorPane;
    public JFXPasswordField password;
    public JFXTextField gmail;

    public void adminLog(ActionEvent actionEvent) throws IOException {
        String gmailText = gmail.getText();
        String passwordText = password.getText();
        if (gmailText.equals("")){new animatefx.animation.Bounce(gmail).play();}
        if (passwordText.equals("")){new animatefx.animation.Bounce(password).play();}
        Stage stage= (Stage) loginfromAnchorPane.getScene().getWindow();
        Window window = loginfromAnchorPane.getScene().getWindow();
        stage.setScene(ViewFactory.getInstance().getScene(SceneTypes.ADMIN_DASHBOARD));
        stage.setMaximized(true);

    }

    public void backOn(ActionEvent actionEvent) throws IOException {
        Stage window= (Stage) loginfromAnchorPane.getScene().getWindow();
        window.setScene(ViewFactory.getInstance().getScene(SceneTypes.LOGIN_PAGE));
    }
}
