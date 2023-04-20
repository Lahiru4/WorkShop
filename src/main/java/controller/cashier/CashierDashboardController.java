package controller.cashier;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierDashboardController implements Initializable {

    public AnchorPane itemsAndBill;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemsAndBill.getChildren().clear();
        Node node = FXMLLoader.load(getClass().getResource("/view/cashier/Items.fxml"));
        itemsAndBill.getChildren().addAll(node);

    }

    public void homebtnOnAction(ActionEvent actionEvent) throws IOException {
        itemsAndBill.getChildren().clear();
        Node node = FXMLLoader.load(getClass().getResource("/view/cashier/Items.fxml"));
        itemsAndBill.getChildren().addAll(node);
    }

    public void logOutBtnOn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemsAndBill.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(ViewFactory.getInstance().getScene(SceneTypes.LOGIN_PAGE));
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.show();
    }
}
