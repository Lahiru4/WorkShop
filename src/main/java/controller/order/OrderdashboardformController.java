package controller.order;

import dto.Order;
import dto.OrderCustomerQuotationJoinDto;
import dto.tm.OrderTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.QueryModel;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderdashboardformController implements Initializable {
    public TableColumn<OrderCustomerQuotationJoinDto,String> qID;
    public TableColumn<?,?> workRent;
    public TableColumn<?,?> itemCost;
    public TableColumn<?,?> returnDate;
    public TableColumn<?,?> placeDate;
    public TableColumn<?,?> cusID;
    public TableColumn<?,?> orderID;
    public TableView orderTable;
    public AnchorPane oderDashboard;

    private void getAll() {
        try {
            List<Order> all = QueryModel.getAll();
            ObservableList<OrderTM> obList = FXCollections.observableArrayList();

            for(Order order : all) {
                obList.add(new OrderTM(order.getId(),order.getOrder_date(),order.getReturn_date(),order.getWork_rent(),order.getItem_cost(),order.getCustomer_Id(),"S001"));

            }
            orderTable.setItems(obList);
            orderTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"SQL ERROR").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        orderID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusID.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        placeDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("work_rent"));
        itemCost.setCellValueFactory(new PropertyValueFactory<>("item_cost"));
        workRent.setCellValueFactory(new PropertyValueFactory<>("work_rent"));
        qID.setCellValueFactory(new PropertyValueFactory<>("qID"));
        /*private String id;
        private String description;
        private String order_date;
        private String return_date;
        private double work_rent;
        private double item_cost;
        private String customer_Id;*/
    }
    public void addOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(ViewFactory.getInstance().getScene(SceneTypes.ADD_ORDER));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(oderDashboard.getScene().getWindow());
        stage.show();
    }

    public void quotationOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(ViewFactory.getInstance().getScene(SceneTypes.Question_PAGE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(oderDashboard.getScene().getWindow());
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
}
