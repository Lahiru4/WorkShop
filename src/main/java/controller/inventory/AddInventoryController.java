package controller.inventory;

import com.jfoenix.controls.JFXButton;
import dto.Items;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.ItemsModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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


    public void addImg(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file =chooser.showOpenDialog(addimg.getScene().getWindow());
        try {
            fileInputStream=new FileInputStream(file);
            //-----------------------------
            Image image1=new Image(fileInputStream);
            imgView.setImage(image1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
       /* FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");

        chooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        file = chooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Items colectData()  {
        return new Items(itemCode.getText(), description.getText(), Integer.valueOf(qty.getText()),
                Integer.valueOf(sellingprice.getText()), Integer.valueOf(purchaseprice.getText()),fileInputStream);
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
}
