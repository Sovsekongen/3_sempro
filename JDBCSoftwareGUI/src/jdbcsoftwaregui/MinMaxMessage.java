package jdbcsoftwaregui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Series;

public class MinMaxMessage 
{

    private double min, max, avg;
    private String name;
    Series series;
    
    public MinMaxMessage()
    {
        
    }
    
    public MinMaxMessage(Series series)
    {
        this.series = series;
        this.min = 10000;
        this.max = 1;
        this.avg = 0;
        this.name = series.getName();
    }
    
    public void calcMinMaxAvg()
    {
        double num = 0, sum = 0;
        this.name = series.getName();
        ObservableList newList = series.getData();
        ArrayList list = new ArrayList(newList);
        
        ArrayList<Double> listValues = new ArrayList<Double>();
        String val = "";
        String length = "";
        
        for(int i = 0; i < newList.size(); i++)
        {
            length = list.get(i).toString();
            if(length.length() == 16)
            {
                val = list.get(i).toString().substring(7, 10);
            }
            else if(length.length() == 17)
            {
                val = list.get(i).toString().substring(7, 11);
            }
            else if(length.length() < 16)
            {
                val = list.get(i).toString().substring(7, 9);
            }
            
            listValues.add(Double.parseDouble(val));
        }
        
        for(int i = 0; i < listValues.size(); i++)
        {
            num = listValues.get(i);
            
            if(num < min)
            {
                min = num;
            }
            
            if(num > max)
            {
                max = num;
            }
            
            sum += num;
        }
        avg = sum / listValues.size();
    }

    public double getMin()
    {
        return min;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getAvg()
    {
        return avg;
    }

    public void setAvg(double avg)
    {
        this.avg = avg;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Series getSeries()
    {
        return series;
    }

    public void setSeries(Series series)
    {
        this.series = series;
    }
    
    
}
