package QueG1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {  
        Parent root = FXMLLoader.load(getClass().getResource("JFX_TEST_FXML.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}
