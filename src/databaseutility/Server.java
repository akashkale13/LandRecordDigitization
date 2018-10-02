/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import java.io.FileNotFoundException;
import java.sql.*;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author akash
 */
public class DatabaseConnector 
{
    static Connection conn = null;
    
    public static void connectToDB()
    {
        final String jdbcdriver = "com.mysql.jdbc.Driver";
        final  String url = "jdbc:mysql://localhost:3306/akashdb";
        
        try
        {
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(url,"akash","akash@13");
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void checkLogin(String username ,String password)
    {
        
    }
}
