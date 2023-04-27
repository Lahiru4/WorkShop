package controller.barcodeReade;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import controller.cashier.AddCartController;
import dto.tm.ItemsTMTM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemsModel;
import util.webCam.WebCamView;
import util.webCam.WebcamService;

import java.io.IOException;
import java.sql.SQLException;

public class BarcodeReadController {
    public Rectangle main;
    public Rectangle second;
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView imageView;


    @FXML
    private JFXButton btnStart;

    @FXML
    private JFXButton btnStop;
    private WebcamService service;


    @FXML
    void startBtnOnAction(ActionEvent event) {
        service.restart();

        btnStart.setDisable(true);
    }

    @FXML
    void stopBtnOnAction(ActionEvent event) {
        service.cancel();

        btnStart.setDisable(false);
    }

    public void initialize() {

        Webcam cam = Webcam.getWebcams().get(0);
        service = new WebcamService(cam);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebCamView view = new WebCamView(service, imageView);
        pane.getChildren().add(view.getView());

        service.progressProperty().addListener((a, b, c) -> {
            String s = String.valueOf(c);
            if (s.equals("1.0")) {
                System.out.println(c);
                service.cancel();
                service.getT1().stop();
                imageView.getScene().getWindow().hide();

                String readBarCodeId = service.getReadBarCodeId();


                Stage stage = new Stage();
                try {
                    ItemsTMTM search = ItemsModel.search(readBarCodeId);
                    AddCartController.setItemsTMTM(search);


                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/AddCart.fxml"))));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(imageView.getScene().getWindow());
                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
            second.setWidth(main.getWidth() * c.doubleValue());
        });

        /*service.messageProperty().addListener((a,old,c) -> {
            if(c!=null){
                if(old==null){
                    System.out.println(c);

                }else
                if(!old.equals(c)) {
                    //
                }
            }
        });*/
        startRead();
    }

    private void startRead() {
        service.restart();
        //progress.setVisible(true);
        btnStart.setDisable(true);
    }

    public WebcamService getService() {
        return service;
    }
}
