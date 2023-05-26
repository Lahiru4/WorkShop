package controller.inventory;

import barcode.Barcode_genarate;
import com.jfoenix.controls.JFXButton;
import dto.Items;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import java.time.LocalDate;
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
    public ImageView barCodeImageView;
    public Label date;
    ObservableList<String> comData= FXCollections.observableArrayList();
    List<Supplier> all;

    public void initialize(){
        supplierIdComBoxsetData();
        setItemCode();
        date.setText(String.valueOf(LocalDate.now()));

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
    public void setBarcodeImg(){
        Barcode_genarate barcodeGenarate = new Barcode_genarate();
        barcodeGenarate.createImage(description.getText()+".png",itemCode.getText());
        Image img = barcodeGenarate.getImg();
        barCodeImageView.setImage(img);
    }

    public void supplierIdComBoxOnAction(ActionEvent actionEvent) {
        int selectedIndex = supplierIdComBox.getSelectionModel().getSelectedIndex();
        Supplier supplier = all.get(selectedIndex);
        supnumber.setText(supplier.getNumber());
        supName.setText(supplier.getName());
        supAddress.setText(supplier.getAddress());
        supGmail.setText(supplier.getGmail());
    }

    public void generateBarcodebtnOnAction(ActionEvent actionEvent) {
        if (itemCode.getText().equals("") || description.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"please Fill description and itemCode").showAndWait();
        }else {
            setBarcodeImg();
        }
    }
    private String genOrderId() throws SQLException, ClassNotFoundException {
        String lastId = ItemsModel.itemsGetLastCode();
        String id = "";
        if (lastId==null){
            id= "12341251";
        }else {
            String[] split = lastId.split("1234");
            int i = Integer.parseInt(split[1]);
            i++;
            id = String.format("1234%04d",i);
        }
        return id;
    }
    public void setItemCode(){
        try {
            String s = genOrderId();
            itemCode.setText(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
