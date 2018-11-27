package jdbcsoftwaregui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jdbcCon.AllVal;
import jdbcCon.JDBC;
import jdbcCon.ObjectVal;
import jdbcCon.PickUpVal;
import jdbcCon.TimeVal;

public class SingleTabController implements Initializable
{
    @FXML private TableView pickUpTable;
    @FXML private TableColumn throwNumTab;
    @FXML private TableColumn posXTab;
    @FXML private TableColumn posYTab;
    @FXML private TableColumn timestampTab;
    @FXML private ScatterChart chartTab1;
    @FXML private NumberAxis xAxisTab1;
    @FXML private NumberAxis yAxisTab1;
    @FXML private Button deleteButton;
    @FXML private Button updateBut;
    
    @FXML private TableView objectTable;
    @FXML private TableColumn radiusTab;
    @FXML private TableColumn colourTab;
    @FXML private TableColumn shapeTab;
    @FXML private TableColumn throwNumTab2;
    @FXML private ImageView objectImageView;
    @FXML private Button deleteButton1;
    @FXML private Button updateBut1;
    
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
    @FXML private TableView minMaxTable;
    @FXML private TableColumn seriesColumn;
    @FXML private TableColumn minColumn;
    @FXML private TableColumn maxColumn;
    @FXML private TableColumn avgColumn;
    @FXML private Button deleteButton3;
    @FXML private Button updateBut3;
    
    Series seriesImageP = new Series();
    Series seriesPickUp = new Series();
    Series seriesThrow = new Series();
    Series seriesTotal = new Series();
    
    private PickUpVal[] pickUpVals;
    private TimeVal[] timeVals;
    private ObjectVal[] objectVals;
    
    private JDBC con = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        con = new JDBC("root", "123");
        
        minMaxTable.setPlaceholder(new Label(""));
        
        timeGraph.setAnimated(false);
        updateTables();
        
        addSeriesListener(imagePBox.selectedProperty(), seriesImageP, minMaxTable, timeGraph);
        addSeriesListener(pickUpBox.selectedProperty(), seriesPickUp, minMaxTable, timeGraph);
        addSeriesListener(throwBox.selectedProperty(), seriesThrow, minMaxTable, timeGraph);
        addSeriesListener(totalBox.selectedProperty(), seriesTotal, minMaxTable, timeGraph);

        objectTable.setOnMouseClicked((MouseEvent event) -> 
        {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1)
            {
                try
                {
                    ObjectVal b = (ObjectVal)objectTable.getSelectionModel().getSelectedItem();
                
                    FileInputStream fs = new FileInputStream(b.getPic());
                    Image pic = new Image(fs);
        
                    objectImageView.setImage(pic);
                    fs.close();
                } 
                catch (IOException e)
                {
                }
            }
        });
        
        EventHandler<ActionEvent> updateHandler = (ActionEvent event) ->
        {
            updateTables();
        };
                
        EventHandler<ActionEvent> deleteHandler = (ActionEvent event) -> 
        {
            if(((Control)event.getSource()).getId().equals("deleteButton")) 
            {
                PickUpVal p = (PickUpVal)pickUpTable.getSelectionModel().getSelectedItem();
                con.deleteRow(p.getThrowNum());
            }
            else if(((Control)event.getSource()).getId().equals("deleteButton1"))
            {
                ObjectVal p = (ObjectVal)objectTable.getSelectionModel().getSelectedItem();
                con.deleteRow(p.getThrowNr());
            }
            else if(((Control)event.getSource()).getId().equals("deleteButton3"))
            {
                TimeVal p = (TimeVal)timeTable.getSelectionModel().getSelectedItem();
                con.deleteRow(p.getThrowNr());
            }
            
            updateTables();
        };
        
        deleteButton.setOnAction(deleteHandler);
        deleteButton1.setOnAction(deleteHandler);
        deleteButton3.setOnAction(deleteHandler);
        
        updateBut.setOnAction(updateHandler);
        updateBut1.setOnAction(updateHandler);
        updateBut3.setOnAction(updateHandler);
    }    
    
    /*
     * General update all tables.
     */
    public void updateTables()
    {
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        pickUpBox.setSelected(false);
        imagePBox.setSelected(false);
        throwBox.setSelected(false);
        totalBox.setSelected(false);
        
        updatePickUpTab();
        updateTimeTab();
        updateObjectTab();
        
        drawScatterPlot();
        updateLineChartData();
    }
    
    /*
     * Draws the scatter Plot on the first tab.
     */
    public void drawScatterPlot()
    {
        chartTab1.getData().clear();
        xAxisTab1.setLabel("Pos X");
        yAxisTab1.setLabel("Pos Y");

        Series seriesTab1 = new Series();
        seriesTab1.setName("Pick Up Locations");
        
        for(PickUpVal t : pickUpVals)
        { 
            seriesTab1.getData().add(new XYChart.Data(t.getPosX(), t.getPosY()));
        }
        
        chartTab1.getData().add(seriesTab1);
    }
    
    /*
     * Updates the chart on the Time-Tab
     */
    public void updateLineChartData()
    {
        timeGraph.getData().clear();
        xAxisTab4.setLabel("Throw Nr.");
        yAxisTab4.setLabel("Time (ms)");

        seriesImageP.setName("Image P. Time");
        seriesPickUp.setName("Pickup Time");
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
    
    /*
     * Adds information to MinMaxAvg TableViews.     * 
     */
    private void addToTable(MinMaxMessage series, TableView tv)
    {
        series.calcMinMaxAvg();
        
        seriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        minColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
        avgColumn.setCellValueFactory(new PropertyValueFactory<>("avg"));
        
        tv.getItems().add(series);
    }
    
    private void removeFromTable(MinMaxMessage series, TableView tv)
    {
        tv.getItems().remove(series);
    } 
    
     /*
    * Adds a series listener for a specific ClickBox.
    * Uses the isSelected property to determine whether or not to perfom an action.
    */
    private void addSeriesListener(BooleanProperty selected, final Series series, TableView tv, LineChart lc) 
    {
        MinMaxMessage mmm = new MinMaxMessage(series);
        
        selected.addListener((observable, wasSelected, isSelected) -> 
        {            
            if (isSelected) 
            {
                addToTable(mmm, tv);
                lc.getData().add(series);
            } 
            else 
            {
                removeFromTable(mmm, tv);
                lc.getData().remove(series);
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
        
        pickUpTable.getItems().addAll(Arrays.asList(pickUpVals));
    }
    
    public void updateObjectTab()
    {
        objectTable.getItems().clear();
        
        throwNumTab2.setCellValueFactory(new PropertyValueFactory<>("throwNr"));
        radiusTab.setCellValueFactory(new PropertyValueFactory<>("radius"));
        colourTab.setCellValueFactory(new PropertyValueFactory<>("colour"));
        shapeTab.setCellValueFactory(new PropertyValueFactory<>("shape"));
        
        objectTable.getItems().addAll(Arrays.asList(objectVals));
    }
    
    public void updateTimeTab()
    {
        timeTable.getItems().clear();
        
        throwNumTab4.setCellValueFactory(new PropertyValueFactory<>("throwNr"));
        imagePTimeTab.setCellValueFactory(new PropertyValueFactory<>("imagePTime"));
        pickUpTimeTab.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        throwTimeTab.setCellValueFactory(new PropertyValueFactory<>("throwTime"));
        totalTimeTab.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        
        timeTable.getItems().addAll(Arrays.asList(timeVals));
    }
}
