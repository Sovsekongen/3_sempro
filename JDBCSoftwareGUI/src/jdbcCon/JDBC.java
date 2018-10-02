package jdbcCon;

import java.sql.*;

public class JDBC
{

    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection con = null;

    private String url = "jdbc:mysql://localhost:3306/bib?useSSL=false&serverTimezone=EST";
    private String usr;
    private String pwd;
    
    private String date;
    private int posX;
    private int posY;
    private int throwNr;

    public JDBC(String usr, String pwd)
    {
        this.usr = usr;
        this.pwd = pwd;
        
        try
        {
            con = DriverManager.getConnection(url, usr, pwd);

        } 
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public TableVal[] selectQuery(String query)
    {
        TableVal[] vals = null;
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            vals = new TableVal[columns];
            
            while (rs.next())
            {
                for(int i = 1; i < columns; i++)
                {
                    if(i > 1)
                    {
                        System.out.print("; ");
                    }
                    
                    date = rs.getString("tDate");
                    posX = rs.getInt("posX");
                    posY = rs.getInt("posY");
                    throwNr = rs.getInt("throwNr");
                    
                    TableVal tab = new TableVal(date, posX, posY, throwNr);
                    
                    String columnName = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnName);
                    
                    vals[i] = tab;
                }
                System.out.println(" ");
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

    public void insertPickupPos(String date, int posx, int posy)
    {
        try
        {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM book;");     
            
            rs.moveToInsertRow();
            rs.updateString("", date);
            rs.updateInt("", posx);
            rs.updateInt("", posy);
            
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
