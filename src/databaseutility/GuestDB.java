/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseutility;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author akash
 */
public class GuestDB 
{
 public static Vector<Vector<String>> SearchRecords(String LandID,String State,String City,String Type,String Areamin,String Areamax,String Pricemin,String Pricemax)
    {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = new String();
        
        Vector<Vector<String>> ans = null;
        
        try
        {
                sql = "select l.LandID,l.Area,l.LandType,l.Address,l.City,l.State,a.Price from LandDetails l left outer join (select LandID,max(Price) as Price from Registration group by LandID) a on l.LandID=a.LandID where (l.LandID = ? or ? = '') and (l.State = ? or ? = '') and (l.City = ? or ? = '') and (l.LandType = ?  or ? = '') and (l.area between ? and ? or ? = '') and (a.Price between ? and ? or ? = '');"; 
            
                ps = Server.conn.prepareStatement(sql);
          
                ps.setString(3,State);
                ps.setString(4,State);
                ps.setString(5,City);
                ps.setString(6,City);
                ps.setString(7,Type);
                ps.setString(8,Type);
                
                ps.setString(1, LandID);
                ps.setString(2, LandID);
                
                if((Areamin.equals("")))
                {
                    ps.setDouble(9,java.sql.Types.NULL);
                    ps.setDouble(10,java.sql.Types.NULL);
                    ps.setString(11,"");
                }
                
                else
                {
                    ps.setDouble(9,Double.parseDouble(Areamin));
                    ps.setDouble(10 ,Double.parseDouble(Areamax));
                    ps.setDouble(11,Double.parseDouble(Areamin));
                }
                
                if((Pricemin.equals("")))
                {
                    ps.setDouble(12,java.sql.Types.NULL);
                    ps.setDouble(13,java.sql.Types.NULL);
                    ps.setString(14,"");
                }
                
                else
                {
                    ps.setDouble(12,Double.parseDouble(Pricemin));
                    ps.setDouble(13 ,Double.parseDouble(Pricemax));
                    ps.setDouble(14,Double.parseDouble(Pricemin));
                }   
            
            JOptionPane.showMessageDialog(null, ps.toString());
            rs = ps.executeQuery();
            ans = new Vector<Vector<String>>();
	    	
            while(rs.next())
            {
		Vector<String> record = new Vector<String>();
		    
		record.add(rs.getString("LandID"));
		record.add(rs.getString("State"));
		record.add(rs.getString("City"));
		record.add(rs.getString("LandType"));
                record.add(rs.getString("Area"));
		record.add(rs.getString("Price"));
		     
		ans.add(record);
            }
		
        }
        
        catch(SQLException sqe)
        {
            sqe.printStackTrace();
        }
                
        return (ans);
    }    
}
