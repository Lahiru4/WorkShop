package controller.employee;

import com.jfoenix.controls.JFXButton;
import dto.Employee;
import dto.tm.EmployeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.EmployeeModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {

    public JFXButton employRegisterBtn;
    public TableColumn id;
    public TableColumn name;
    public TableColumn gmail;
    public TableColumn phnumber;
    public TableColumn id_number;
    public TableColumn r_date;
    public TableColumn position;
    public TableColumn action;
    public TableColumn address;
    public TableView empTable;
    ObservableList<EmployeeTM> data = FXCollections.observableArrayList();


    public void empRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee/AddEmployee.fxml"))));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(employRegisterBtn.getScene().getWindow());
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        setTableData();

    }

    private void setTableData() {
        List<Employee> all = EmployeeModel.getAll();

        for (Employee temp : all) {
            Image img = new Image("/img/icons8-cancel-50.png");
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(30);
            imageView.setPreserveRatio(true);
            JFXButton button = new JFXButton();
            button.setGraphic(imageView);

            setRemoveBtnOnAction(button, temp);
            data.add(new EmployeeTM(temp.getEmpId(),temp.getEmpName(),temp.getGmail(),temp.getNumber(),temp.getID_number(),
                    temp.getRegister_date(),temp.getAddress(),temp.getPosition(),button));
        }
        empTable.setItems(data);
        //cusTable.refresh();
    }

    private void setRemoveBtnOnAction(JFXButton button, Employee temp) {
        button.setOnAction((actionEvent -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (buttonType.orElse(no) == yes) {
                try {

                    boolean b = EmployeeModel.deleteEmployee(temp.getEmpId());
                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION,"Employee Deleted Successful").showAndWait();
                    }
                    data=null;
                    data=FXCollections.observableArrayList();
                    setTableData();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,"Employee Delete Failed - Database Error").showAndWait();
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,"Employee Delete Failed - Driver Error").showAndWait();
                    throw new RuntimeException(e);
                }

            }

        }));
    }

    private void setCellValueFactory() {
        id.setCellValueFactory(new PropertyValueFactory<>("empId"));
        name.setCellValueFactory(new PropertyValueFactory<>("empName"));
        gmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        phnumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        id_number.setCellValueFactory(new PropertyValueFactory<>("iD_number"));
        r_date.setCellValueFactory(new PropertyValueFactory<>("register_date"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        action.setCellValueFactory(new PropertyValueFactory<>("button"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

}
