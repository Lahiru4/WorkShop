package barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class Barcode {
    public static void barcodeRead(BufferedImage read) {
        try {
            LuminanceSource source= new BufferedImageLuminanceSource(read);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result decode = new MultiFormatReader().decode(binaryBitmap);
            System.out.println(decode.getText());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeBarcode(){

    }
}
