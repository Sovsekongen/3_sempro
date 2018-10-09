package jdbcCon;

public class ResultVal 
{
    private int points;
    private double distanceFromO;

    public ResultVal(int points, double distanceFromO)
    {
        this.points = points;
        this.distanceFromO = distanceFromO;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public double getDistanceFromO()
    {
        return distanceFromO;
    }

    public void setDistanceFromO(double distanceFromO)
    {
        this.distanceFromO = distanceFromO;
    }
}
