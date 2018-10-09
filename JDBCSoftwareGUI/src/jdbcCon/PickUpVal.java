package jdbcCon;

public class PickUpVal 
{

    private double posX;
    private double posY;
    private int throwNum;
    private String timestamp;
    
    public PickUpVal(int throwNr, String timestamp, int posX, int posY)
    {
        //this.timestamp = new SimpleStringProperty(timestamp);
        this.timestamp = timestamp;
        this.posY = posY;
        this.posX = posX;
        this.throwNum = throwNr;
    }

    public int getThrowNum()
    {
        return throwNum;
    }

    public void setThrowNum(int throwNum)
    {
        this.throwNum = throwNum;
    }

    public double getPosX()
    {
        return posX;
    }

    public void setPosX(double posX)
    {
        this.posX = posX;
    }

    public double getPosY()
    {
        return posY;
    }

    public void setPosY(double posY)
    {
        this.posY = posY;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String date)
    {
        this.timestamp = date;
    }
    
    
}
