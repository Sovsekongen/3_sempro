package jdbcsoftwaregui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import jdbcCon.JDBC;
import jdbcCon.ObjectVal;
import jdbcCon.PickUpVal;
import jdbcCon.ResultVal;
import jdbcCon.TimeVal;

public class FXMLDocumentController implements Initializable
{
    @FXML private TableView pickUpTable;
    @FXML private TableColumn throwNumTab;
    @FXML private TableColumn posXTab;
    @FXML private TableColumn posYTab;
    @FXML private TableColumn timestampTab;
    @FXML private Button updateBut;
    @FXML private ScatterChart chartTab1;
    @FXML private NumberAxis xAxisTab1;
    @FXML private NumberAxis yAxisTab1;
    
    @FXML private TableView objectTable;
    @FXML private TableColumn radiusTab;
    @FXML private TableColumn colourTab;
    @FXML private TableColumn shapeTab;
    @FXML private TableColumn throwNumTab3;
    @FXML private Pane imagePaneObject;
    @FXML private Circle objectCircle;
    
    @FXML private TableView resultTable;
    @FXML private TableColumn pointsTab;
    @FXML private TableColumn throwNumTab2;
    @FXML private TableColumn distanceFromOTab;
    
    @FXML private TableView timeTable;
    @FXML private TableColumn imagePTimeTab;
    @FXML private TableColumn pickUpTimeTab;
    @FXML private TableColumn throwTimeTab;
    @FXML private TableColumn totalTimeTab;
    @FXML private TableColumn throwNumTab4;
    @FXML private CheckBox imagePBox;
    @FXML private CheckBox pickUpBox;
    @FXML private CheckBox throwBox;
    @FXML private CheckBox totalBox;
    @FXML private LineChart timeGraph; 
    @FXML private NumberAxis xAxisTab4;
    @FXML private NumberAxis yAxisTab4;
    @FXML private Button updateTimeBut;
    
    @FXML private TableView minMaxTable;
    @FXML private TableColumn seriesColumn;
    @FXML private TableColumn minColumn;
    @FXML private TableColumn maxColumn;
    @FXML private TableColumn avgColumn;
    
    private PickUpVal[] pickUpVals;
    private ResultVal[] resultVals;
    private TimeVal[] timeVals;
    private ObjectVal[] objectVals;
    
    XYChart.Series seriesImageP = new XYChart.Series();
    XYChart.Series seriesPickUp = new XYChart.Series();
    XYChart.Series seriesThrow = new XYChart.Series();
    XYChart.Series seriesTotal = new XYChart.Series();
    
    private AtomicBoolean imagePCheck = new AtomicBoolean(false);
    private AtomicBoolean pickUpCheck = new AtomicBoolean(false);
    private AtomicBoolean throwCheck = new AtomicBoolean(false);
    private AtomicBoolean totalCheck = new AtomicBoolean(false);
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String query = "SELECT * FROM UR5;";
        JDBC con = new JDBC("root", "doyouloveit123");
        
        minMaxTable.setPlaceholder(new Label(""));
        
        pickUpVals = con.getPickUpArray();
        resultVals = con.getResultArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        drawScatterPlot();
        
        updatePickUpTab();
        updateObjectTab();
        updateResultTab();
        updateTimeTab();
        
        addSeriesListener(imagePBox.selectedProperty(), seriesImageP);
        addSeriesListener(pickUpBox.selectedProperty(), seriesPickUp);
        addSeriesListener(throwBox.selectedProperty(), seriesThrow);
        addSeriesListener(totalBox.selectedProperty(), seriesTotal);
        
        updateLineChartData();
        
