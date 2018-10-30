package jdbcsoftwaregui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.TextField;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
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
    @FXML private ChoiceBox graphBox;
   
    @FXML private LineChart sGraph;
    @FXML private NumberAxis sXAxis;
    @FXML private NumberAxis sYAxis;
    @FXML private CheckBox sImagePBox;
    @FXML private CheckBox sPickupBox;
    @FXML private CheckBox sThrowBox;
    @FXML private CheckBox sTotalBox;
    
    @FXML private StackedBarChart sChart;
    @FXML private CategoryAxis nameAxis;
    @FXML private NumberAxis numberAxis;
    
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
    
    XYChart.Series sBarMin = new XYChart.Series();
    XYChart.Series sBarMax = new XYChart.Series();
    XYChart.Series sBarAvg = new XYChart.Series();
    
    ObservableList<String> list = FXCollections.observableArrayList();
    
    private final AtomicBoolean imagePCheck = new AtomicBoolean(false);;
    private final AtomicBoolean pickUpCheck = new AtomicBoolean(false);;
    private final AtomicBoolean throwCheck = new AtomicBoolean(false);;
    private final AtomicBoolean totalCheck = new AtomicBoolean(false);;
    
    private JDBC con = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        con = new JDBC("root", "doyouloveit123");
        
        minMaxTable.setPlaceholder(new Label(""));
        
        updateTables();
        FXMLDocumentController.this.updateSTableView();
        initComboBox();
        
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
        
        EventHandler<ActionEvent> setTrueFalse = new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                AllVal p = (AllVal)sTableView.getSelectionModel().getSelectedItem();
                int index = sTableView.getSelectionModel().getFocusedIndex();
                
                switch (((Control)event.getSource()).getId())
                {
                    case "sPickTrue":
                    {
                        String query = "UPDATE pickupobject SET pickTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sPickFalse":
                    {
                        String query = "UPDATE pickupobject SET pickTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sTargetTrue":
                    {
                        String query = "UPDATE pickupobject SET hitTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sTargetFalse":
                    {
                        String query = "UPDATE pickupobject SET hitTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    default:
                        break;
                }
                
                updateTables();
            }
        };
        
        EventHandler<ActionEvent> selectVal = (ActionEvent event) -> 
        {
            String val = sChooseBox.getText();
            if(val.contains("-"))
            {
               Scanner s = new Scanner(val).useDelimiter("-");
                int num1 = s.nextInt(), num2 = s.nextInt();
                updateSLineChart(num1, num2);
                updateSTableView(num1, num2);
                updateBarChart();
                
                addSeriesListener(sImagePBox.selectedProperty(), sSeriesImageP, sTimeView, sGraph);
                addSeriesListener(sPickupBox.selectedProperty(), sSeriesPickUp, sTimeView, sGraph);
                addSeriesListener(sThrowBox.selectedProperty(), sSeriesThrow, sTimeView, sGraph);
                addSeriesListener(sTotalBox.selectedProperty(), sSeriesTotal, sTimeView, sGraph);
                
                addBarSeriesListener(sImagePBox.selectedProperty(), sBarMin, sChart, sImagePBox.getText());
                addBarSeriesListener(sPickupBox.selectedProperty(), sBarMax, sChart, sPickupBox.getText());
                addBarSeriesListener(sThrowBox.selectedProperty(), sBarAvg, sChart, sThrowBox.getText());
            }
            else
            {
                try
                {
                    int num = Integer.parseInt(val);
                    FXMLDocumentController.this.updateSTableView(num);
                }
                catch (NumberFormatException e)
                {
                    
                }
            }
            
            sChooseBox.clear();
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

    
    /*
     * General update all tables.
     */
    public void updateTables()
    {
        pickUpVals = con.getPickUpArray();
        objectVals = con.getObjectArray();
        timeVals = con.getTimeArray();
        
        updatePickUpTab();
        updateTimeTab();
        updateObjectTab();
        
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
    
    /*
     * Draws the scatter Plot on the first tab.
     */
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
     * Updates the chart on Single tab by getting det min, max and avg for all values and min, max and avg
     * for the specific amount of items and calculating the differences of those.
     * Then the series are added to the graph.
     * The Graph showcases the actual numerical difference from value to value.
     */
    public void updateSLineChart(int num1, int num2)
    {
        Series bufIP = new Series();
        Series bufPU = new Series();
        Series bufThrow = new Series();
        Series bufTot = new Series();
        
        if(num1 == num2)
        {
            FXMLDocumentController.this.updateSTableView(num2);
            return;
        }
        
        allVals = con.getThrowNum(num1, num2);
        AllVal[] bufVals = con.getThrowNum();
                
        sGraph.getData().clear();
        sXAxis.setLabel("Throw Nr.");
        sYAxis.setLabel("Difference (ms)");
        
        for(AllVal t : allVals)
        {
            sSeriesImageP.getData().add(new XYChart.Data(t.getThrowNum(), t.getImagePTime()));
            sSeriesPickUp.getData().add(new XYChart.Data(t.getThrowNum(), t.getPickUpTime()));
            sSeriesThrow.getData().add(new XYChart.Data(t.getThrowNum(), t.getThrowTime()));
            sSeriesTotal.getData().add(new XYChart.Data(t.getThrowNum(), t.getTotalTime()));
        }
        
        for(AllVal t : bufVals)
        {
            bufIP.getData().add(new XYChart.Data(t.getThrowNum(), t.getImagePTime()));
            bufPU.getData().add(new XYChart.Data(t.getThrowNum(), t.getPickUpTime()));
            bufThrow.getData().add(new XYChart.Data(t.getThrowNum(), t.getThrowTime()));
            bufTot.getData().add(new XYChart.Data(t.getThrowNum(), t.getTotalTime()));
        }
        
        sSeriesImageP = getDifSeries(bufIP, sSeriesImageP);
        sSeriesPickUp = getDifSeries(bufPU, sSeriesPickUp);
        sSeriesThrow = getDifSeries(bufThrow, sSeriesThrow);
        sSeriesTotal = getDifSeries(bufTot, sSeriesTotal);
        
        sSeriesImageP.setName("Image P. Time");
        sSeriesPickUp.setName("Pickup Time");
        sSeriesThrow.setName("Throw Time");
        sSeriesTotal.setName("Total Time");    
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
    
    /*
     * Adds information to MinMaxAvg TableViews.     * 
     */
    private void addToTable(MinMaxMessage series, TableView tv)
    {
        series.calcMinMaxAvg();
        
        if(tv.getId().equals(sTimeView.getId()))
        {
            sName.setCellValueFactory(new PropertyValueFactory<>("name"));
            sMin.setCellValueFactory(new PropertyValueFactory<>("min"));
            sMax.setCellValueFactory(new PropertyValueFactory<>("max"));
            sAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
        }
        else
        {
            seriesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            minColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
            maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
            avgColumn.setCellValueFactory(new PropertyValueFactory<>("avg"));
        }
        
        tv.getItems().add(series);
    }
    
    /*
     * Removes information from MinMaxAvg TableViews.     * 
     */
    private void removeFromTable(MinMaxMessage series, TableView tv)
    {
        tv.getItems().remove(series);
    }
    
    /*
     * Gets information from the SQL server and adds it to the TableView found on the single-tab.
     * Works like the update individual table-view updates where the SQL-con object gets information from the server,
     * adds the information to a single data-value object call AllVal.
     * In the end it gets the columns defined as variables and adds the values to the columns based on ID of column and
     * variable name in AllVals.
     */
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
        
        sTableView.getItems().addAll(Arrays.asList(vals));
       
    }
    
    /*
     * Gets information from the SQL server and adds it to the TableView found on the single-tab.
     * Works like the update individual table-view updates where the SQL-con object gets information from the server,
     * adds the information to a single data-value object call AllVal.
     * In the end it gets the columns defined as variables and adds the values to the columns based on ID of column and
     * variable name in AllVals.
     * 
     * For a single value.
     */
    private void updateSTableView(int num)
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
    
    
    /*
     * Gets information from the SQL server and adds it to the TableView found on the single-tab.
     * Works like the update individual table-view updates where the SQL-con object gets information from the server,
     * adds the information to a single data-value object call AllVal.
     * In the end it gets the columns defined as variables and adds the values to the columns based on ID of column and
     * variable name in AllVals.
     * 
     * For values between two numbers.
     */
    private void updateSTableView(int num1, int num2)
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
    
    /*
     * Takes to series and gets the differences between them. Returns the values in a new series object.
     * Series1 - series2.
     */
    public Series getDifSeries(Series s1, Series s2)
    {
        Series res = new Series();
        Scanner in;
        double bufVal;
        ArrayList series1 = new ArrayList(s1.getData());
        ArrayList series2 = new ArrayList(s2.getData());
        
        for(int i = 0; i < series2.size(); i++)
        {
            in = new Scanner(series1.get(i).toString()).useDelimiter(",");
            in.next();
            bufVal = in.nextDouble();
            
            in = new Scanner(series2.get(i).toString()).useDelimiter(",");
            in.next();
            
            res.getData().add(new XYChart.Data(i, bufVal - in.nextDouble()));
        }
        
        return res;
    }
    
    
    /*
     * Uses the choice box to show/hide the Bar Chart or the Line Chart.
     */
    public void initComboBox()
    {
        graphBox.getItems().addAll("Line Chart", "Bar Chart");
        updateBarChart();
        
        graphBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                if(newValue.equals(0))
                {
                    sGraph.setVisible(true);
                    sChart.setVisible(false);
                }
                else
                {
                    sGraph.setVisible(false);
                    sChart.setVisible(true);
                }
            }
        } );
    }
    
    public void updateBarChart()
    {
        sChart.getData().clear();
        
        sBarMin.setName("Min");
        sBarMax.setName("Max");
        sBarAvg.setName("Avg");
        
        numberAxis.setLabel("Time [ms]");
        nameAxis.setLabel("Axis");
        
        ArrayList<Pair<String, Double>> avgArray = new ArrayList<>();
        ArrayList<Pair<String, Double>> maxArray = new ArrayList<>();
        ArrayList<Pair<String, Double>> minArray = new ArrayList<>();
        
//        //System.out.println(sTimeView.getItems().toString());
//        for(MinMaxMessage d : new ArrayList<MinMaxMessage>(sTimeView.getItems()))
//        {
//            System.out.println(d.getName());
//            avgArray.add(new Pair<>(d.getName(), d.getAvg()));
//            minArray.add(new Pair<>(d.getName(), d.getMin()));
//            maxArray.add(new Pair<>(d.getName(), d.getMax()));
//        }

        ArrayList<MinMaxMessage> vals = new ArrayList<>(sTimeView.getItems());
        for (MinMaxMessage item : vals) 
        {
            System.out.println(sName.getCellData(item) + " " + sAvg.getCellObservableValue(item).getValue());
            avgArray.add(new Pair(sName.getCellData(item), sAvg.getCellObservableValue(item).getValue()));
            maxArray.add(new Pair(sName.getCellData(item), sMax.getCellObservableValue(item).getValue()));
            minArray.add(new Pair(sName.getCellData(item), sMin.getCellObservableValue(item).getValue()));
        }
        
        for(int i = 0; i < avgArray.size(); i++)
        {
            sBarAvg.getData().add(new XYChart.Data(avgArray.get(i).getKey(), avgArray.get(i).getValue()));
            sBarMin.getData().add(new XYChart.Data(minArray.get(i).getKey(), minArray.get(i).getValue()));
            sBarMax.getData().add(new XYChart.Data(maxArray.get(i).getKey(), maxArray.get(i).getValue()));
        }
    }
    
    private void addBarSeriesListener(BooleanProperty selected, final Series series, StackedBarChart lc, String nameOfBut) 
    {
        selected.addListener((observable, wasSelected, isSelected) -> 
        {
            if (isSelected) 
            {
               lc.getData().add(series);
               
               updateBarChart();
               list.add(nameOfBut);
               
               System.out.println(nameOfBut);
               nameAxis.setCategories(list);
            } 
            else 
            {
                list.remove(nameOfBut);
                nameAxis.setCategories(list);
                
                lc.getData().remove(series);
            }
        });
    }
}
