package controller.inventory;

import com.jfoenix.controls.JFXButton;
import dto.Items;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.ItemsModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
    public File file;


    public void addImg(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");

        chooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterPNG);

        file = chooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Items colectData() {
        Image image1 = imgView.getImage();
        return new Items(itemCode.getText(), description.getText(), Integer.valueOf(qty.getText()),
                Integer.valueOf(sellingprice.getText()), Integer.valueOf(purchaseprice.getText()), file);
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
        }
    }
}
