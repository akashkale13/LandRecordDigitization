/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import static databaseutility.Server.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author anuj
 */
public class SurveyDB
{
    public static void addLand(Map<String,String> hm)
    {
        String lid = hm.get("LandID");
        String ltype = hm.get("LandType");
        String ar = hm.get("Area");
        String st = hm.get("State");
        String ct = hm.get("City");
        String addr = hm.get("Address");
                
        //String sql = String.format("INSERT INTO LandDetailS VALUES(%s,%s,%s,%s,%s,%s)",lid,ar,ltype,addr,ct,st);
        
        String sql = "insert into LandDetails values(?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(lid));
            ps.setDouble(2, Double.parseDouble(ar));
            ps.setString(3, ltype);
            ps.setString(4, addr);
            ps.setString(5, ct);
            ps.setString(6, st);
        
            int result = ps.executeUpdate();
 
            if(result!=0)
            {
                JOptionPane.showMessageDialog(null, "Successfully entered information","Information",JOptionPane.INFORMATION_MESSAGE);
            }
            if(result == 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid input" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(SQLException sqe)
        {
            sqe.printStackTrace();
        }
    }
}
