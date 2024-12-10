package edu.sysagenda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;

import java.io.FileInputStream;
import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            //fxmlLoader.setRoot(scrollPaneMain);
            Parent root = fxmlLoader.load(new FileInputStream(Paths.get("src/main/java/edu/sysagenda/view/Agenda.fxml").toAbsolutePath().toString()));
//            Parent root = FXMLLoader.load(getClass().getResource("/agenda.fxml"));
            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Agenda de Contatos");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}