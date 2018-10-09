package jdbcsoftwaregui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdbcCon.JDBC;
import jdbcCon.PickUpVal;

public class FXMLDocumentController implements Initializable
{
    @FXML private TableView table;
    @FXML private TableColumn throwNumTab;
    @FXML private TableColumn posXTab;
    @FXML private TableColumn posYTab;
    @FXML private TableColumn timestampTab;
    @FXML private Button updateBut;
    @FXML private ScatterChart chartTab1;
    @FXML private NumberAxis xAxisTab1;
    @FXML private NumberAxis yAxisTab1;
    
    
    @FXML
    private Label label;
    private Tab tab;
    
    private PickUpVal[] vals;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String query = "SELECT * FROM UR5;";
        JDBC con = new JDBC("root", "doyouloveit123");
        
        vals = con.selectQuery(query);
        drawChart();
        
        updateTable1();
        
        updateBut.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e)
        {
            updateTable1();
            drawChart();
        }
        });
    }

    public void updateTable1()
    {
        table.getItems().clear();
        
        throwNumTab.setCellValueFactory(new PropertyValueFactory<>("throwNum"));
        posXTab.setCellValueFactory(new PropertyValueFactory<>("posX"));
        posYTab.setCellValueFactory(new PropertyValueFactory<>("posY"));
        timestampTab.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        
        //table.setItems(date);
      
        //table.getItems().add(val1);
        for(PickUpVal t : vals)
        {
            table.getItems().add(t);
        }
    }
    
    public void drawChart()
    {
        chartTab1.getData().clear();
        xAxisTab1.setLabel("Pos X");
        xAxisTab1.setLowerBound(-200);
        xAxisTab1.setUpperBound(200);
        xAxisTab1.setAutoRanging(false);
        
        yAxisTab1.setLabel("Pos Y");
        yAxisTab1.setLowerBound(0);
        yAxisTab1.setUpperBound(200);
        yAxisTab1.setAutoRanging(false);
        
        XYChart.Series seriesTab1 = new XYChart.Series();
        seriesTab1.setName("Pick Up Locations");
        
        for(PickUpVal t : vals)
        { 
            seriesTab1.getData().add(new XYChart.Data(t.getPosX(), t.getPosY()));
        }
        
        //chartTab1.setLegendVisible(false);
        chartTab1.getData().add(seriesTab1);
        //System.out.println(chartTab1.getLayoutX());
    }
}
