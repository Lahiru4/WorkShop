package controller.cashier;

import dto.tm.ItemsTMTM;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class AddCartController {
    public static ItemsTMTM itemsTMTM1;



    public static ControllerItems controllerItems;
    public TextField entQty;
    public Label qty;
    public static void setControllerItems(ControllerItems controllerItems) {
        AddCartController.controllerItems = controllerItems;
    }

    public void initialize(){
        if(itemsTMTM1==null)return;
        qty.setText(String.valueOf(itemsTMTM1.getQty()));
    }

    public static void setItemsTMTM(ItemsTMTM itemsTMTM) {
        itemsTMTM1 = itemsTMTM;
    }

    public void addtobtnOnAction(ActionEvent actionEvent) {
        Window window = qty.getScene().getWindow();
        if (Integer.valueOf(entQty.getText())>0 && Integer.valueOf(qty.getText()) > Integer.valueOf(entQty.getText())) {
            ControllerItems.setEnterQty(Integer.valueOf(entQty.getText()));
            ControllerItems.setItemsTMTM(itemsTMTM1);
            if (controllerItems!=null){
                controllerItems.setBillTableDataCall();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Input").showAndWait();
        }
        window.hide();
    }
}
