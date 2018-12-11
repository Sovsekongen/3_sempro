package jdbcCon;

public class ObjectVal 
{
    private double radius;
    private String colour;
    private String shape;
    private String pic;
    private int throwNr;
    private boolean hitTarget;
    private boolean pickTarget;

    /*
     * Denne klasse opbevarer værdier hivet ud af PickUpObject tabellen.
     */
    public ObjectVal(int throwNr, double radius, String colour, String shape, String pic, boolean hitTarget, boolean pickTarget)
    {
        this.throwNr = throwNr;
        this.radius = radius;
        this.colour = colour;
        this.shape = shape;
        this.pic = pic;
        this.hitTarget = hitTarget;
        this.pickTarget = pickTarget;
    }

    public boolean isHitTarget()
    {
        return hitTarget;
    }

    public void setHitTarget(boolean hitTarget)
    {
        this.hitTarget = hitTarget;
    }

    public boolean isPickTarget()
    {
        return pickTarget;
    }

    public void setPickTarget(boolean pickTarget)
    {
        this.pickTarget = pickTarget;
    }

    
    
    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public int getThrowNr()
    {
        return throwNr;
    }

    public void setThrowNr(int throwNr)
    {
        this.throwNr = throwNr;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }
    
    
}
