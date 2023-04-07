package barcode;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ReadBarcode extends JFrame {
    private WebcamPanel panel=null;
    private Webcam webcam=null;
    JFrame jFrame;
    public ReadBarcode() throws InterruptedException, IOException {
        webcam();
    }
    {
        jFrame = new JFrame();
        jFrame.setSize(400,400);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new ReadBarcode();
    }
    private void webcam() throws InterruptedException, IOException {
        Dimension size= WebcamResolution.QVGA.getSize();
        webcam=Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel=new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jFrame.add(panel);

        /*Stage stage = new Stage();
        stage.setScene(ViewFactory.getInstance().getScene(SceneTypes.Question_PAGE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(oderDashboard.getScene().getWindow());
        stage.showAndWait();*/


    }
}