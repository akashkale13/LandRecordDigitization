/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import static databaseutility.Server.conn;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class Transaction 
{
    //private static Connection conn;
    //private static Vector<Vector> v;
    private int landid;
    private double price;
    
    public Transaction(String lid)
    {
        landid = Integer.parseInt(lid);
    }
    
    public String checkSellerCount()
    {
        String str = new String();
        try 
        {
            PreparedStatement ps = Server.conn.prepareStatement("select count(RegistrationID) as cntregid,RegistrationID from LandOwners where LandID = ? AND OwnerStatus = 'Current' group by RegistrationID");
            ps.setInt(1, landid);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                str = rs.getString("cntregid");
                str += ",";
                str += rs.getString("RegistrationID");
            }
            else
            {
                str = "ERROR";
            }
        } 
        catch (SQLException ex) 
        {
        }
        return str;
    }
    
    public int register(String oldregid , String price)
    {
        String sql = "insert into Registration values(?,?,?,?,?)";
        String helperquery = "select RegistrationID from Registration order by RegistrationID desc";
        try 
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(helperquery);
        
           //need to solve this
            int regid = -1;
            if(rs.first())
            {
                regid = rs.getInt("RegistrationID");
                regid++;
                ps.setInt(1, regid); 
            }
            else
            {
                //send back to login ie cae of empty db
            }
            
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            ps.setInt(2, landid);
            ps.setDate(3, date);
            ps.setInt(4, Integer.parseInt(oldregid));
            ps.setDouble(5, Double.parseDouble(price));

            ps.executeUpdate();
        
            return regid;
        } 
        catch (SQLException ex) 
        {
            //TODO need to resolve this 
            return -1;
        }
        
        
    }
    public void addOwners(String []buyerpans ,String []buyershares,String newregid)
    {
        try
        {
            //update current owners to previous
            String sql = "update LandOwners set OwnerStatus = 'Previous' where LandID = ? AND OwnerStatus='Currrent'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, landid);
            ps.executeUpdate();
            
            //add new owners
            sql = "insert into LandOwners values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            int i = 0;
            while(i < buyerpans.length)
            {
                ps.setInt(1, Integer.parseInt(buyerpans[i]));
                ps.setInt(2, landid);
                ps.setInt(3, Integer.parseInt(buyershares[i]));
                ps.setString(4, "Current");
                ps.setString(5, Integer.parseInt(newregid));
                ps.executeUpdate();
                ++i;
            }
        } 
        catch (SQLException ex) 
        {
        }
    }    
}
