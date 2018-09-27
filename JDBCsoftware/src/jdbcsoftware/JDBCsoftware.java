package jdbcsoftware;

import java.sql.*;

public class JDBCsoftware 
{
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static Connection con = null;
    
    private static String url = "jdbc:mysql://localhost:3306/UR5?useSSL=false";
    private static String usr = "java";
    private static String pwd = "123";
    
    private static String query = "SELECT * FROM PickUpLoc;";
    
    
    public static void main(String[] args) 
    {
        try
        {
            con = DriverManager.getConnection(url, usr, pwd);
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                System.out.printf("%-10s %-15s %n","Datetime: ", rs.getString("Time"));
                System.out.printf("%-10s %-15s %n", "Pos X: ", rs.getInt("PosX"));
                System.out.printf("%-10s %-15s %n", "Pos Y: ", rs.getInt("PosY"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        
    }
    
}
