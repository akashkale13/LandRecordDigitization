/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class RegistrationDB 
{
    public static String checkOwnerCount(String landid)
    {
        String str = new String();
        try 
        {
            PreparedStatement ps = Server.conn.prepareStatement("select count(RegistrationID),RegistrationID from LandOwners where LandID = ? AND OwnerStatus = 'Current' group by RegistrationID");
            ps.setInt(1, Integer.parseInt(landid));
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                str = rs.getString("count(RegistrationID)");
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
    
    public static int addRegistration(String landid ,String oldregid , String price)
    {
        try 
        {
            PreparedStatement ps = Server.conn.prepareStatement("insert into Registration values(?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2,null);
            ps.setDate(3,null);
            ps.setString(4, null);
            ps.setString(5, null);
            String str = ps.toString();
            int i =ps.executeUpdate();
            System.out.println(i);
        } catch (SQLException ex) 
        {
        
        }
        return 1;
    }
    public static void addOwners(String []buyerpans ,String []buyershares ,String landid ,int newregid)
    {
        try {
            //update current owners to previous
            PreparedStatement ps1 = Server.conn.prepareStatement("update LandOwners set OwnerStatus = 'Previous' where LandID = ? AND OwnerStatus='Currrent'");
            ps1.setInt(1,Integer.parseInt(landid));
            ps1.executeUpdate();
            
            //add new owners
            PreparedStatement ps2 = Server.conn.prepareStatement("insert into LandOwners values(?,?,?,?,?)");
            int i = 0;
            while(i < buyerpans.length)
            {
                ps2.setInt(1,Integer.parseInt(buyerpans[i]));
                ps2.setInt(2,Integer.parseInt(landid));
                ps2.setInt(3,Integer.parseInt(buyershares[i]));
                ps2.setString(4,"Current");
                ps2.setInt(5,newregid);
                ps2.executeUpdate();
                ++i;
            }
        } 
        catch (SQLException ex) 
        {
        }
    }    
}
