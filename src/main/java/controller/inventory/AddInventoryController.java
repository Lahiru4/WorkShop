package controller.inventory;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddInventoryController {

    public ImageView imgView;

    public void addImg(ActionEvent actionEvent) {
        FileChooser chooser=new FileChooser();
        FileChooser.ExtensionFilter extensionFilterJPG =new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG =new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");

        chooser.getExtensionFilters().addAll(extensionFilterJPG,extensionFilterPNG);

        File file=chooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage= ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            imgView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
