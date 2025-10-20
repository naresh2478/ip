package gui;

import java.io.IOException;

import app.Jack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A gui for Duke using FXML.
 */
public class Main extends Application {

    private Jack jack = new Jack("data/Jack.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJack(jack);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

