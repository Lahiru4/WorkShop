import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.ViewFactory;
import util.types.SceneTypes;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(ViewFactory.getInstance().getScene(SceneTypes.LOGIN_PAGE));
        primaryStage.getIcons().add(new Image("img/icons8-shop-94.png"));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
