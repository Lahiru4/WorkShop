package controller.cashier;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

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
}
