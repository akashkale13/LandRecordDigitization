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
public class Server
{
    static Connection conn = null;
    
    public static void connectToDB()
    {
        final String jdbcdriver = "com.mysql.jdbc.Driver";
        final  String url = "jdbc:mysql://localhost:3306/LandRecords";
        
        try
        {
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(url,"root","anujk1998");
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
    
    public static boolean checkLogin(String username ,String password) throws SQLException
    {
        String sql = "SELECT * FROM Login where Username = ? AND Password = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        
        boolean check = ps.execute();
        
        return check;
    }
}
