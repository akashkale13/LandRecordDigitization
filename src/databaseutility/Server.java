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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akash
 */
public class Server
{
    static public Connection conn = null;
    
    public static void connectToDB()
    {
        final String jdbcdriver = "com.mysql.jdbc.Driver";
        final  String url = "jdbc:mysql://";
        
        try
        {
            FileReader fr = new FileReader("ip.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            String strlist[] = str.split(",");
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(url+strlist[0]+":3306/"+strlist[2],strlist[1],strlist[3]);
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e)
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
    
    public static void initialize()
    {
        try 
        {
            int k=0;
            Statement st = Server.conn.createStatement();
            st.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            
            st.executeUpdate("DROP TABLE IF EXISTS LandDetails");
            st.executeUpdate("CREATE TABLE LandDetails(LandID int(11) NOT NULL,Area double NOT NULL,LandType varchar(15) NOT NULL,Address varchar(100) NOT NULL,City varchar(20) NOT NULL,State varchar(20) NOT NULL,PRIMARY KEY(LandID))");
     
            st.executeUpdate("DROP TABLE IF EXISTS LandOwners");
            st.executeUpdate("CREATE TABLE LandOwners(PAN varchar(10) NOT NULL,LandID int(11) NOT NULL,Share float NOT NULL,OwnerStatus varchar(10) NOT NULL,RegistrationID int(11) NOT NULL,PRIMARY KEY(PAN,LandID),KEY RegistrationID (RegistrationID),CONSTRAINT LandOwners_ibfk_1 FOREIGN KEY (RegistrationID) REFERENCES Registration (RegistrationID) ON DELETE CASCADE ON UPDATE CASCADE)");
            
            st.executeUpdate("DROP TABLE IF EXISTS Login");
            st.executeUpdate("CREATE TABLE Login(Username varchar(25) DEFAULT NULL,Password varchar(10) DEFAULT NULL)");
            st.executeUpdate("INSERT INTO Login VALUES ('REG01','REG01PASS'),('SUR01','SUR01PASS'),('REG02','REG02PASS'),('SUR02','SUR02PASS')");
            
            st.executeUpdate("DROP TABLE IF EXISTS Person");
            st.executeUpdate("CREATE TABLE Person(PAN varchar(10) NOT NULL,AadharNo bigint(12) NOT NULL,Name varchar(20) DEFAULT NULL,Bank varchar(20) DEFAULT NULL,Account_No bigint(20) DEFAULT NULL,Email varchar(30) DEFAULT NULL,Phone bigint(10) DEFAULT NULL,PRIMARY KEY (PAN))");
            
            st.executeUpdate("DROP TABLE IF EXISTS Registration");
            st.executeUpdate("CREATE TABLE Registration(RegistrationID int(11) NOT NULL AUTO_INCREMENT,LandID int(11) DEFAULT NULL,DateOfTransaction date DEFAULT NULL,Seller int(11) DEFAULT NULL,Price double DEFAULT NULL,PRIMARY KEY (RegistrationID),KEY LandID (LandID),CONSTRAINT Registration_ibfk_1 FOREIGN KEY (LandID) REFERENCES LandDetails (LandID) ON DELETE CASCADE ON UPDATE CASCADE)");
            st.executeUpdate("INSERT INTO Registration VALUES(0,NULL,'1950-01-26',NULL,NULL)");
            
            st.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
           
             JOptionPane.showMessageDialog(null, "Successfully created tables","Message" ,JOptionPane.INFORMATION_MESSAGE);   
        }
        catch (SQLException ex) 
        {  
            
        }
    }
    
    public static boolean checkLogin(String username ,String password) throws SQLException
    {
        String sql = "SELECT * FROM Login where Username = ? AND Password = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        
        boolean check = (ps.executeQuery()).next();
        return check;
    }
    
    public static String validateSeller(String landid, String pan)
    {
       // System.out.println(pan);
        String str = new String();
        try
        {
            String sql = "select * from Person where EXISTS(select * from LandOwners where LandID = ? AND OwnerStatus = 'Current' AND PAN = ?) AND PAN = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(landid));    
            ps.setString(2, pan);
            ps.setString(3, pan);
            
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                str = rs.getString("PAN")+","+rs.getString("Name")+","+rs.getString("Phone")+","+rs.getString("Email");   
            }
            else
            {
                str = "ERROR";
            }
        }
        catch(Exception e)
        {

        }
        
        return str;
    }  
    
    public static boolean checkBuyer(String pan)
    {
        String sql = "select * from Person where PAN=?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(pan.equals(""))
            {
                throw new Exception();
            }
            ps.setString(1, pan);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Thrown");
            return false;
        }    
    }
    
    public static String getPersonData(String pan)
    {
        String str = new String();
        try
        {
            String sql = "select PAN,Name,Email,Phone from Person where PAN = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pan);
            ResultSet rs=ps.executeQuery();

            if(rs.next())
            {
                str = rs.getString("PAN")+","+rs.getString("Name")+","+rs.getString("Phone")+","+rs.getString("Email");   
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e.toString() ,"Error" ,JOptionPane.ERROR_MESSAGE);
        }
        
        return str;
    }

    public static void addPerson(HashMap m ,String aadhar)
    {
        try 
        {
            PreparedStatement ps = conn.prepareStatement("insert into Person values(?,?,?,?,?,?,?)");
            ps.setString(1, (String)m.get("PAN"));
            ps.setLong(2, Long.parseLong(aadhar));
            ps.setString(3, (String)m.get("Name"));
            ps.setString(4, (String)m.get("Bank"));
            ps.setLong(5, Long.parseLong((String)m.get("Account")));
            ps.setString(6,(String)m.get("Email"));
            ps.setLong(7, Long.parseLong((String)m.get("Phone")));
            int result = ps.executeUpdate();
            if(result ==  1)
            {
                JOptionPane.showMessageDialog(null, "Data entered successfully" , "Message",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) 
        {
        }
    }
}
