package shiro.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainPane.fxml")));
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
