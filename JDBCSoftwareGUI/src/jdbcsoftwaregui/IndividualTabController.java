package jdbcsoftwaregui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import jdbcCon.AllVal;
import jdbcCon.JDBC;

public class IndividualTabController implements Initializable
{
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
    @FXML private Button deleteBut;
    @FXML private Button resetBut;
    @FXML private TextField sChooseBox;
    @FXML private ChoiceBox graphBox;
    @FXML private ChoiceBox tableBox;
    @FXML private Label pickUpPer;
    @FXML private Label hitPer;
   
    @FXML private ScatterChart sGraph;
    @FXML private NumberAxis sXAxis;
    @FXML private NumberAxis sYAxis;
    @FXML private CheckBox sImagePBox;
    @FXML private CheckBox sPickupBox;
    @FXML private CheckBox sThrowBox;
    @FXML private CheckBox sTotalBox;
    
    @FXML private BarChart sChart;
    @FXML private CategoryAxis nameAxis;
    @FXML private NumberAxis numberAxis;
    
    private AllVal[] allVals;
    
    Series sSeriesImageP = new Series();
    Series sSeriesPickUp = new Series();
    Series sSeriesThrow = new Series();
    Series sSeriesTotal = new Series();
    
    Series sBarMin = new Series();
    Series sBarMax = new Series();
    Series sBarAvg = new Series();
    
    private ArrayList<String> barCategories = new ArrayList<>();
        
    private JDBC con = null;
    private boolean addSeriesListener = false;
    private int bufNum1, bufNum2;
    private double hitPerVal, pickPerVal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        con = new JDBC("root", "123");
        pickUpPer.setText("");
        hitPer.setText("");
        
        updateSTableView();
        initComboBox();
        initTableBox();
        
        sGraph.setAnimated(false);
        sChart.setAnimated(false);
       
        EventHandler<ActionEvent> deleteHandler = (ActionEvent event) -> 
        {
            AllVal a = (AllVal)sTableView.getSelectionModel().getSelectedItem();
            con.deleteRow(a.getThrowNum());
            
            if(bufNum1 != 0 && bufNum2 != 0)
            {
                updateSLineChart(bufNum1, bufNum2);
                updateSTableView(bufNum1, bufNum2);
            }
            else if(bufNum1 != 0 && bufNum2 == 0)
            {
                updateSTableView(bufNum1);
            }
            else if(bufNum1 == 0 && bufNum2 == 0)
            {
                updateSTableView();
            }
        };
        
