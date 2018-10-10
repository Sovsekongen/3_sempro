package jdbcCon;

import java.sql.*;

public class JDBC
{

    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection con = null;

    private String url = "jdbc:mysql://localhost:3306/sempro?useSSL=false&serverTimezone=EST";
    private String usr;
    private String pwd;
    
    private String date;
    private int posX;
    private int posY;
    private int throwNr;
    
    private int imagePTime;
    private int pickUpTime;
    private int throwTime;
    private int totalTime;
    
    private int points;
    private double oDistance;
    
    private double radius;
    private String colour;
    private String shape;

    public JDBC(String usr, String pwd)
    {
        this.usr = usr;
        this.pwd = pwd;
        
        try
        {
            con = DriverManager.getConnection(url, this.usr, this.pwd);

        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public PickUpVal[] selectQuery(String query)
    {
        PickUpVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            rs.beforeFirst();

            vals = new PickUpVal[numOfRows];
            
            for(int i = 0; rs.next(); i++)
            {
                date = rs.getString("dbTime");
                posX = rs.getInt("posX");
                posY = rs.getInt("posY");
                throwNr = rs.getInt("throwNr");

                vals[i] = new PickUpVal(throwNr, date, posX, posY);
            }
                
        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals.clone();
    }
    
    public ObjectVal[] getObjectArray()
    {
        ObjectVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM pickupobject;");
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            rs.beforeFirst();

            vals = new ObjectVal[numOfRows];
            
            for(int i = 0; rs.next(); i++)
            {
                radius = rs.getInt("radius");
                colour = rs.getString("colour");
                shape = rs.getString("shape");
                throwNr = rs.getInt("throwNr");

                vals[i] = new ObjectVal(throwNr, radius, colour, shape);
            }
                
        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals.clone();
    }
    
    public PickUpVal[] getPickUpArray()
    {
        PickUpVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM pickuppos;");
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            rs.beforeFirst();

            vals = new PickUpVal[numOfRows];
            
            for(int i = 0; rs.next(); i++)
            {
                date = rs.getString("dbTime");
                posX = rs.getInt("posX");
                posY = rs.getInt("posY");
                throwNr = rs.getInt("throwNr");

                vals[i] = new PickUpVal(throwNr, date, posX, posY);
            }
                
        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals.clone();
    }

    public ResultVal[] getResultArray()
    {
        ResultVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM throw;");
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            rs.beforeFirst();

            vals = new ResultVal[numOfRows];
            
            for(int i = 0; rs.next(); i++)
            {
                points = rs.getInt("points");
                oDistance = rs.getInt("oDistance");
                throwNr = rs.getInt("throwNr");

                vals[i] = new ResultVal(throwNr, points, oDistance);
            }
                
        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals.clone();
    }
    
    public TimeVal[] getTimeArray()
    {
        TimeVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM exetime;");
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            rs.beforeFirst();

            vals = new TimeVal[numOfRows];
            
            for(int i = 0; rs.next(); i++)
            {
                imagePTime = rs.getInt("openCVTime");
                pickUpTime = rs.getInt("pickUpTime");
                throwTime = rs.getInt("throwTime");
                totalTime = rs.getInt("cycleTime");
                throwNr = rs.getInt("throwNr");

                vals[i] = new TimeVal(throwNr, pickUpTime, imagePTime, throwTime, totalTime);
            }
                
        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals.clone();
    }
    
    public void insertPickupPos(String date, int posX, int posY)
    {
        try
        {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM UR5;");     
            
            rs.moveToInsertRow();
            rs.updateString("tDate", date);
            rs.updateInt("posX", posX);
            rs.updateInt("posY", posY);
            
            rs.insertRow();
            rs.beforeFirst();
        }
        catch(SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally
        {
            if(stmt != null) 
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                } 
            }
        }
    }
}
