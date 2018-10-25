package jdbcsoftwaregui;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class MinMaxMessage 
{
    private double min, max, avg;
    private String name;
    Series series;
    
    public MinMaxMessage(Series series)
    {
        this.series = series;
        this.min = 10000;
        this.max = 1;
        this.avg = 0;
        this.name = series.getName();
    }
    
    public void calcDifMinMaxAvg(Series s, int num1, int num2)
    {
        calcMinMaxAvg(calcDifSeries(s, num1, num2));
    }
    
    public void calcMinMaxAvg(Series s)
    {
        double num = 0, sum = 0;
        this.name = s.getName();
        ArrayList list = new ArrayList(s.getData());
        
        ArrayList<Double> listValues = new ArrayList<>();
        String val = "";
        String length;
        
        for(int i = 0; i < list.size(); i++)
        {
            length = list.get(i).toString();
            System.out.println(list.get(i));
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
    
    public Series calcDifSeries(Series s1, int num1, int num2)
    {
        double listVal, orgListVal;
        Scanner s;
        Series res = new Series();
        
        ArrayList list = new ArrayList(s1.getData());
        ArrayList orgList = new ArrayList(series.getData());

        for(int i = num1, j = 0; i < num2; i++, j++)
        {
            s = new Scanner(list.get(i).toString()).useDelimiter(",");
            s.next();
            listVal = s.nextDouble();
            
            s = new Scanner(orgList.get(i).toString()).useDelimiter(",");
            s.next();
            orgListVal = s.nextDouble();
            
            res.getData().add(new XYChart.Data(j, (orgListVal - listVal)));
        }
        
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
    
    
}
