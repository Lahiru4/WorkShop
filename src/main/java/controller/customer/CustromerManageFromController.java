package controller.customer;

import com.jfoenix.controls.JFXButton;
import dto.Customer;
import dto.tm.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CustomerModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustromerManageFromController {
    public JFXButton addCustomer;
    public TableView cusTable;
    public TableColumn cusid;
    public TableColumn cusName;
    public TableColumn cusPhNumber;
    public TableColumn cusAddress;
    public TableColumn cusGmail;
    public TableColumn action;
    public AnchorPane customerDashboard;
    private ObservableList<CustomerTM> data= FXCollections.observableArrayList();

    public void initialize(){
        setTableData();
        setCellValueFactory();
    }
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer/AddCustromerFrom.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addCustomer.getScene().getWindow());
        stage.show();
    }
    public  void setTableData(){
        List<Customer> all = CustomerModel.getAll();
        ObservableList<CustomerTM> dataTemp= FXCollections.observableArrayList();

        for (Customer temp:all) {
            Image img=new Image("/img/icons8-delete-100.png");
            ImageView imageView=new ImageView(img);
            imageView.setFitHeight(30);
            imageView.setPreserveRatio(true);
            JFXButton button=new JFXButton();
            button.setGraphic(imageView);

            setRemoveBtnOnAction(button,temp);
            dataTemp.add(new CustomerTM(temp.getCusID(),temp.getCusName(),temp.getCusNumber(),temp.getCusAddress(),
                    temp.getCusGmail(),button));
        }
        data=dataTemp;
        cusTable.setItems(data);
        cusTable.refresh();

    }

    private void setRemoveBtnOnAction(JFXButton button,Customer temp) {
        button.setOnAction((actionEvent -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (buttonType.orElse(no) == yes){
                String cusID = temp.getCusID();

                try {
                    boolean b = CustomerModel.deleteCustomer(cusID);
                    if (b){
                        ImageView imageView = new ImageView(new Image("/img/icons8-ok-48.png"));
                        Notifications.create()
                                .graphic(imageView)
                                .text(" Deleted Successful ")
                                .title("Successful")
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT)
                                .darkStyle()
                                .show();
                        setTableData();

                        data.remove(temp);
                        cusTable.refresh();
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

    private void setCellValueFactory() {
        cusid.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        cusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        cusPhNumber.setCellValueFactory(new PropertyValueFactory<>("cusNumber"));
        cusAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        cusGmail.setCellValueFactory(new PropertyValueFactory<>("cusGmail"));
        action.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    public void restatBtnOnAction(ActionEvent actionEvent) {
        cusTable.refresh();
    }

    public void cusUpleOnAction(MouseEvent mouseEvent) throws IOException {
        int selectedIndex = cusTable.getSelectionModel().getSelectedIndex();
        String cusID = data.get(selectedIndex).getCusID();
        AddCustromerFromController.setUpdate_cus_ID(cusID);

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer/AddCustromerFrom.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addCustomer.getScene().getWindow());
        stage.show();


    }
}