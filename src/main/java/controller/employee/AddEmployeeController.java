package controller.employee;

import dto.Employee;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.EmployeeModel;

import java.sql.SQLException;

public class AddEmployeeController {


    public TextField empId;
    public TextField gmail;
    public TextField empAddress;
    public TextField empName;
    public TextField empNumber;
    public TextField empPosition;
    public TextField date;
    public TextField idNumber;

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
        String empPositionText = empPosition.getText();
        return new Employee(idText,empNameText,gmailText,empNumberText,idNumberText,dateText,empAddressText,empPositionText);
    }


}
