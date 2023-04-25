package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import controller.employee.EmployeeManagementController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;

public class MainDashBoardController {
    public Label lblOder;
    public Label reportsLabel;
    public Label stockLabel;
    public Label customerLabel;
    public Label logoutLabel;
    public Label profitlabel;
    public Label employeeLabel;

    public AnchorPane adminDashboard;
    public JFXButton homeBtn;
    public JFXButton customerBtn;
    public JFXButton orderBtn;
    public JFXButton reportBtn;
    public JFXButton employeeBtn;
    public JFXButton stockBtn;
    public AnchorPane manDash;
    public static EmployeeManagementController employeeManafecontroller;

    public void initialize() throws IOException {
        homeBtn.getStyleClass().removeAll("orderBtn2");
        homeBtn.getStyleClass().add("orderBtn");
        try {
            homeSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void homeSet() throws IOException {
        Node resource = FXMLLoader.load(getClass().getResource("/view/home/DashBoardFrom.fxml"));
        manDash.getChildren().clear();
        manDash.getChildren().add(resource);
    }


    public void onHome(ActionEvent actionEvent) {
        try {
            homeSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeBtn.getStyleClass().removeAll("orderBtn2");
        homeBtn.getStyleClass().add("orderBtn");

        employeeBtn.getStyleClass().removeAll("orderBtn");
        employeeBtn.getStyleClass().add("orderBtn2");

        customerBtn.getStyleClass().removeAll("orderBtn");
        customerBtn.getStyleClass().add("orderBtn2");

        stockBtn.getStyleClass().removeAll("orderBtn");
        stockBtn.getStyleClass().add("orderBtn2");

        reportBtn.getStyleClass().removeAll("orderBtn");
        reportBtn.getStyleClass().add("orderBtn2");

        orderBtn.getStyleClass().removeAll("orderBtn");
        orderBtn.getStyleClass().add("orderBtn2");
    }

    public void onOrder(ActionEvent actionEvent) throws IOException {
        Node node=FXMLLoader.load(getClass().getResource("/view/order/Order_dashboard_form.fxml"));
        manDash.getChildren().clear();
        manDash.getChildren().add(node);

        orderBtn.getStyleClass().removeAll("orderBtn2");
        orderBtn.getStyleClass().add("orderBtn");

        employeeBtn.getStyleClass().removeAll("orderBtn");
        employeeBtn.getStyleClass().add("orderBtn2");

        customerBtn.getStyleClass().removeAll("orderBtn");
        customerBtn.getStyleClass().add("orderBtn2");

        stockBtn.getStyleClass().removeAll("orderBtn");
        stockBtn.getStyleClass().add("orderBtn2");

        reportBtn.getStyleClass().removeAll("orderBtn");
        reportBtn.getStyleClass().add("orderBtn2");

        homeBtn.getStyleClass().removeAll("orderBtn");
        homeBtn.getStyleClass().add("orderBtn2");

    }

    public void onCustomer(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/customer/CustromerManageFrom.fxml"));
        //if (manDash.getChildren()==node) {
            manDash.getChildren().clear();
            manDash.getChildren().add(node);
        //}

        customerBtn.getStyleClass().removeAll("orderBtn2");
        customerBtn.getStyleClass().add("orderBtn");

        orderBtn.getStyleClass().removeAll("orderBtn");
        orderBtn.getStyleClass().add("orderBtn2");

        employeeBtn.getStyleClass().removeAll("orderBtn");
        employeeBtn.getStyleClass().add("orderBtn2");

        stockBtn.getStyleClass().removeAll("orderBtn");
        stockBtn.getStyleClass().add("orderBtn2");

        reportBtn.getStyleClass().removeAll("orderBtn");
        reportBtn.getStyleClass().add("orderBtn2");

        homeBtn.getStyleClass().removeAll("orderBtn");
        homeBtn.getStyleClass().add("orderBtn2");





    }

    public void onReport(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/report/ReportManageFrom.fxml"));
        manDash.getChildren().clear();
        manDash.getChildren().addAll(node);

        reportBtn.getStyleClass().removeAll("orderBtn2");
        reportBtn.getStyleClass().add("orderBtn");

        orderBtn.getStyleClass().removeAll("orderBtn");
        orderBtn.getStyleClass().add("orderBtn2");

        employeeBtn.getStyleClass().removeAll("orderBtn");
        employeeBtn.getStyleClass().add("orderBtn2");

        customerBtn.getStyleClass().removeAll("orderBtn");
        customerBtn.getStyleClass().add("orderBtn2");

        homeBtn.getStyleClass().removeAll("orderBtn");
        homeBtn.getStyleClass().add("orderBtn2");

        stockBtn.getStyleClass().removeAll("orderBtn");
        stockBtn.getStyleClass().add("orderBtn2");
    }

    public void onEmployee(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/view/employee/EmployeeManagement.fxml"));
        Node load = fxmlLoader.load();
        employeeManafecontroller = fxmlLoader.getController();
        manDash.getChildren().clear();
        manDash.getChildren().addAll(load);

        employeeBtn.getStyleClass().removeAll("orderBtn2");
        employeeBtn.getStyleClass().add("orderBtn");

        orderBtn.getStyleClass().removeAll("orderBtn");
        orderBtn.getStyleClass().add("orderBtn2");

        homeBtn.getStyleClass().removeAll("orderBtn");
        homeBtn.getStyleClass().add("orderBtn2");

        customerBtn.getStyleClass().removeAll("orderBtn");
        customerBtn.getStyleClass().add("orderBtn2");

        stockBtn.getStyleClass().removeAll("orderBtn");
        stockBtn.getStyleClass().add("orderBtn2");

        reportBtn.getStyleClass().removeAll("orderBtn");
        reportBtn.getStyleClass().add("orderBtn2");
    }
    public void onStock(ActionEvent actionEvent) throws IOException {
        Node node=FXMLLoader.load(getClass().getResource("/view/inventory/StockManage.fxml"));
        manDash.getChildren().clear();
        manDash.getChildren().addAll(node);

        stockBtn.getStyleClass().removeAll("orderBtn2");
        stockBtn.getStyleClass().add("orderBtn");

        orderBtn.getStyleClass().removeAll("orderBtn");
        orderBtn.getStyleClass().add("orderBtn2");

        employeeBtn.getStyleClass().removeAll("orderBtn");
        employeeBtn.getStyleClass().add("orderBtn2");

        customerBtn.getStyleClass().removeAll("orderBtn");
        customerBtn.getStyleClass().add("orderBtn2");

        homeBtn.getStyleClass().removeAll("orderBtn");
        homeBtn.getStyleClass().add("orderBtn2");

        reportBtn.getStyleClass().removeAll("orderBtn");
        reportBtn.getStyleClass().add("orderBtn2");

    }



    public void logOutBtnOn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) manDash.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(ViewFactory.getInstance().getScene(SceneTypes.LOGIN_PAGE));
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.show();

    }


}
