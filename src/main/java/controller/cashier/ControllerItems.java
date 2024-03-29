package controller.cashier;

import com.jfoenix.controls.JFXButton;
import controller.barcodeReade.BarcodeReadController;
import controller.order.AddOdercontroller;
import dto.Order;
import dto.tm.ItemTM2;
import dto.tm.ItemsTM;
import dto.tm.ItemsTMTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.ItemsModel;
import model.PlaceOrder;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public static ItemsTMTM itemsTMTM;
    public int selectedIndex;
    public Label totalelbl;
    public RadioButton project;
    public boolean selected;
    public TableColumn action;

    public static void setEnterQty(int enterQty) {
        ControllerItems.enterQty = enterQty;
    }

    public static void setItemsTMTM(ItemsTMTM itemsTMTM) {
        ControllerItems.itemsTMTM = itemsTMTM;
    }

    ObservableList<ItemsTMTM> data = FXCollections.observableArrayList();
    ObservableList<ItemTM2> billTableData = FXCollections.observableArrayList();

    public void setBillTableDataCall() {
        if (enterQty > 0) {
            setBillItems();
            calTotale();
        }
    }

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
                data.add(new ItemsTMTM(img, description, tem.getQTY(), tem.getSelling_price(), tem.getItemCode()));
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
        Image img=new Image("/img/icons8-delete-100.png");
        ImageView imageView=new ImageView(img);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        JFXButton button=new JFXButton();
        button.setGraphic(imageView);
        billTableData.add(new ItemTM2(itemsTMTM.getDescription(), enterQty, itemsTMTM.getSallingPrice(), itemsTMTM.getItemCode(), itemsTMTM.getPurchase_price(), itemsTMTM.getSuppler_Id(), button));
        billTable.setItems(billTableData);
        billTable.refresh();
        setRemoveBtnOnAction(button);
    }

    private void setRemoveBtnOnAction(JFXButton button) {

    }


    private void setCellValueFactoryBillTable() {
        billItemName.setCellValueFactory(new PropertyValueFactory<>("description"));
        bilItemSQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        itemsPrice.setCellValueFactory(new PropertyValueFactory<>("sallingPrice"));
        action.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    public void barCodeCamOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/barcodeReade/BarcodeRead.fxml"));
        Parent load = fxmlLoader.load();
        stage.setScene(new Scene(load));
        BarcodeReadController controller = fxmlLoader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(table1.getScene().getWindow());
        controller.setControllerItem(this);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                controller.getService().cancel();
            }
        });
        stage.show();
    }

    public void playBillOnAction(MouseEvent mouseEvent) throws IOException {
        if (selected) {
            //
            AddOdercontroller.setBillTableData(billTableData);



            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/order/AddOder.fxml"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(table1.getScene().getWindow());
            stage.show();

        } else {
            try {
                String date = String.valueOf(LocalDate.now());
                String s = genOrderId();

                Order order = new Order(s,"",date,"0000-00-00",00,00,"CC007=DEMO");

                boolean b = PlaceOrder.placeOrder(order, billTableData);
                if (b) {

                    Notifications.create()
                            .graphic(new ImageView(new Image("/img/icons8-ok-48.png")))
                            .text("order success ")
                            .title("success")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .show();

                    billTableData.clear();
                    billTable.refresh();
                    PlayBillController.setOrId(s);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/playBill.fxml"))));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(table1.getScene().getWindow());
                    stage.show();

                } else {
                    Notifications.create()
                            .graphic(new ImageView(new Image("/img/icons8-fail-48.png")))
                            .text("order Fail ")
                            .title("Fail")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .show();
                }
            } catch (SQLException e) {
                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-fail-48.png")))
                        .text("order Fail ")
                        .title("Fail")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    private String genOrderId() throws SQLException, ClassNotFoundException {
        String lastId = PlaceOrder.orderGetLastId();
        String id;
        if (lastId == null) {
            id = "ORD-0001";
        } else {
            String[] split = lastId.split("[ORD][-]");
            int i = Integer.parseInt(split[1]);
            i++;
            id = String.format("ORD-%04d", i);
        }

        return id;
    }


    public void projectOnAction(ActionEvent actionEvent) {
        selected = project.isSelected();
    }
}
