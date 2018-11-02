package jdbcsoftwaregui;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.chart.XYChart.Series;

public class MinMaxMessage 
{
    private double min, max, avg;
    private String name;
    private Series series;
    
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
        min = 10000;
        double num = 0, sum = 0;
        ArrayList list = new ArrayList(series.getData());
        ArrayList<Double> listValues = new ArrayList<>();
        Scanner in;

        for(int i = 0; i < list.size(); i++)
        {
            in = new Scanner(list.get(i).toString()).useDelimiter(",");
            in.next();
            listValues.add(Double.parseDouble(in.next()));
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

    public void calcMinMaxAvg(Series s)
    {
        double num = 0, sum = 0, bufVal;
        ArrayList list = new ArrayList(series.getData());
        ArrayList fullList = new ArrayList(s.getData());
        
        ArrayList<Double> listValues = new ArrayList<>();
        Scanner in;

        for(int i = 0; i < list.size(); i++)
        {
            in = new Scanner(list.get(i).toString()).useDelimiter(",");
            in.next();
            bufVal = in.nextDouble();
            
            in = new Scanner(fullList.get(i).toString()).useDelimiter(",");
            in.next();
            
            listValues.add(in.nextDouble() - bufVal);
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
    
    public MinMaxMessage getDiff(MinMaxMessage m)
    {
        MinMaxMessage res = new MinMaxMessage();
        
        res.setMin(m.getMin() - min);
        res.setMax(m.getMax() - max);
        res.setAvg(m.getAvg() - avg);
        
        return res;
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
    
    @Override
    public String toString()
    {
        return max + " " + min + " " + avg;
    }
}
