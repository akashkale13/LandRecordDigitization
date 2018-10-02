/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LandRecords;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akash
 */
public class Registration extends JFrame
{
    //top panel variables
    JPanel toppanel;
            
    //middle panel variables
    JPanel middlepanel;
    JPanel buyerpanel;
    JPanel sellerpanel;
    
    JLabel landidlabel;
    JLabel pricelabel;
    JLabel buyerpanlabel;
    JLabel sellerpanlabel;
    JLabel sharelabel;
    
    JTextField landid;
    JTextField price;
    JTextField buyerpan;
    JTextField sellerpan;
    JTextField share;
    
    JButton addbuyer;
    JButton addseller;
    JButton submit;
    
    JTable buyerdata;
    JTable sellerdata;
    
    //bottom panel variables
    JPanel bottompanel;
    
    public Registration()
    {
        instantiate();
    }
    
    void instantiate()
    {
        //top panel variables
        toppanel = new JPanel();
        toppanel.setBackground(new Color(52, 69, 150));
        toppanel.setPreferredSize(new Dimension(0, 100));
        
        //middle panel variables
        middlepanel = new JPanel();
        buyerpanel = new JPanel();
        sellerpanel = new JPanel();
        
        landidlabel = new JLabel("Land ID");
        pricelabel = new JLabel("Price");
        buyerpanlabel = new JLabel("PAN");
        sellerpanlabel = new JLabel("PAN");
        sharelabel = new JLabel("Share");
        
        landid= new JTextField();
        price = new JTextField();
        buyerpan = new JTextField();
        sellerpan = new JTextField();
        share = new JTextField();
        
        addbuyer = new JButton("Add");
        addseller = new JButton("Add");
        submit = new JButton("Submit");
        
        Vector rows = new Vector();
        Vector headers = new Vector();
        headers.add("PAN");
        buyerdata = new JTable(new DefaultTableModel(new Object[][]{} , new String[]{"Name","PAN"}));
        JScrollPane sp = new JScrollPane(buyerdata);
        sp.setPreferredSize(new Dimension(200, 200));
        //design buyerpanel
        buyerpanel.setLayout(new GridBagLayout());
        
        GridBagConstraints g;
        
        buyerpanel.add(sp);
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        buyerpanel.add(buyerdata ,g);
        
        //design sellerpanel
        
        //design middlepanel
        middlepanel.setLayout(new GridBagLayout());
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        middlepanel.add(landidlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;
        middlepanel.add(landid ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        middlepanel.add(pricelabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        middlepanel.add(price ,g);
        
        g = new GridBagConstraints();
        g.gridx = 5;
        g.gridy = 0;
        middlepanel.add(buyerpanel ,g);
        
        //bottom panel variables
        bottompanel = new JPanel();
        bottompanel.setBackground(new Color(52, 69, 150));
        bottompanel.setPreferredSize(new Dimension(0, 50));
        
        //set Frame layout and add components
        setLayout(new BorderLayout());
        add(toppanel, BorderLayout.PAGE_START);
        add(middlepanel, BorderLayout.CENTER);
        add(bottompanel, BorderLayout.PAGE_END);
    

    }
}
