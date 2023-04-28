package controller.order;

import dto.OrderCustomerQuotationJoinDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.sql.SQLException;
import java.util.List;

public class OrderdashboardformController {
    public TableColumn<OrderCustomerQuotationJoinDto,String> qID;
    public TableColumn<?,?> workRent;
    public TableColumn<?,?> itemCost;
    public TableColumn<?,?> returnDate;
    public TableColumn<?,?> placeDate;
    public TableColumn<?,?> cusID;
    public TableColumn<?,?> orderID;
    public TableView orderTable;
    public AnchorPane oderDashboard;
    public void initialize(){
        getAll();
        setCellValueFactory();
    }
    private void getAll() {
        try {
            List<OrderCustomerQuotationJoinDto> all = QueryModel.getAll();
            ObservableList<OrderCustomerQuotationJoinDto> obList = FXCollections.observableArrayList();

            for(OrderCustomerQuotationJoinDto order : all) {
                obList.add(new OrderCustomerQuotationJoinDto(
                        order.getOrderId(),
                        order.getOrderDescription(),
                        order.getOrderDate(),
                        order.getReturnOrderDate(),
                        order.getWorkRent(),
                        order.getItemCost(),
                        order.getCusId(),
                        order.getCusName(),
                        order.getCusPhoneNumber(),
                        order.getCusAddress(),
                        order.getCusGmail(),
                        "SS002"

                ));
                //sup
            }
            orderTable.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"SQL ERROR").show();
        }
    }

    private void setCellValueFactory() {
        orderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        cusID.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        placeDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnOrderDate"));
        itemCost.setCellValueFactory(new PropertyValueFactory<>("itemCost"));
        workRent.setCellValueFactory(new PropertyValueFactory<>("workRent"));
        qID.setCellValueFactory(new PropertyValueFactory<>("qID"));
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
}
