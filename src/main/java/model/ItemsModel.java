package model;

import db.DbConnection;
import dto.Items;
import dto.tm.ItemsTM;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import util.CrudUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsModel {
    private static byte[] imagenToByte(Image imagen) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(imagen, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output );
            ImageIO.write(bufferimage, "png", output );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte [] data = output.toByteArray();
        return data;
    }
    public static boolean setData(Items items) throws SQLException, ClassNotFoundException, IOException {
        byte[] blob = imagenToByte(items.getImg());
        boolean b = CrudUtil.execute("INSERT INTO items(code, description, qty, " +
                        "selling_price,Purchase_price,img,supplier_id) VALUES(?, ?, ?, ?,?,?,?)", items.getItemCode(), items.getDescription(), items.getQTY(), items.getSelling_price()
                , items.getPurchase_price(),blob,items.getSuppler_Id());
        return b;
    }
    public static List<ItemsTM> getAll() throws SQLException, ClassNotFoundException {
        List<ItemsTM> data= new ArrayList<>();
        ResultSet rs=CrudUtil.execute("SELECT * FROM items");
        while (rs.next()){

            Blob blob = rs.getBlob(6);
            InputStream binaryStream = blob.getBinaryStream();
            Image image = new Image(binaryStream);

            data.add(new ItemsTM(rs.getString(1),rs.getString(2),rs.getInt(3), rs.getDouble(4),
            rs.getDouble(5),image,rs.getString(7)));
        }
        return  data;
    }

    public static boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from items  where code = ?",itemCode);
    }
    public static boolean updateQty(List<Items> cartDTOList) throws SQLException, ClassNotFoundException {
        for (Items dto : cartDTOList) {
            if(!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Items dto) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        boolean b = CrudUtil.execute("UPDATE items SET qty = (qty - ?) WHERE code = ?", dto.getQTY(), dto.getItemCode());
        return b;
    }
}
