package jdbcsoftwaregui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JDBCSoftwareGUI extends Application
{    
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlsources/FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        loader.getController();
        
        scene.getStylesheets().add("/csssources/styles.css");

        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
