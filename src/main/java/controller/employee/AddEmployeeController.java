package controller.employee;

import com.jfoenix.controls.JFXButton;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.EmployeeModel;
import org.controlsfx.control.Notifications;
import regex.Regex;

import java.sql.SQLException;

public class AddEmployeeController {



    public TextField gmail;
    public TextField empAddress;
    public TextField empName;
    public TextField empNumber;

    public TextField idNumber;
    public ComboBox empPositionCombox;
    public static String updateEmpId;
    public DatePicker date;
    public JFXButton update;
    public JFXButton save;
    public TextField empId;
    public Label lblEMpId;


    public static void setUpdateEmpId(String updateEmpId) {
        AddEmployeeController.updateEmpId = updateEmpId;
    }

    ObservableList<String> comBoxData = FXCollections.observableArrayList();

    public void initialize() {
        setDataCombox();

        if (updateEmpId.equals("")) {
            empId.setText("");
        } else {
            empId.setText(updateEmpId);
        }
        setEmpId();
    }

    private void dataVelideshan() {
        save.setDisable(true);
        if (empPositionCombox.getSelectionModel().getSelectedItem()!=null&&empId.getText()!=null&&gmail.getText()!=null&&empAddress.getText()!=null
                &&empName.getText()!=null&&empNumber.getText()!=null&&date.getValue()!=null&&idNumber.getText()!=null) {
            save.setDisable(false);
        }
    }

    static {
        updateEmpId = "";
    }

    public void saveEmp(ActionEvent actionEvent) {
        try {
            boolean b = EmployeeModel.saveEmployee(clectedDeta());
            if (b) {
                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-ok-48.png")))
                        .text("ADD EMPLOYEE SUCCESS")
                        .title("SUCCESS")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();
                texClear();


            } else {
                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-fail-48")))
                        .text("EMPLOYEE ADDING FALL")
                        .title("FALL")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Employee clectedDeta() {
        String idText = empId.getText();
        String empNameText = empName.getText();
        String gmailText = gmail.getText();
        String empNumberText = empNumber.getText();
        String idNumberText = idNumber.getText();
        String dateText = String.valueOf(date.getValue());
        String empAddressText = empAddress.getText();
        String empPositionText = comBoxData.get(empPositionCombox.getSelectionModel().getSelectedIndex());
        return new Employee(idText, empNameText, gmailText, empNumberText, idNumberText, dateText, empAddressText, empPositionText);
    }

    public void setDataCombox() {
        comBoxData.add("Admin");
        comBoxData.add("Cashier");
        comBoxData.add("Manager");
        comBoxData.add("Worker");
        empPositionCombox.setItems(comBoxData);
    }


    public void setEmpId() {
        try {
            String s = jenEmpId();
            empId.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String jenEmpId() throws SQLException, ClassNotFoundException {
        String lastId = EmployeeModel.getLastEmployeeId();
        String new_id;
        if (lastId.equals("")) {
            new_id = "EMP-1";
        } else {
            String[] split = lastId.split("[EMP][-]");
            int i = Integer.parseInt(split[1]);
            i++;
            new_id = String.format("EMP-" + i);
        }
        return new_id;
    }

    public void updateOnActin(ActionEvent actionEvent) {
        Employee employee = clectedDeta();
        try {
            boolean b = EmployeeModel.UpdateEmploy(employee, updateEmpId);
            if (b) {
                new Alert(Alert.AlertType.INFORMATION,"OKAY").show();
                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-fail-48")))
                        .text("ADD EMPLOYEE SUCCESS")
                        .title("SUCCESS")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();
                texClear();
                //empId.getScene().getWindow().hide();
            } else {

                Notifications.create()
                        .graphic(new ImageView(new Image("/img/icons8-fail-48")))
                        .text("EMPLOYEE UPDATE FALL")
                        .title("FALL")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void texClear() {
        empId.setText(null);
        gmail.clear();
        empAddress.clear();
        empName.clear();
        empNumber.clear();
        date.setValue(null);
        idNumber.clear();
        empPositionCombox.setSelectionModel(null);

    }

    public void velidetOnAction(KeyEvent keyEvent) {
        if (Regex.gmail(gmail.getText())) {
            gmail.setStyle("-fx-border-color: rgba(112,241,112,0.8)");
        } else {
            gmail.setStyle("-fx-border-color: red");
        }
    }

    public void empIdNumberVelidetOnAction(KeyEvent keyEvent) {

    }

    public void empAddressVelidetOnAction(KeyEvent keyEvent) {

    }

    public void empNameVelidetOnAction(KeyEvent keyEvent) {

        if (Regex.name(empName.getText())) {
            empName.setStyle("-fx-border-color: rgba(112,241,112,0.8)");
        } else {
            empName.setStyle("-fx-border-color: red");
        }
    }

    public void empPhoneNumberVelidetOnAction(KeyEvent keyEvent) {
        if (Regex.phoneNumber(empNumber.getText())) {
            empNumber.setStyle("-fx-border-color: rgba(112,241,112,0.8)");
        } else {
            empNumber.setStyle("-fx-border-color: red");
        }
    }
}
