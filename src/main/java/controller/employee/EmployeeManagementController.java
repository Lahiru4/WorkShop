package controller.employee;

import com.jfoenix.controls.JFXButton;
import dto.Employee;
import dto.tm.EmployeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.EmployeeModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeManagementController {

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employee/AddEmployee.fxml"));
        Parent parent=fxmlLoader.load();
        stage.setScene(new Scene(parent));
        AddEmployeeController controller = fxmlLoader.getController();
        controller.update.setVisible(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(employRegisterBtn.getScene().getWindow());
        stage.show();

    }
    public void initialize() {
        setCellValueFactory();
        setTableData();

    }

    private void setTableData() {
        List<Employee> all = EmployeeModel.getAll();

        for (Employee temp : all) {
            Image img = new Image("/img/icons8-delete-100.png");
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
        position.setCellValueFactory(new PropertyValueFactory<>("address"));
        action.setCellValueFactory(new PropertyValueFactory<>("button"));
        address.setCellValueFactory(new PropertyValueFactory<>("position"));
    }


    public void empUpletOnAction(MouseEvent mouseEvent) throws IOException {
        int selectedIndex = empTable.getSelectionModel().getSelectedIndex();
        String empId = data.get(selectedIndex).getEmpId();
        AddEmployeeController.setUpdateEmpId(empId);

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employee/AddEmployee.fxml"));
        Parent parent=fxmlLoader.load();
        AddEmployeeController controller = fxmlLoader.getController();
        controller.save.setVisible(false);
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(employRegisterBtn.getScene().getWindow());
        stage.show();

        /*FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/view/admin/AdminLogPage.fxml"));
        adminLogPage = fxmlLoader2.load();
        adminLogPagecontroller = fxmlLoader2.getController();
        adminLoginScene = new Scene(adminLogPage);*/
    }

    public void saveEmployeeDetailsOnAction(ActionEvent actionEvent) {
        /*FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(empTable.getScene().getWindow());
        String absolutePath = file.getParentFile().getAbsolutePath();*/

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");

        //ObservableList<TaxTM> data = tableTax.getItems();
        List<Employee> all = EmployeeModel.getAll();

        for (int i = 0; i < all.size(); i++) {
            Employee rowData = all.get(i);
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(rowData.getEmpId());

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(rowData.getEmpName());

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(rowData.getNumber());

            Cell cell3 = row.createCell(3);
            cell2.setCellValue(rowData.getID_number());

            Cell cell4 = row.createCell(4);
            cell2.setCellValue(rowData.getRegister_date());

            Cell cell5 = row.createCell(5);
            cell2.setCellValue(rowData.getPosition());

            Cell cell6 = row.createCell(6);
            cell2.setCellValue(rowData.getAddress());

            Cell cell7 = row.createCell(7);
            cell2.setCellValue(rowData.getGmail());

            /*Cell cell1 = row.createCell(0);
            *//*cell1.setCellValue(rowData.getId());*//*

            Cell cell2 = row.createCell(1);
            *//*cell2.setCellValue(rowData.getName());*//*

            Cell cell3 = row.createCell(2);
            *//*cell3.setCellValue(rowData.getPercentage());*//*

            Cell cell4 = row.createCell(3);
            *//*cell4.setCellValue(rowData.getDescription());*/

        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showSaveDialog(employRegisterBtn.getScene().getWindow());
        if (file == null) {

        }

    }

    public void employSalaryOnAction(ActionEvent actionEvent) {

    }
}
