package controller.order;

import com.jfoenix.controls.JFXTextArea;
import dto.Order;
import dto.tm.ItemTM2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PlaceOrder;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddOdercontroller {


    public DatePicker date;
    public Label orderDate;
    public TextField orderId;
    public Label itemcost;
    public TextField workrent;
    public Label total;
    public JFXTextArea addOrderDescription;
    public DatePicker retanDate;
    public static ObservableList<ItemTM2> billTableData = FXCollections.observableArrayList();
    public TextField cusId;

    public static void setBillTableData(ObservableList<ItemTM2> billTableData) {
        AddOdercontroller.billTableData = billTableData;
    }

    public void initialize() {
        orderDate.setText(String.valueOf(LocalDate.now()));
        try {
            String s = genOrderId();
            orderId.setText(s);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public static Order collectData() {
        String id = "";
        String description = "";
        String order_date = "2002.02.12";
        String return_date = "2002.12.02";
        double work_rent = 122.2;
        double item_cost = 111.25;
        String customer_Id = "";
        return new Order(id, description, order_date, return_date, work_rent, item_cost, customer_Id);
    }

    public void saveOrder(ActionEvent actionEvent) throws IOException {
        try {
            boolean b = PlaceOrder.placeOrder(colectData(), billTableData);
            if (b) {
                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-ok-48.png")))
                        .text("order success ")
                        .title("success")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();
            } else {
                new Alert(Alert.AlertType.ERROR, "fall").showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/playBill.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(date.getScene().getWindow());
        stage.show();
    }

    public Order colectData() {
        orderId.getText();
        addOrderDescription.getText();
        orderDate.getText();
        retanDate.getValue();
        workrent.getText();
        cusId.getText();

        Order order = new Order(orderId.getText(), addOrderDescription.getText(), orderDate.getText(), String.valueOf(retanDate.getValue()), Double.parseDouble(workrent.getText()), 00, cusId.getText());
        return order;
    }
}