        EventHandler<ActionEvent> resetHandler = (ActionEvent event) ->
        {
            resetAll();
            
            bufNum1 = 0;
            bufNum2 = 0;
            
            updateSTableView();
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
                        String query = "UPDATE PickUpObject SET pickTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sPickFalse":
                    {
                        String query = "UPDATE PickUpObject SET pickTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sTargetTrue":
                    {
                        String query = "UPDATE PickUpObject SET hitTarget = TRUE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    case "sTargetFalse":
                    {
                        String query = "UPDATE PickUpObject SET hitTarget = FALSE WHERE throwNr = " + p.getThrowNum() + ";";
                        con.updateObject(query);
                        AllVal[] updatedVal = con.getThrowNum(p.getThrowNum());
                        sTableView.getItems().set(index, updatedVal[0]);
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        
        EventHandler<ActionEvent> selectVal = (ActionEvent event) -> 
        {
            String val = sChooseBox.getText();
            resetAll();
            
            if(val.contains("-"))
            {
                Scanner s = new Scanner(val).useDelimiter("-");
                bufNum1 = s.nextInt();
                bufNum2 = s.nextInt();

                if(!addSeriesListener)
                {
                    updateSLineChart(bufNum1, bufNum2);
                    updateSTableView(bufNum1, bufNum2);
                    updateBarChart();
                    calcPercentage(bufNum1, bufNum2);
                    
                    addSeriesListener = addListeners();
                
                }
                else
                {
                    updateSLineChart(bufNum1, bufNum2);
                    updateSTableView(bufNum1, bufNum2);
                    calcPercentage(bufNum1, bufNum2);
                    updateBarChart();
                }
            }
            else
            {
                try
                {
                    bufNum1 = Integer.parseInt(val);
                    updateSTableView(bufNum1);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("You have entered a bad value.");
                }
            }
            
            sChooseBox.clear();
        };
        
        sPickTrue.setOnAction(setTrueFalse);
        sPickFalse.setOnAction(setTrueFalse);
        sTargetTrue.setOnAction(setTrueFalse);
        sTargetFalse.setOnAction(setTrueFalse);
        
        deleteBut.setOnAction(deleteHandler);
        resetBut.setOnAction(resetHandler);
        
        sSelect.setOnAction(selectVal);
    }    
    
    public boolean addListeners()
    {
        addSSeriesListener(sImagePBox.selectedProperty(), sSeriesImageP, sTimeView, sGraph);
        addSSeriesListener(sPickupBox.selectedProperty(), sSeriesPickUp, sTimeView, sGraph);
        addSSeriesListener(sThrowBox.selectedProperty(), sSeriesThrow, sTimeView, sGraph);
        addSSeriesListener(sTotalBox.selectedProperty(), sSeriesTotal, sTimeView, sGraph);

        addBarSeriesListener(sImagePBox.selectedProperty(), sChart, sImagePBox.getText());
        addBarSeriesListener(sPickupBox.selectedProperty(), sChart, sPickupBox.getText());
        addBarSeriesListener(sThrowBox.selectedProperty(), sChart, sThrowBox.getText());
        addBarSeriesListener(sTotalBox.selectedProperty(), sChart, sTotalBox.getText());
        return true;
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
        
        bufIP.setName("bufIP");
        bufPU.setName("bufPU");
        bufThrow.setName("bufThrow");
        bufTot.setName("bufTot");
        
        sSeriesImageP.getData().clear();
        sSeriesPickUp.getData().clear();
        sSeriesThrow.getData().clear();
        sSeriesTotal.getData().clear();
        
        if(num1 == num2)
        {
            updateSTableView(num2);
            return;
        }
        
        allVals = con.getThrowNum(num1, num2);
        AllVal[] bufVals = con.getThrowNum();
                
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
    
    public void updateSLineChartNonDif(int num1, int num2)
    {
        sSeriesImageP.getData().clear();
        sSeriesPickUp.getData().clear();
        sSeriesThrow.getData().clear();
        sSeriesTotal.getData().clear();
        
        if(num1 == num2)
        {
            updateSTableView(num2);
            return;
        }
        
        allVals = con.getThrowNum(num1, num2);
                
        sXAxis.setLabel("Throw Nr.");
        sYAxis.setLabel("Time (ms)");
        
        for(AllVal t : allVals)
        {
            sSeriesImageP.getData().add(new XYChart.Data(t.getThrowNum(), t.getImagePTime()));
            sSeriesPickUp.getData().add(new XYChart.Data(t.getThrowNum(), t.getPickUpTime()));
            sSeriesThrow.getData().add(new XYChart.Data(t.getThrowNum(), t.getThrowTime()));
            sSeriesTotal.getData().add(new XYChart.Data(t.getThrowNum(), t.getTotalTime()));
        }
        
        sSeriesImageP.setName("Image P. Time");
        sSeriesPickUp.setName("Pickup Time");
        sSeriesThrow.setName("Throw Time");
        sSeriesTotal.setName("Total Time");    
    }
    
    /*
     * Adds information to MinMaxAvg TableViews.     * 
     */
    private void addToTable(MinMaxMessage series, TableView tv)
    {
        series.calcMinMaxAvg();

        sName.setCellValueFactory(new PropertyValueFactory<>("name"));
        sMin.setCellValueFactory(new PropertyValueFactory<>("min"));
        sMax.setCellValueFactory(new PropertyValueFactory<>("max"));
        sAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
        
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
        
        AllVal[] vals = con.getThrowNum();
        
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
        
        sTableView.getItems().addAll(Arrays.asList(vals));
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
        
        sTableView.getItems().addAll(Arrays.asList(vals));
    }
    
    /*
     * Takes to series and gets the differences between them. Returns the values in a new series object.
     * avg - sammenligningsseries.
     */
    public Series getDifSeries(Series s1, Series s2)
    {
        Series res = new Series();
        Scanner in;
        double bufVal = 0, orgVal = 0;
        MinMaxMessage buf = new MinMaxMessage(s1);
        buf.calcMinMaxAvg();
        
        ArrayList series2 = new ArrayList(s2.getData());
        
        for(int i = 0; i < series2.size(); i++)
        {
            bufVal = buf.getAvg();
            
            in = new Scanner(series2.get(i).toString()).useDelimiter(",");
            in.next();
            orgVal = in.nextDouble();
            
            res.getData().add(new XYChart.Data(i + 1, bufVal - orgVal));
            in.reset();
        }
        return res;
    }
        
    /*
     * Uses the choice box to show/hide the Bar Chart or the Line Chart.
     */
    public void initComboBox()
    {
        graphBox.getItems().addAll("Scatter Chart", "Bar Chart");
        graphBox.getSelectionModel().selectFirst();
        
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
    
    public void initTableBox()
    {
        tableBox.getItems().addAll("Difference", "Series");
        tableBox.getSelectionModel().selectFirst();
        
        tableBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                resetAll();
                
                if(newValue.equals(0))
                {
                    updateSLineChart(bufNum1, bufNum2);
                    updateBarChart();
                }
                else if(newValue.equals(1))
                {
                    updateSLineChartNonDif(bufNum1, bufNum2);
                    updateBarChart();
                }
            }
        } );
    }
    
    public void updateBarChart()
    {
        sChart.getData().clear();
        
        ArrayList<Pair<String, Double>> avgArray = new ArrayList<>();
        ArrayList<Pair<String, Double>> maxArray = new ArrayList<>();
        ArrayList<Pair<String, Double>> minArray = new ArrayList<>();

        sBarAvg.setName("Avg");
        sBarMax.setName("Max");
        sBarMin.setName("Min");
   
        numberAxis.setLabel("Time [ms]");
        nameAxis.setLabel("Axis");

        MinMaxMessage imagePTime = new MinMaxMessage(sSeriesImageP);
        MinMaxMessage pickupTime = new MinMaxMessage(sSeriesPickUp);
        MinMaxMessage throwTime = new MinMaxMessage(sSeriesThrow);
        MinMaxMessage totalTime = new MinMaxMessage(sSeriesTotal);

        MinMaxMessage[] minMaxAvg = {imagePTime, pickupTime, throwTime, totalTime};
  
        for(MinMaxMessage m : minMaxAvg)
        {
            m.calcMinMaxAvg();
            avgArray.add(new Pair(m.getName(), m.getAvg()));
            minArray.add(new Pair(m.getName(), m.getMin()));
            maxArray.add(new Pair(m.getName(), m.getMax()));
        }
        
        for(int i = 0; i < avgArray.size(); i++)
        {
            sBarAvg.getData().add(new XYChart.Data(avgArray.get(i).getKey(), avgArray.get(i).getValue()));
            sBarMin.getData().add(new XYChart.Data(minArray.get(i).getKey(), minArray.get(i).getValue()));
            sBarMax.getData().add(new XYChart.Data(maxArray.get(i).getKey(), maxArray.get(i).getValue()));
        }
    }
    
    private void addBarSeriesListener(BooleanProperty selected, BarChart lc, String nameOfBut) 
    {
        selected.addListener((observable, wasSelected, isSelected) -> 
        {
            if (isSelected) 
            {
               nameAxis.getCategories().clear();
               sBarAvg.getData().clear();
               sBarMax.getData().clear();
               sBarMin.getData().clear();
               updateBarChart();
               
               barCategories.add(nameOfBut);
               ObservableList<String> list = FXCollections.observableArrayList(barCategories);
               nameAxis.setCategories(list);
               try
               {
                   lc.getData().addAll(sBarMin, sBarAvg, sBarMax);
               }
               catch(IllegalArgumentException e)
               {
                   System.out.println("Failed to add information: ");
                   e.printStackTrace();
               }
            } 
            else 
            {
                nameAxis.getCategories().clear();
                sBarAvg.getData().clear();
                sBarMax.getData().clear();
                sBarMin.getData().clear();
                updateBarChart();
                
                barCategories.remove(nameOfBut);
                
                ObservableList<String> list = FXCollections.observableArrayList(barCategories);
                nameAxis.setCategories(list);
                
                if(!barCategories.isEmpty())
                {
                    lc.getData().addAll(sBarMin, sBarAvg, sBarMax);
                }
            }
        });
    }
    
    private void addSSeriesListener(BooleanProperty selected, final Series series, TableView tv, ScatterChart lc) 
    {
        MinMaxMessage mmm = new MinMaxMessage(series);
        
        selected.addListener((observable, wasSelected, isSelected) -> 
        {
            if(!sImagePBox.isSelected() && !sPickupBox.isSelected() && !sThrowBox.isSelected() && !sTotalBox.isSelected())
            {
                resetAll();
            }
            
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
    
    public void resetAll()
    {
        sImagePBox.selectedProperty().setValue(false);
        sThrowBox.selectedProperty().setValue(false);
        sPickupBox.selectedProperty().setValue(false);
        sTotalBox.selectedProperty().setValue(false);
                
        sSeriesImageP.getData().clear();
        sSeriesPickUp.getData().clear();
        sSeriesThrow.getData().clear();
        sSeriesTotal.getData().clear();
        
        sBarMin.getData().clear();
        sBarMax.getData().clear();
        sBarAvg.getData().clear();
        
        sGraph.getData().clear();
        sChart.getData().clear();
        
        sTimeView.getItems().clear();
    }
    
    public void calcPercentage(int num1, int num2)
    {
        ArrayList<Boolean> buf = con.getTrueFalseHit(num1, num2);
        
        for(boolean b : buf)
        {
            if(b)
            {
                hitPerVal++;
            }
            System.out.println(hitPerVal + " bufSize: " + buf.size());
            
        }
        
        hitPerVal /= buf.size();
        buf.clear();
        buf = con.getTrueFalsePickUp(num1, num2);
        
        for(Boolean b : buf)
        {
            if(b)
            {
                pickPerVal++;
            }
        }
        
        pickPerVal /= buf.size();
        pickPerVal *= 100;
        hitPerVal *= 100;
        
        pickUpPer.setText(String.format("%.2f", pickPerVal) + "%");
        hitPer.setText(String.format("%.2f", hitPerVal) + "%");
    }
    
}
