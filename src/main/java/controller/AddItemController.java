package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItemController {
    public ImageView imgAdd;
    public JFXTextField itemName;
    public JFXTextField itemPrice;
    public JFXTextField itemQTY;

    public void addItemsOnAction(ActionEvent actionEvent) {
       /* FileChooser chooser=new FileChooser();
        FileChooser.ExtensionFilter extensionFilterJPG =new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter extensionFilterPNG =new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");

        chooser.getExtensionFilters().addAll(extensionFilterJPG,extensionFilterPNG);

        File file=chooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage= ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            imgAdd.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void itemSaveBtn(ActionEvent actionEvent) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop", "root", "1234");
        String sql = "INSERT INTO items(itemName,itemCode,itemPrice,itemQTY,img)" + "VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, itemName.getText());
        preparedStatement.setInt(2, 21);
        preparedStatement.setInt(3, 12);
        preparedStatement.setInt(4, 44);
        /*preparedStatement.setBlob(5, );*/
        int update = preparedStatement.executeUpdate();
        connection.close();
    }
}
