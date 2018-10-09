package jdbcCon;

public class ObjectVal 
{

    private double radius;
    private String colour;
    private String shape;

    public ObjectVal(double radius, String colour, String shape)
    {
        this.radius = radius;
        this.colour = colour;
        this.shape = shape;
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
}
