package com.github.rosjava.rosjava_catkin_package_a.my_pub_sub_tutorial;

import java.sql.*;
import org.apache.commons.logging.Log;
import org.ros.node.ConnectedNode;

public class JDBC
{

    private Statement stmt  = null;
    private ResultSet rs    = null;
    private Connection con  = null;

    private String url = "jdbc:mysql://localhost:3306/UR5?useSSL=false&serverTimezone=EST";
    private String usr;
    private String pwd;
    private String date;

    private int posX;
    private int posY;
    private int throwNr;
    private int throwNr2 = 1;
    private int openCVExeTime;
    private int cycleTime;

    public JDBC(String usr, String pwd, Log log)
    {
        this.usr = usr;
        this.pwd = pwd;
        
        try
        {
            con = DriverManager.getConnection(url, this.usr, this.pwd);
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            log.error("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    //Method that inserts values from openCV publisher to database
    //This method has to be called BEFORE insertExeTime() because this method gets the Primary Key(throwNr) and saves it to the throwNr2 variable
    public void insertOpenCVData(String date, int posX, int posY, int cvTime, int radius, String colour, String shape, String pic, Log log)
    {

        try
        {
            //Set openCVExeTime so that cycleTime can be calculated in insertExeTime()
            openCVExeTime = cvTime;
            //Queries for inserting data into database
            String query1 = "INSERT INTO PickUpLoc (PosX, PosY, Time) VALUES ("+ posX + ","+ posY +",'"+ date +"');";
            String query2 = "INSERT INTO ExeTime (throwNr, openCVTime) VALUES (LAST_INSERT_ID(), "+ cvTime +");";
            String query3 = "INSERT INTO PickUpObject VALUES (LAST_INSERT_ID(), "+ radius +", '"+ colour +"', '"+ shape +"', '"+ pic +"');";


            con = DriverManager.getConnection(url, usr, pwd);
            stmt = con.createStatement();

            //Execute queries
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);

            //Get current throwNr from database so insertExeTime() inserts into correct throwNr
            String throwNr1 = "SELECT throwNR FROM PickUpLoc ORDER BY throwNr DESC LIMIT 1;";
            rs = stmt.executeQuery(throwNr1);
            rs.next();
            throwNr2 = rs.getInt("throwNr");
            
        }
        catch(SQLException e)
        {
            log.error("SQLException: " + e.getMessage());
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

    //Method that inserts data from UR publisher to databse
    //This method has to be called AFTER insertOpenCVData()
    public void insertExeTime(int pickUpTime, int throwTime, Log log)
    {

        try
        {
            //Calculates cycleTime
            cycleTime = openCVExeTime + pickUpTime + throwTime;
            //Query for inserting data into database
            String query = "UPDATE ExeTime SET pickUpTime = "+ pickUpTime+", throwTime = "+ throwTime +", cycleTime = "+ cycleTime +" WHERE throwNr = "+ throwNr2 +";";

            con = DriverManager.getConnection(url, usr, pwd);
            stmt = con.createStatement();

            stmt.executeUpdate(query);

        }
        catch(SQLException e)
        {
            log.error("SQLException: " + e.getMessage());
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


    //This method is not used, since there is no planned way to get this data automaticly
    public void insertThrow(int points, int oDistance, Log log)
    {

        try
        {
            String query = "INSERT INTO Throw VALUES ("+ throwNr2 +", "+ points +", "+ oDistance +");";
            con = DriverManager.getConnection(url, usr, pwd);
            stmt = con.createStatement();

            stmt.executeUpdate(query);

        }
        catch(SQLException e)
        {
            log.error("SQLException: " + e.getMessage());
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