        updateBut.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                updatePickUpTab();
                drawScatterPlot();
            }
        });
        
        updateTimeBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                updateTimeTab();
                updateLineChartData();
            }
        });
    }

    public void updatePickUpTab()
    {
        pickUpTable.getItems().clear();
        
        throwNumTab.setCellValueFactory(new PropertyValueFactory<>("throwNum"));
        posXTab.setCellValueFactory(new PropertyValueFactory<>("posX"));
        posYTab.setCellValueFactory(new PropertyValueFactory<>("posY"));
        timestampTab.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        
        for(PickUpVal t : pickUpVals)
        {
            pickUpTable.getItems().add(t);
        }
    }
    
    public void updateObjectTab()
    {
        objectTable.getItems().clear();
        
        throwNumTab2.setCellValueFactory(new PropertyValueFactory<>("throwNr"));
        radiusTab.setCellValueFactory(new PropertyValueFactory<>("radius"));
        colourTab.setCellValueFactory(new PropertyValueFactory<>("colour"));
        shapeTab.setCellValueFactory(new PropertyValueFactory<>("shape"));
        
        for(ObjectVal t : objectVals)
        {
            objectTable.getItems().add(t);
        }
    }
    
    public void updateResultTab()
    {
        resultTable.getItems().clear();
        
        throwNumTab3.setCellValueFactory(new PropertyValueFactory<>("throwNr"));
        pointsTab.setCellValueFactory(new PropertyValueFactory<>("points"));
        distanceFromOTab.setCellValueFactory(new PropertyValueFactory<>("distanceFromO"));
        
        for(ResultVal t : resultVals)
        {
            resultTable.getItems().add(t);
        }
    }
    
    public void updateTimeTab()
    {
        timeTable.getItems().clear();
        
        throwNumTab4.setCellValueFactory(new PropertyValueFactory<>("throwNr"));
        imagePTimeTab.setCellValueFactory(new PropertyValueFactory<>("imagePTime"));
        pickUpTimeTab.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        throwTimeTab.setCellValueFactory(new PropertyValueFactory<>("throwTime"));
        totalTimeTab.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        
        for(TimeVal t : timeVals)
        {
            timeTable.getItems().add(t);
        }
    }
    
    public void drawScatterPlot()
    {
        chartTab1.getData().clear();
        xAxisTab1.setLabel("Pos X");
        
        yAxisTab1.setLabel("Pos Y");
//        yAxisTab1.setLowerBound(0);
//        yAxisTab1.setUpperBound(400);
//        yAxisTab1.setAutoRanging(false);
        
        XYChart.Series seriesTab1 = new XYChart.Series();
        seriesTab1.setName("Pick Up Locations");
        
        for(PickUpVal t : pickUpVals)
        { 
            seriesTab1.getData().add(new XYChart.Data(t.getPosX(), t.getPosY()));
        }
        
        //chartTab1.setLegendVisible(false);
        chartTab1.getData().add(seriesTab1);
        //System.out.println(chartTab1.getLayoutX());
    }
    
    public void updateLineChartData()
    {
        timeGraph.getData().clear();
        xAxisTab4.setLabel("Throw Nr.");
        yAxisTab4.setLabel("Time (ms)");
        
        seriesImageP.setName("Image P. Time");
        seriesPickUp.setName("Pick Up Time");
        seriesThrow.setName("Throw Time");
        seriesTotal.setName("Total Time");
        
        for(TimeVal t : timeVals)
        {
            seriesImageP.getData().add(new XYChart.Data(t.getThrowNr(), t.getImagePTime()));
            seriesPickUp.getData().add(new XYChart.Data(t.getThrowNr(), t.getPickUpTime()));
            seriesThrow.getData().add(new XYChart.Data(t.getThrowNr(), t.getThrowTime()));
            seriesTotal.getData().add(new XYChart.Data(t.getThrowNr(), t.getTotalTime()));
        }
    }
    
    private void addSeriesListener(BooleanProperty selected, final XYChart.Series series) 
    {
        MinMaxMessage m = new MinMaxMessage(series);
        selected.addListener((observable, wasSelected, isSelected) -> 
        {
            if (isSelected) 
            {
               addToTable(m);
               timeGraph.getData().add(series);
            } 
            else 
            {
                removeFromTable(m);
                timeGraph.getData().remove(series);
            }
        });
    }
    
    private void addToTable(MinMaxMessage series)
    {
        series.calcMinMaxAvg();
               
        seriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        minColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
        avgColumn.setCellValueFactory(new PropertyValueFactory<>("avg"));
        
        minMaxTable.getItems().add(series);
    }
    
    private void removeFromTable(MinMaxMessage series)
    {
        //series.calcMinMaxAvg();
        
        minMaxTable.getItems().remove(series);
    }
}
