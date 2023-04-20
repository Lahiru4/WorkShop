package controller.cashier;

import dto.Order;
import dto.tm.ItemsTM;
import dto.tm.ItemsTMTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemsModel;
import model.PlaceOrder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ControllerItems {
    public TableView table1;
    public TableColumn img1;
    public TableColumn name;
    public TableColumn qty;
    public TableView billTable;
    public TableColumn billItemName;
    public TableColumn bilItemSQTY;
    public TableColumn itemsPrice;
    public static int enterQty;
    public ItemsTMTM itemsTMTM;
    public int selectedIndex;
    public Label totalelbl;
    public RadioButton project;
    public boolean selected;

    public static void setEnterQty(int enterQty) {
        ControllerItems.enterQty = enterQty;
    }

    ObservableList<ItemsTMTM> data = FXCollections.observableArrayList();
    ObservableList<ItemsTMTM> billTableData = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        setTableData();
        setCellValueFactoryBillTable();

    }

    private void setCellValueFactory() {
        img1.setCellValueFactory(new PropertyValueFactory<>("img"));
        name.setCellValueFactory(new PropertyValueFactory<>("description"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void setTableData() {
        try {

            List<ItemsTM> all = ItemsModel.getAll();
            for (ItemsTM tem : all) {
                Image img1 = tem.getImg();
                ImageView img = new ImageView();
                img.setFitHeight(100);
                img.setFitWidth(100);
                img.setImage(img1);
                String description = tem.getDescription();
                data.add(new ItemsTMTM(img, description, tem.getQTY(), tem.getSelling_price(),tem.getItemCode()));
            }
            table1.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItemOnAction(MouseEvent mouseEvent) throws IOException {
        selectedIndex = table1.getSelectionModel().getSelectedIndex();
        itemsTMTM = data.get(selectedIndex);
        AddCartController.setItemsTMTM(itemsTMTM);

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/AddCart.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(table1.getScene().getWindow());
        stage.showAndWait();

        if (enterQty > 0) {
            setBillItems();
            calTotale();
        }
    }

    private void calTotale() {
        ItemsTMTM itemsTMTM1 = data.get(selectedIndex);
        double sallingPrice = itemsTMTM1.getSallingPrice();
        double itemTotal = enterQty * sallingPrice;
        double temp = Double.parseDouble(totalelbl.getText());
        double tot = temp + itemTotal;
        totalelbl.setText(tot + "");
        enterQty = 0;
    }

    private void setBillItems() {
        billTableData.add(new ItemsTMTM(itemsTMTM.getDescription(), enterQty, itemsTMTM.getSallingPrice() * enterQty,itemsTMTM.getItemCode(),itemsTMTM.getPurchase_price(),itemsTMTM.getSuppler_Id()));
        billTable.setItems(billTableData);
        billTable.refresh();
    }


    private void setCellValueFactoryBillTable() {
        billItemName.setCellValueFactory(new PropertyValueFactory<>("description"));
        bilItemSQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        itemsPrice.setCellValueFactory(new PropertyValueFactory<>("sallingPrice"));
    }

    public void barCodeCamOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/barcodeReade/BarcodeRead.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(table1.getScene().getWindow());
        stage.show();
    }

    public void playBillOnAction(MouseEvent mouseEvent) throws IOException {
        if (selected){
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/order/AddOder.fxml"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(table1.getScene().getWindow());
            stage.show();

        }else {
            try {
                String s = PlaceOrder.orderGetLastId();
                Order order = new Order();
                order.setId(s);
                boolean b = PlaceOrder.placeOrder(order, billTableData);
                if (b){
                    new Alert(Alert.AlertType.INFORMATION,"okay").showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }



            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/playBill.fxml"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(table1.getScene().getWindow());
            stage.show();
        }
    }


    public void projectOnAction(ActionEvent actionEvent) {
        selected = project.isSelected();
    }
}
