package jdbcCon;

public class AllVal 
{
    private double posX;
    private double posY;
    private int throwNum;
    private String timestamp;
    private int pickUpTime;
    private int imagePTime;
    private int throwTime;
    private int totalTime;
    private double radius;
    private String colour;
    private String shape;
    private String pic;
    private boolean hitTarget;
    private boolean pickTarget;

    public AllVal(double posX, double posY, int throwNum, String timestamp, int pickUpTime, int imagePTime, int throwTime, int totalTime, double radius, String colour, String shape, String pic, boolean hitTarget, boolean pickTarget)
    {
        this.posX = posX;
        this.posY = posY;
        this.throwNum = throwNum;
        this.timestamp = timestamp;
        this.pickUpTime = pickUpTime;
        this.imagePTime = imagePTime;
        this.throwTime = throwTime;
        this.totalTime = totalTime;
        this.radius = radius;
        this.colour = colour;
        this.shape = shape;
        this.pic = pic;
        this.hitTarget = hitTarget;
        this.pickTarget = pickTarget;
    }
    
    public AllVal(PickUpVal p, TimeVal t, ObjectVal o)
    {
        this.posX = p.getPosX();
        this.posY = p.getPosY();
        this.throwNum = p.getThrowNum();
        this.timestamp = p.getTimestamp();
        this.pickUpTime = t.getPickUpTime();
        this.imagePTime = t.getImagePTime();
        this.throwTime = t.getThrowTime();
        this.totalTime = t.getTotalTime();
        this.radius = o.getRadius();
        this.colour = o.getColour();
        this.shape = o.getShape();
        this.pic = o.getPic();
        this.hitTarget = o.isHitTarget();
        this.pickTarget = o.isPickTarget();
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

    public int getThrowNum()
    {
        return throwNum;
    }

    public void setThrowNum(int throwNum)
    {
        this.throwNum = throwNum;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public int getPickUpTime()
    {
        return pickUpTime;
    }

    public void setPickUpTime(int pickUpTime)
    {
        this.pickUpTime = pickUpTime;
    }

    public int getImagePTime()
    {
        return imagePTime;
    }

    public void setImagePTime(int imagePTime)
    {
        this.imagePTime = imagePTime;
    }

    public int getThrowTime()
    {
        return throwTime;
    }

    public void setThrowTime(int throwTime)
    {
        this.throwTime = throwTime;
    }

    public int getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(int totalTime)
    {
        this.totalTime = totalTime;
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

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    
}
