package jdbcCon;

/*
 * Ikke brugt, men opbevarer data fra den ikke implementerede funktion Result.
 */
public class ResultVal 
{
    private int points;
    private double distanceFromO;
    private int throwNr;
    
    public ResultVal(int throwNr, int points, double distanceFromO)
    {
        this.throwNr = throwNr;
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

    public int getThrowNr()
    {
        return throwNr;
    }

    public void setThrowNr(int throwNr)
    {
        this.throwNr = throwNr;
    }
    
    
}
