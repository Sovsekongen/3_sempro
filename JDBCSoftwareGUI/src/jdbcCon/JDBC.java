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
    private String pic;
    private int posX;
    private int posY;
    private int throwNr;
    
    private int imagePTime;
    private int pickUpTime;
    private int throwTime;
    private int totalTime;
    
    private double radius;
    private String colour;
    private String shape;
    private boolean hitTarget;
    private boolean pickTarget;

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
    
    public void deleteRow(int throwNr)
    {
        String deletePick = "DELETE FROM pickupobject WHERE throwNr = " + throwNr;
        String deletePos = "DELETE FROM pickuppos WHERE throwNr = " + throwNr;
        String deleteTime = "DELETE FROM exetime WHERE throwNr = " + throwNr;
        
        String[] queries = {deletePick, deleteTime, deletePos};
        try
        {
            stmt = con.createStatement();
            for(String s : queries)
            {
                stmt.executeUpdate(s);
            }
            
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
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
                pic = rs.getString("pic");
                hitTarget = rs.getBoolean("hitTarget");
                pickTarget = rs.getBoolean("pickTarget");
                

                vals[i] = new ObjectVal(throwNr, radius, colour, shape, pic, hitTarget, pickTarget);
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

    public TimeVal[] getTimeArray()
    {
        TimeVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM exetime;");

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
    
    public void updateObject(String query)
    {
        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public AllVal[] getThrowNum(int num)
    {
        AllVal[] vals = null;
        PickUpVal p = null;
        TimeVal tv = null;
        ObjectVal o = null;

        String condition = " WHERE throwNr = " + num + ";";
        String select = "SELECT * FROM ";
        String[] tables = {"exetime", "pickupobject", "pickuppos"};
        
        
        try
        {
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM pickuppos");
            
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            vals = new AllVal[numOfRows];
            
            for(int j = 0; j < 1; j++)
            {
                for(String t : tables)
                {
                    rs.beforeFirst();
                    rs = stmt.executeQuery(select + t + condition);

                    if(t.equals(tables[0]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            imagePTime = rs.getInt("openCVTime");
                            pickUpTime = rs.getInt("pickUpTime");
                            throwTime = rs.getInt("throwTime");
                            totalTime = rs.getInt("cycleTime");
                            throwNr = rs.getInt("throwNr");
                        }
                        tv = new TimeVal(throwNr, pickUpTime, imagePTime, throwTime, totalTime);
                    }
                    else if(t.equals(tables[1]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            radius = rs.getInt("radius");
                            colour = rs.getString("colour");
                            shape = rs.getString("shape");
                            throwNr = rs.getInt("throwNr");
                            pic = rs.getString("pic");
                            hitTarget = rs.getBoolean("hitTarget");
                            pickTarget = rs.getBoolean("pickTarget");
                        }
                        
                        o = new ObjectVal(throwNr, radius, colour, shape, pic, hitTarget, pickTarget);
                    }
                    else if(t.equals(tables[2]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            date = rs.getString("dbTime");
                            posX = rs.getInt("posX");
                            posY = rs.getInt("posY");
                            throwNr = rs.getInt("throwNr");
                        }
                        
                        p = new PickUpVal(throwNr, date, posX, posY);
                    }
                }
                
                vals[j] = new AllVal(p, tv, o);
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals;
    }
    
    public AllVal[] getThrowNum()
    {
        AllVal[] vals = null;
        PickUpVal p = null;
        TimeVal tv = null;
        ObjectVal o = null;

        String select = "SELECT * FROM ";
        String[] tables = {"exetime;", "pickupobject;", "pickuppos;"};
        
        
        try
        {
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM pickuppos");
            
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            
            vals = new AllVal[numOfRows];
            
            for(int j = 0; j < numOfRows; j++)
            {
                for(String t : tables)
                {
                    rs.beforeFirst();
                    rs = stmt.executeQuery(select + t);

                    if(t.equals(tables[0]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            imagePTime = rs.getInt("openCVTime");
                            pickUpTime = rs.getInt("pickUpTime");
                            throwTime = rs.getInt("throwTime");
                            totalTime = rs.getInt("cycleTime");
                            throwNr = rs.getInt("throwNr");
                        }
                        tv = new TimeVal(throwNr, pickUpTime, imagePTime, throwTime, totalTime);
                    }
                    else if(t.equals(tables[1]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            radius = rs.getInt("radius");
                            colour = rs.getString("colour");
                            shape = rs.getString("shape");
                            throwNr = rs.getInt("throwNr");
                            pic = rs.getString("pic");
                            hitTarget = rs.getBoolean("hitTarget");
                            pickTarget = rs.getBoolean("pickTarget");
                        }
                        
                        o = new ObjectVal(throwNr, radius, colour, shape, pic, hitTarget, pickTarget);
                    }
                    else if(t.equals(tables[2]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            date = rs.getString("dbTime");
                            posX = rs.getInt("posX");
                            posY = rs.getInt("posY");
                            throwNr = rs.getInt("throwNr");
                        }
                        
                        p = new PickUpVal(throwNr, date, posX, posY);
                    }
                }
                
                vals[j] = new AllVal(p, tv, o);
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals;
    }
    
    public AllVal[] getThrowNum(int num1, int num2)
    {
        AllVal[] vals = null;
        
        String condition = " WHERE throwNr BETWEEN " + num1 + " AND " + num2 + ";";
        String select = "SELECT * FROM ";
        String[] tables = {"exetime", "pickupobject", "pickuppos"};
        
        try
        {
            stmt = con.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM pickuppos WHERE throwNr BETWEEN " + num1 + " AND " + num2);
            
            int numOfRows = 0;
            
            while(rs.next())
            {
                numOfRows++;
            }
            rs.beforeFirst();
            
            vals = new AllVal[numOfRows];
            PickUpVal[] p = new PickUpVal[numOfRows];
            TimeVal[] tv = new TimeVal[numOfRows];
            ObjectVal[] o = new ObjectVal[numOfRows];
                
            for(int j = 0; j < numOfRows; j++)
            {
                for(String t : tables)
                {
                    
                    rs = stmt.executeQuery(select + t + condition);

                    if(t.equals(tables[0]))
                    {
                        for(int i = 0; rs.next(); i++)
                        {
                            imagePTime = rs.getInt("openCVTime");
                            pickUpTime = rs.getInt("pickUpTime");
                            throwTime = rs.getInt("throwTime");
                            totalTime = rs.getInt("cycleTime");
                            throwNr = rs.getInt("throwNr");
                            
                            tv[i] = new TimeVal(throwNr, pickUpTime, imagePTime, throwTime, totalTime);
                        }
                    }
                    else if(t.equals(tables[1]))
                    {
                        rs.beforeFirst();
                        
                        for(int i = 0; rs.next(); i++)
                        {
                            radius = rs.getInt("radius");
                            colour = rs.getString("colour");
                            shape = rs.getString("shape");
                            throwNr = rs.getInt("throwNr");
                            pic = rs.getString("pic");
                            hitTarget = rs.getBoolean("hitTarget");
                            pickTarget = rs.getBoolean("pickTarget");
                            
                            o[i] = new ObjectVal(throwNr, radius, colour, shape, pic, hitTarget, pickTarget);
                        }
                    }
                    else if(t.equals(tables[2]))
                    {
                        rs.beforeFirst();
                        
                        for(int i = 0; rs.next(); i++)
                        {
                            date = rs.getString("dbTime");
                            posX = rs.getInt("posX");
                            posY = rs.getInt("posY");
                            throwNr = rs.getInt("throwNr");
                            
                            p[i] = new PickUpVal(throwNr, date, posX, posY);
                        }
                    }
                }
                
                vals[j] = new AllVal(p[j], tv[j], o[j]);
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
        return vals;
    }
}
