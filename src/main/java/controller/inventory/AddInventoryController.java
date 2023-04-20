package controller.inventory;

import com.jfoenix.controls.JFXButton;
import dto.Items;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.ItemsModel;
import model.SupplierModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddInventoryController {

    public ImageView imgView;
    public TextField itemCode;
    public TextField description;
    public TextField qty;
    public TextField sellingprice;
    public TextField purchaseprice;
    public JFXButton arto;
    public JFXButton save;
    public Image image;
    public JFXButton addimg;
    public FileInputStream fileInputStream;
    public ComboBox supplierIdComBox;
    public TextField supName;
    public TextField supAddress;
    public TextField supnumber;
    public TextField supGmail;
    ObservableList<String> comData= FXCollections.observableArrayList();
    List<Supplier> all;

    public void initialize(){
        supplierIdComBoxsetData();
    }

    public void addImg(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file =chooser.showOpenDialog(addimg.getScene().getWindow());
        try {
            fileInputStream=new FileInputStream(file);
            image=new Image(fileInputStream);
            imgView.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Items colectData()  {
        String sup_id = all.get(supplierIdComBox.getSelectionModel().getSelectedIndex()).getId();
        System.out.println(sup_id);
        return new Items(itemCode.getText(), description.getText(), Integer.valueOf(qty.getText()),
                Integer.valueOf(sellingprice.getText()), Integer.valueOf(purchaseprice.getText()),image,sup_id);
    }
    public void saveOnAction(ActionEvent actionEvent) {
        try {
            boolean b = ItemsModel.setData(colectData());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "OKAY").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void supplierIdComBoxsetData(){
        try {
            all= SupplierModel.getAll();
            for (Supplier tem:all){
                comData.add(tem.getId());
            }
            supplierIdComBox.setItems(comData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
