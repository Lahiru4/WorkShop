package controller.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StockManageController {


    public AnchorPane stockManage;

    public void addItemsOn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/inventory/AddInventory.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stockManage.getScene().getWindow());
        stage.showAndWait();
    }
}
