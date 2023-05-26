package controller.inventory;

import com.jfoenix.controls.JFXButton;
import dto.tm.ItemsTM;
import dto.tm.StockTM;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemsModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StockManageController {


    public AnchorPane stockManage;
    public TableView stockTable;
    public TableColumn code;
    public TableColumn description;
    public TableColumn qty;
    public TableColumn purchasePrice;
    public TableColumn sellingPrice;
    public TableColumn supplierId;
    public TableColumn action;
    public List<ItemsTM> all;
    ObservableList<StockTM> data= FXCollections.observableArrayList();
    public void initialize(){
        setCellValueFactory();
        setTableData();
    }

    private void setTableData() {
        try {


            all= ItemsModel.getAll();
            for (ItemsTM temp:all){
                Image img=new Image("/img/icons8-delete-100.png");
                ImageView imageView=new ImageView(img);
                imageView.setFitHeight(30);
                imageView.setPreserveRatio(true);
                JFXButton button=new JFXButton();
                button.setGraphic(imageView);
                setRemoveBtnOnAction(button,temp);
                data.add(new StockTM(temp.getItemCode(),temp.getDescription(),temp.getQTY(),temp.getSelling_price(),temp.getPurchase_price(),temp.getSupplier_id(),button));
            }
            stockTable.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRemoveBtnOnAction(JFXButton button, ItemsTM temp) {
        button.setOnAction((actionEvent -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (buttonType.orElse(no) == yes){
                String itemCode = temp.getItemCode();

                try {
                    boolean b = ItemsModel.deleteItem(itemCode);
                    if (b){
                        new Alert(Alert.AlertType.INFORMATION,"User Deleted Successful").showAndWait();
                        data.remove(temp);
                        stockTable.refresh();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,"User Delete Failed - Database Error").showAndWait();
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,"User Delete Failed - Driver Error").showAndWait();
                    e.printStackTrace();
                }
            }

        }));
    }

    public void addItemsOn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/inventory/AddInventory.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stockManage.getScene().getWindow());
        stage.show();
    }
    private void setCellValueFactory() {
        code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        qty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        purchasePrice.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("Purchase_price"));
        supplierId.setCellValueFactory(new PropertyValueFactory<>("suppler_Id"));
        action.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    public void supplierdetailsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplierdetails/SupplierDetails.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stockManage.getScene().getWindow());
        stage.show();
    }

    public void itemUpletOnAction(MouseEvent mouseEvent) {
        int selectedIndex = stockTable.getSelectionModel().getSelectedIndex();




    }
}
