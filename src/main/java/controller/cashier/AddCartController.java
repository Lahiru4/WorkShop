package controller.cashier;

import dto.tm.ItemsTMTM;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class AddCartController {
    public static ItemsTMTM itemsTMTM1;
    public TextField entQty;
    public Label qty;
    public void initialize(){
        qty.setText(String.valueOf(itemsTMTM1.getQty()));
    }

    public static void setItemsTMTM(ItemsTMTM itemsTMTM) {
        itemsTMTM1 = itemsTMTM;
    }
    public void addtobtnOnAction(ActionEvent actionEvent) {
        Window window = qty.getScene().getWindow();
        if (Integer.valueOf(entQty.getText())>0 && Integer.valueOf(qty.getText()) > Integer.valueOf(entQty.getText())) {
            ControllerItems.setEnterQty(Integer.valueOf(entQty.getText()));
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Input ").showAndWait();
        }
        window.hide();
    }
}
