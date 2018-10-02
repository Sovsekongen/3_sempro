package jdbcCon;

public class TableVal 
{

    private int posX;
    private int posY;
    private int throwNr;
    private String date;
    
    public TableVal(String date, int posx, int posy, int throwNr)
    {
        this.date = date;
        this.posY = posy;
        this.posX = posx;
        this.throwNr = throwNr;
    }

    public int getThrowNr()
    {
        return throwNr;
    }

    public void setThrowNr(int throwNr)
    {
        this.throwNr = throwNr;
    }

    public int getPosX()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    
    
}
