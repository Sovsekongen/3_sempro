package jdbcsoftwaregui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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

public class FXMLDocumentController implements Initializable
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
    
    @FXML private TableView sTableView;
    @FXML private TableView sTimeView;
    @FXML private TableColumn sThrowNum;
    @FXML private TableColumn sTimestamp;
    @FXML private TableColumn sPosX;
    @FXML private TableColumn sPosY;
    @FXML private TableColumn sColour;
    @FXML private TableColumn sPickedUp;
    @FXML private TableColumn sHitTarget;
    @FXML private TableColumn sRadius;
    @FXML private TableColumn sPickUpTime;
    @FXML private TableColumn sImagePTime;
    @FXML private TableColumn sThrowTime;
    @FXML private TableColumn sTotalTime;
    @FXML private TableColumn sShape;
    @FXML private TableColumn sName;
    @FXML private TableColumn sMin;
    @FXML private TableColumn sMax;
    @FXML private TableColumn sAvg;
    @FXML private Button sPickTrue;
    @FXML private Button sPickFalse;
    @FXML private Button sTargetTrue;
    @FXML private Button sTargetFalse;
    @FXML private Button sSelect;
    @FXML private TextField sChooseBox;
    @FXML private ImageView sPic;
    @FXML private LineChart sGraph;
    @FXML private NumberAxis sXAxis;
    @FXML private NumberAxis sYAxis;
    
    private PickUpVal[] pickUpVals;
    private TimeVal[] timeVals;
    private ObjectVal[] objectVals;
    private AllVal[] allVals;
    
    XYChart.Series seriesImageP = new XYChart.Series();
    XYChart.Series seriesPickUp = new XYChart.Series();
    XYChart.Series seriesThrow = new XYChart.Series();
    XYChart.Series seriesTotal = new XYChart.Series();
    
    XYChart.Series sSeriesImageP = new XYChart.Series();
    XYChart.Series sSeriesPickUp = new XYChart.Series();
    XYChart.Series sSeriesThrow = new XYChart.Series();
    XYChart.Series sSeriesTotal = new XYChart.Series();
    
    private AtomicBoolean imagePCheck = new AtomicBoolean(false);
    private AtomicBoolean pickUpCheck = new AtomicBoolean(false);
    private AtomicBoolean throwCheck = new AtomicBoolean(false);
    private AtomicBoolean totalCheck = new AtomicBoolean(false);
    
    private JDBC con = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        con = new JDBC("root", "doyouloveit123");
        
        minMaxTable.setPlaceholder(new Label(""));
        
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
                    System.out.println(b.getPic());
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
            drawScatterPlot();
            updateLineChartData();
            System.out.println("dab");
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
        
        EventHandler<ActionEvent> setTrueFalse = (ActionEvent event) -> 
        {
            AllVal p = (AllVal)sTableView.getSelectionModel().getSelectedItem();
            
            if(((Control)event.getSource()).getId().equals("sPickTrue")) 
            {
                String query = "UPDATE pickupobject SET pickTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                con.updateObject(query);
            }
            else if(((Control)event.getSource()).getId().equals("sPickFalse"))
            {
                String query = "UPDATE pickupobject SET pickTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                con.updateObject(query);
            }
            else if(((Control)event.getSource()).getId().equals("sTargetTrue"))
            {
                String query = "UPDATE pickupobject SET hitTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                con.updateObject(query);
            }
            else if(((Control)event.getSource()).getId().equals("sTargetFalse"))
            {
                String query = "UPDATE pickupobject SET hitTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                con.updateObject(query);
            }
            
            updateTables();
        };
        
        EventHandler<ActionEvent> selectVal = (ActionEvent event) -> 
        {
            String val = sChooseBox.getText();
            if(val.contains("-"))
            {
                Scanner s = new Scanner(val).useDelimiter("-");
                int num1 = s.nextInt(), num2 = s.nextInt();
                setThrowVal(num1, num2);
                updateSLineChart(num1, num2);
            }
            else
            {
                int num = Integer.parseInt(val);
                setThrowVal(num);
            }
            
            updateTables();
        };
        
        sPickTrue.setOnAction(setTrueFalse);
        sPickFalse.setOnAction(setTrueFalse);
        sTargetTrue.setOnAction(setTrueFalse);
        sTargetFalse.setOnAction(setTrueFalse);
        
        deleteButton.setOnAction(deleteHandler);
        deleteButton1.setOnAction(deleteHandler);
        deleteButton3.setOnAction(deleteHandler);
        
        updateBut.setOnAction(updateHandler);
        updateBut1.setOnAction(updateHandler);
        updateBut3.setOnAction(updateHandler);
        
        sSelect.setOnAction(selectVal);
    }

    public void updateTables()
    {
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        updatePickUpTab();
        updateTimeTab();
        updateObjectTab();
        updateSTableView();
        
        drawScatterPlot();
        updateLineChartData();
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

        XYChart.Series seriesTab1 = new XYChart.Series();
        seriesTab1.setName("Pick Up Locations");
        
        for(PickUpVal t : pickUpVals)
        { 
            seriesTab1.getData().add(new XYChart.Data(t.getPosX(), t.getPosY()));
        }
        
        chartTab1.getData().add(seriesTab1);
    }
    
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
    
    public void updateSLineChart(int num1, int num2)
    {
        allVals = con.getThrowNum(num1, num2);
        
        sGraph.getData().clear();
        sXAxis.setLabel("Throw Nr.");
        sYAxis.setLabel("Difference (ms)");
        
        sSeriesImageP.setName("Image P. Time");
        sSeriesPickUp.setName("Pickup Time");
        sSeriesThrow.setName("Throw Time");
        sSeriesTotal.setName("Total Time");
        
        for(AllVal t : allVals)
        {
            sSeriesImageP.getData().add(new XYChart.Data(t.getThrowNum(), t.getImagePTime()));
            sSeriesPickUp.getData().add(new XYChart.Data(t.getThrowNum(), t.getPickUpTime()));
            sSeriesThrow.getData().add(new XYChart.Data(t.getThrowNum(), t.getThrowTime()));
            sSeriesTotal.getData().add(new XYChart.Data(t.getThrowNum(), t.getTotalTime()));
        }
    }
    
    private void addSeriesListener(BooleanProperty selected, final XYChart.Series series, TableView v, LineChart lc) 
    {
        MinMaxMessage m = new MinMaxMessage(series);
        selected.addListener((observable, wasSelected, isSelected) -> 
        {
            if (isSelected) 
            {
               addToTable(m, v);
               lc.getData().add(series);
            } 
            else 
            {
                removeFromTable(m, v);
                lc.getData().remove(series);
            }
        });
    }
    
    private void addToTable(MinMaxMessage series, TableView tv)
    {
        series.calcMinMaxAvg(series.getSeries());
               
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
    
    private void updateSTableView()
    {
        sTableView.getItems().clear();
        
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        AllVal[] vals = new AllVal[pickUpVals.length];
        
        for(int i = 0; i < pickUpVals.length; i++)
        {
            AllVal t = new AllVal(pickUpVals[i].getPosX(), pickUpVals[i].getPosY(), 
                pickUpVals[i].getThrowNum(), pickUpVals[i].getTimestamp(), timeVals[i].getPickUpTime(), 
                timeVals[i].getImagePTime(), timeVals[i].getThrowTime(), timeVals[i].getTotalTime(), objectVals[i].getRadius(), 
                objectVals[i].getColour(), objectVals[i].getShape(), objectVals[i].getPic(), 
                objectVals[i].isHitTarget(), objectVals[i].isPickTarget());
            
            vals[i] = t;
        }
        
        sThrowNum.setCellValueFactory(new PropertyValueFactory<>("throwNum"));
        sPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
        sPosY.setCellValueFactory(new PropertyValueFactory<>("posY"));
        sTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        sRadius.setCellValueFactory(new PropertyValueFactory<>("radius"));
        sColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        sShape.setCellValueFactory(new PropertyValueFactory<>("shape"));
        sImagePTime.setCellValueFactory(new PropertyValueFactory<>("imagePTime"));
        sPickUpTime.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        sThrowTime.setCellValueFactory(new PropertyValueFactory<>("throwTime"));
        sTotalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        sPickedUp.setCellValueFactory(new PropertyValueFactory<>("pickTarget"));
        sHitTarget.setCellValueFactory(new PropertyValueFactory<>("hitTarget"));
        
        for(AllVal t : vals)
        {
            sTableView.getItems().add(t);
        }
       
    }
    
    private void setThrowVal(int num)
    {
        sTableView.getItems().clear();
        
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        AllVal[] vals = con.getThrowNum(num);
        
        sThrowNum.setCellValueFactory(new PropertyValueFactory<>("throwNum"));
        sPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
        sPosY.setCellValueFactory(new PropertyValueFactory<>("posY"));
        sTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        sRadius.setCellValueFactory(new PropertyValueFactory<>("radius"));
        sColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        sShape.setCellValueFactory(new PropertyValueFactory<>("shape"));
        sImagePTime.setCellValueFactory(new PropertyValueFactory<>("imagePTime"));
        sPickUpTime.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        sThrowTime.setCellValueFactory(new PropertyValueFactory<>("throwTime"));
        sTotalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        sPickedUp.setCellValueFactory(new PropertyValueFactory<>("pickTarget"));
        sHitTarget.setCellValueFactory(new PropertyValueFactory<>("hitTarget"));
        
        for(AllVal t : vals)
        {
            sTableView.getItems().add(t);
        }
    }
    
    private void setThrowVal(int num1, int num2)
    {
        sTableView.getItems().clear();
        
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        AllVal[] vals = con.getThrowNum(num1, num2);
        
        sThrowNum.setCellValueFactory(new PropertyValueFactory<>("throwNum"));
        sPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
        sPosY.setCellValueFactory(new PropertyValueFactory<>("posY"));
        sTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        sRadius.setCellValueFactory(new PropertyValueFactory<>("radius"));
        sColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        sShape.setCellValueFactory(new PropertyValueFactory<>("shape"));
        sImagePTime.setCellValueFactory(new PropertyValueFactory<>("imagePTime"));
        sPickUpTime.setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        sThrowTime.setCellValueFactory(new PropertyValueFactory<>("throwTime"));
        sTotalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        sPickedUp.setCellValueFactory(new PropertyValueFactory<>("pickTarget"));
        sHitTarget.setCellValueFactory(new PropertyValueFactory<>("hitTarget"));
        
        for(AllVal t : vals)
        {
            sTableView.getItems().add(t);
        }
    }
}
