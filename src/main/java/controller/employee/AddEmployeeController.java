package controller.employee;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.event.ActionEvent;

public class AddEmployeeController {
    public JFXTextField empId;
    public JFXTextField empName;
    public JFXTextField empNumber;
    public JFXTextField empPosition;
    public JFXTextField iDnumber;
    public JFXTextField empAddress;

    public void saveEmp(ActionEvent actionEvent) {

    }
    public Employee clectedDeta(){
        return new Employee(empId.getText(),empName.getText(),empNumber.getText(),empAddress.getText(),empPosition.getText()
                ,iDnumber.getText());
    }
}
