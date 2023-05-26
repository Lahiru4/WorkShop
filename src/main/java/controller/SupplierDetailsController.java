package controller;

import com.jfoenix.controls.JFXButton;
import dto.Supplier;
import dto.tm.SupplierTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.SupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierDetailsController implements Initializable {
    public TableView supData;
    public TableColumn supId;
    public TableColumn name;
    public TableColumn address;
    public TableColumn phNumber;
    public TableColumn gmail;
    public TableColumn btn;

    private void setCellValueFactory() {
        supId.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        gmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        btn.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        setTableDzzata();
    }

    private void setTableDzzata() {
        try {
            List<Supplier> all = SupplierModel.getAll();



            ObservableList<SupplierTm> data= FXCollections.observableArrayList();
            for (Supplier temp:all){
                Image img=new Image("/img/icons8-delete-100.png");
                ImageView imageView=new ImageView(img);
                imageView.setFitHeight(30);
                imageView.setPreserveRatio(true);
                JFXButton button=new JFXButton();
                button.setGraphic(imageView);
                data.add(new SupplierTm(temp.getId(),temp.getName(),temp.getAddress(),temp.getNumber(),temp.getGmail(),button));
            }

            supData.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSupOnAction(ActionEvent actionEvent) {

    }
}
