package barcode;

import javafx.scene.image.Image;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class Barcode_genarate {
    private  BufferedImage img;
    private Image image;


    /*public  void createImage() {
        Barcode_genarate.createImage("ddghtefyukhk.png", "52661");
        System.out.println("finished");
    }*/

    /*public static void main(String[] args) {
        createImage();
    }*/
    public  void createImage(String image_name,String myString)  {
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 500, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            //write to png file

            FileOutputStream fos = new FileOutputStream("D:\\frist project\\barcodelists\\"+image_name);
            fos.write(baos.toByteArray());


            BufferedImage read = ImageIO.read(new FileInputStream("D:\\frist project\\barcodelists\\"+image_name));
            img=read;
            FileInputStream fileInputStream = new FileInputStream("D:\\frist project\\barcodelists\\"+image_name);
            image=new Image(fileInputStream);
            String s = Barcode.barcodeRead(read);
            System.out.println(s+"       --okay");
            //
            read.flush();

            fos.flush();
            fos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    public  BufferedImage grtImgBufferedImage(){
        return img;
    }
    public Image getImg(){
        return image;
    }
}
