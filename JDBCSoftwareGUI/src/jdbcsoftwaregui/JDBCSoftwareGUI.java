/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsoftwaregui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import jdbcCon.JDBC;
import jdbcCon.TableVal;

public class JDBCSoftwareGUI extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlsources/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add("/csssources/styles.css");
        
        String query = "SELECT * FROM UR5;";
        JDBC con = new JDBC("root", "doyouloveit123");
        
        TableVal[] vals = con.selectQuery(query).clone();
        
        TableView<TableVal> table = new TableView<>();
        for(int i = 0; i < vals.length; i++)
        {
            System.out.println(vals[i].getDate());
            table.getItems().add(vals[i]);
        }
        
        stage.show();
        
    }

    public static void main(String[] args)
    {
        
        
        launch(args);
    }
    
}
