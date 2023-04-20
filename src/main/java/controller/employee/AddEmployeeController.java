package controller.employee;

import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public class AddEmployeeController {


    public TextField empId;
    public TextField gmail;
    public TextField empAddress;
    public TextField empName;
    public TextField empNumber;
    public TextField empPosition;
    public TextField date;
    public TextField idNumber;
    public ComboBox empPositionCombox;
    public static String updateEmpId;

    public static void setUpdateEmpId(String updateEmpId) {
        AddEmployeeController.updateEmpId = updateEmpId;
    }

    ObservableList<String> comBoxData= FXCollections.observableArrayList();

    public void initialize(){
        setDataCombox();
        if (updateEmpId.equals("")){
            empId.setText("");
        }else {
            empId.setText(updateEmpId);
        }
    }
    static {
        updateEmpId="";
    }
    public void saveEmp(ActionEvent actionEvent) {
        try {
            boolean b = EmployeeModel.saveEmployee(clectedDeta());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "ADD EMPLOYEE SUCCESS").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "EMPLOYEE ADDING FALL").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, " Failed - Driver Error").showAndWait();
            throw new RuntimeException(e);
        }

    }

    public Employee clectedDeta() {
        String idText = empId.getText();
        String empNameText = empName.getText();
        String gmailText = gmail.getText();
        String empNumberText = empNumber.getText();
        String idNumberText = idNumber.getText();
        String dateText = date.getText();
        String empAddressText = empAddress.getText();
        String empPositionText = comBoxData.get(empPositionCombox.getSelectionModel().getSelectedIndex());
        return new Employee(idText,empNameText,gmailText,empNumberText,idNumberText,dateText,empAddressText,empPositionText);
    }
    public void setDataCombox(){
        comBoxData.add("Admin");
        comBoxData.add("Cashier");
        comBoxData.add("Manager");
        comBoxData.add("Worker");
        empPositionCombox.setItems(comBoxData);
    }


    public void artoOnAction(ActionEvent actionEvent) {
        List<Employee> all = EmployeeModel.getAll();
        int size = all.size();

        int integer = Integer.valueOf(all.get(size - 1).getEmpId());
        integer++;
        empId.setText("000"+integer);
    }

    public void updateOnActin(ActionEvent actionEvent) {
        Employee employee = clectedDeta();
        try {
            boolean b = EmployeeModel.UpdateEmploy(employee, updateEmpId);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Employee Update Success").showAndWait();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Employee Update Fall").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
