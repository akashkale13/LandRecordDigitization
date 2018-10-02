/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LandRecords;

import databaseutility.Server;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akash
 */
public class Registration extends JFrame
{
    JFrame f;
    String user;
    
    //top panel variables
    JPanel toppanel;
    JLabel logout;
    JLabel username;
    
    //middle panel variables
    JPanel middlepanel;
    
    JLabel landidlabel;
    JLabel pricelabel;
    JLabel buyerpanlabel;
    JLabel sellerpanlabel;
    JLabel sharelabel;
    JLabel buyerlabel;
    JLabel sellerlabel;
    
    JTextField landid;
    JTextField price;
    JTextField buyerpan;
    JTextField sellerpan;
    JTextField share;
    
    JButton addbuyer;
    JButton addseller;
    JButton submit;
    
    JTable buyertable;
    JTable sellertable;
    
    //bottom panel variables
    JPanel bottompanel;
    
    public Registration()
    {
        instantiate();
    }
    
    public Registration(JFrame f , String str)
    {
        this.f = f;
        user = str;
        instantiate();
        username.setText("Logged in as "+user);
    }
    
    void instantiate()
    {
        //top panel variables
        toppanel = new JPanel();
        toppanel.setBackground(new Color(52, 69, 150));
        toppanel.setPreferredSize(new Dimension(0, 100));
        
        logout = new JLabel("Log Out");
        username = new JLabel();
        logout.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        logout.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        toppanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 40));
        toppanel.add(username);
        toppanel.add(logout);
        
        //middle panel variables
        middlepanel = new JPanel();
   
        landidlabel = new JLabel("Land ID");
        landidlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        landidlabel.setPreferredSize(new Dimension(80, 40));
        
        pricelabel = new JLabel("Price");
        pricelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pricelabel.setPreferredSize(new Dimension(80, 40));
        
        buyerpanlabel = new JLabel("PAN");
        buyerpanlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        buyerpanlabel.setPreferredSize(new Dimension(80, 40));
        
        sellerpanlabel = new JLabel("PAN");
        sellerpanlabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sellerpanlabel.setPreferredSize(new Dimension(80, 40));
        
        sharelabel = new JLabel("Share");
        sharelabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sharelabel.setPreferredSize(new Dimension(80, 40));
        
        buyerlabel = new JLabel("Buyer Details");
        buyerlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        buyerlabel.setPreferredSize(new Dimension(150, 40));
        
        sellerlabel = new JLabel("Seller Details");
        sellerlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        sellerlabel.setPreferredSize(new Dimension(150, 40));
        
        landid= new JTextField();
        landid.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        landid.setPreferredSize(new Dimension(200, 30));
        
        price = new JTextField();
        price.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        price.setPreferredSize(new Dimension(200, 30));
        
        buyerpan = new JTextField();
        buyerpan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        buyerpan.setPreferredSize(new Dimension(200, 30));
        
        sellerpan = new JTextField();
        sellerpan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sellerpan.setPreferredSize(new Dimension(200, 30));
        
        share = new JTextField();
        share.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        share.setPreferredSize(new Dimension(200, 30));
        
        addbuyer = new JButton("Add");
        addbuyer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        addbuyer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        addseller = new JButton("Add");
        addseller.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        addseller.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        submit = new JButton("Submit");
        submit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        submit.setPreferredSize(new Dimension(150, 40));
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        sellertable = new JTable(new DefaultTableModel(new Object[][]{} , new String[]{"PAN","Name","Phone No.","Email ID"}));
        sellertable.setPreferredSize(new Dimension(400, 200));
        sellertable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        sellertable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(sellertable);
        sp1.setPreferredSize(new Dimension(400, 100));
        
        buyertable = new JTable(new DefaultTableModel(new Object[][]{} , new String[]{"PAN","Name","Share","Phone No.","Email ID"}));
        buyertable.setPreferredSize(new Dimension(400, 200));
        buyertable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        buyertable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        
        JScrollPane sp2 = new JScrollPane();
        sp2.setViewportView(buyertable);
        sp2.setPreferredSize(new Dimension(400, 100));
        
        //design middlepanel
        GridBagConstraints g;
   
        middlepanel.setLayout(new GridBagLayout());
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(landidlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;
        //g.insets = new Insets(5, 5, 5, 15);
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(landid ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(pricelabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        //g.insets = new Insets(5, 15, 5, 5); 
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(price ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 2;
        //g.insets = new Insets(30, 5, 5, 5);
        g.weighty = 1;
        g.weightx = 1;
        g.anchor = GridBagConstraints.SOUTH;
        middlepanel.add(sellerlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 1;
        g.gridwidth = 2;
        //g.insets = new Insets(30, 5, 5, 5);
        g.weighty = 1;
        g.weightx = 1;
        g.anchor = GridBagConstraints.SOUTH;
        middlepanel.add(buyerlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.gridheight = 2;
        g.weighty = 0.5;
        g.weightx =0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(sellerpanlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 2;
        g.gridheight = 2;
        g.weighty = 0.5;
        g.weightx =0.5;
        middlepanel.add(sellerpan ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 2;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(buyerpanlabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 2;
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(buyerpan ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 3;
        g.weighty = 0.5;
        g.weightx = 0.5;
        g.anchor = GridBagConstraints.EAST;
        middlepanel.add(sharelabel ,g);
        
        g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 3;
        g.weighty = 0.5;
        g.weightx = 0.5;
        middlepanel.add(share ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(addseller ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 4;
        g.gridwidth = 2;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(addbuyer ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weighty = 5;
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1;
        g.insets = new Insets(15, 15, 15, 15);
        middlepanel.add(sp1 ,g);
        
        g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weighty = 5;
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1;
        g.insets = new Insets(15, 15, 15, 15);
        middlepanel.add(sp2 ,g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 4;
        g.weighty = 0.2;
        g.weightx = 1;
        middlepanel.add(submit ,g);
        
        //bottom panel variables
        bottompanel = new JPanel();
        bottompanel.setBackground(new Color(52, 69, 150));
        bottompanel.setPreferredSize(new Dimension(0, 50));
        
        //set Frame layout and add components
        setLayout(new BorderLayout());
        add(toppanel, BorderLayout.PAGE_START);
        add(middlepanel, BorderLayout.CENTER);
        add(bottompanel, BorderLayout.PAGE_END);
    
        //mouse clicked listener for logout
        logout.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                logoutclicked();
            }
        });
        
        //actionlistener for addseller
        addseller.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent a) 
            {
                Server.connectToDB();
                
                String lid;
                lid = landid.getText();

                String span;
                span = sellerpan.getText();

                String str = Server.validateSeller(lid, span);
                
                if(str.equals("ERROR"))
                {
                    JOptionPane.showMessageDialog(null, "Incorrect PAN", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else
                {
                    String[] strlist = str.split(",");
                    
                    Vector <String> v = new Vector<String>();
                    int i=0;
                    while(i < strlist.length)
                    {
                        v.add(strlist[i]);
                        i++;
                    }

                    DefaultTableModel m = (DefaultTableModel) sellertable.getModel();
                    m.addRow(v);
                }
                
                sellerpan.setText("");
                
                Server.closeConnection();
            }
        });
        
        //actionlistener for addbuyer
        addbuyer.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String bpan , pershare;
                bpan = buyerpan.getText();
                pershare = share.getText();
                String lid = landid.getText();
                
                Server.connectToDB();

                boolean valid = Server.checkBuyer(bpan);
                if(valid)
                {
                    String str = Server.getPersonData(bpan);
                    String []strlist = str.split(",");
                    Vector <String> v = new Vector<String>();
                    int i=0;
                    while(i<strlist.length)
                    {
                        v.add(strlist[i]);
                        i++;
                    }
                    
                    v.insertElementAt(pershare, 2);
                    DefaultTableModel m = (DefaultTableModel) buyertable.getModel();
                    m.addRow(v);
                    //Transaction.makeTransaction(v, conn, landid);
                }
                
                else
                {
                    //TODO new panel for entering person details
                }
                
                buyerpan.setText("");
                share.setText("");
            }
        });
        
        //actionlistener for submit button
        submit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            }
        });
        
    }
    
    void logoutclicked()
    {
        f.setVisible(true);
        dispose();
    }
}
