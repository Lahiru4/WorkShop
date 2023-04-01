package util;

import controller.AddOdercontroller;
import controller.LogingPageController;
import controller.QuotationPageController;
import controller.MainDashBoardController;
import controller.admin.AdminLogPagecontroller;
import controller.cashier.CashierDashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import util.types.ControllerTypes;
import util.types.SceneTypes;
import util.types.ViewTypes;

import java.io.IOException;

public class ViewFactory {
    private static ViewFactory viewFactory;
    private final MainDashBoardController mainDashBoardController;
    private final AdminLogPagecontroller adminLogPagecontroller;
    private final Object customerDashboardController;
    private CashierDashboardController cashierDashboardController;
    private final LogingPageController logingPageController;
    private final AddOdercontroller addOdercontroller;
    private final QuotationPageController questionpagecontroller;
    private final Scene addOrderScene,questionScene,loginPageScene,cashierDashBoardScene,adminLoginScene,adminDashBoardScene,customerDashboardScene;
    private final Parent addOrderpage,questionpage,loginPage,cashierDashBoard,adminLogPage,adminDashBoard,customerDashboard;


    private ViewFactory() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/MainDashBoardFrom.fxml"));
        adminDashBoard=fxmlLoader1.load();
        mainDashBoardController =fxmlLoader1.getController();
        adminDashBoardScene = new Scene(adminDashBoard);

        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/view/admin/AdminLogPage.fxml"));
        adminLogPage=fxmlLoader2.load();
        adminLogPagecontroller = fxmlLoader2.getController();
        adminLoginScene=new Scene(adminLogPage);


        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/view/cashier/CashierDashboard.fxml"));
        cashierDashBoard=fxmlLoader3.load();
        cashierDashboardController = fxmlLoader3.getController();
        cashierDashBoardScene=new Scene(cashierDashBoard);

        FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("/view/LogingPage.fxml"));
        loginPage=fxmlLoader5.load();
        logingPageController = fxmlLoader5.getController();
        loginPageScene=new Scene(loginPage);

        FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("/view/AddOder.fxml"));
        addOrderpage=fxmlLoader6.load();
        addOdercontroller= fxmlLoader6.getController();
        addOrderScene=new Scene(addOrderpage);

        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("/view/QuotationPage.fxml"));
        questionpage=fxmlLoader7.load();
        questionpagecontroller= fxmlLoader7.getController();
        questionScene=new Scene(questionpage);

        FXMLLoader fxmlLoader8=new FXMLLoader(getClass().getResource("/view/CustromerManageFrom.fxml"));
        customerDashboard=fxmlLoader8.load();
        customerDashboardController=fxmlLoader8.getController();
        customerDashboardScene=new Scene(customerDashboard);
    }

    public static ViewFactory getInstance() throws IOException {
        if(viewFactory ==null) viewFactory =new ViewFactory();
        return viewFactory;
    }

    public Parent getView(ViewTypes viewTypes){
        switch (viewTypes){
            case ADMIN_DASHBOARD:return adminDashBoard;
            case ADMIN_LOGIN_PAGE:return adminLogPage;
            case CASHIER_DASHBOARD:return cashierDashBoard;
            case LOGIN_PAGE:return loginPage;
            case ADD_ORDER:return addOrderpage;
            case Question_PAGE:return questionpage;
            case CUSTOMER_DASHBOARD:return customerDashboard;
        }
        return null;
    }

    public <T>T getController(ControllerTypes controllerTypes){
        switch (controllerTypes){
            case ADMIN_DASHBOARD:return(T) mainDashBoardController;
            case ADMIN_LOGIN_PAGE:return (T)adminLogPagecontroller;
            case CASHIER_DASHBOARD:return (T)cashierDashboardController;
            case LOGIN_PAGE:return (T)logingPageController;
            case ADD_ORDER:return (T) addOdercontroller;
            case Question_PAGE:return (T) questionpagecontroller;
            case CUSTOMER_DASHBOARD:return (T) customerDashboardController;
        }
        return null;
    }

    public Scene getScene(SceneTypes sceneType){
        switch (sceneType){
            case LOGIN_PAGE:return loginPageScene;
            case CASHIER_DASHBOARD:return cashierDashBoardScene;
            case ADMIN_DASHBOARD:return adminDashBoardScene;
            case ADMIN_LOGIN_PAGE:return adminLoginScene;
            case ADD_ORDER:return addOrderScene;
            case Question_PAGE:return questionScene;
            case CUSTOMER_DASHBOARD:return customerDashboardScene;
        }
        return null;
    }
}
