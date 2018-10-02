/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LandRecords;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 *
 * @author akash
 */
public class Guest extends JFrame 
{
    JFrame l;
    
    //top panel variables
    JPanel toppanel;
    JLabel username;
    JLabel logout;
    
    //middle panel variables
    JPanel middlepanel;
    JPanel choicepanel;
    
    JLabel landidlabel;
    JLabel statelabel;
    JLabel citylabel;
    JLabel costlabel;
    JLabel arealabel;
    JLabel typelabel;
    JLabel areato;
    JLabel costto;
    
    JTextField landid;
    JComboBox<String> state;
    JComboBox<String> city;
    JComboBox<String> type;
    JTextField areamin;
    JTextField areamax;
    JTextField costmin;
    JTextField costmax;
    
    JButton search;
    
    //bottom panel variables
    JPanel bottompanel;
    
    public Guest()
    {
        instantiate();
    }
    
    public Guest(JFrame l)
    {
        instantiate();
        this.l = l;
        
        city.setEnabled(false);
        //add states
        try
        {
            FileReader fr = new FileReader("Statelist.txt");
            BufferedReader br = new BufferedReader(fr);

            String statestr;
            statestr = br.readLine();

            do
            {
                String [] statelist;
                statelist = statestr.split(",");
                state.addItem(statelist[0]+"."+statelist[1]);
                statestr = br.readLine();
            }while(statestr!=null);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    void instantiate()
    {
        //top panel variables
        toppanel = new JPanel();
        username = new JLabel("Logged in as Guest");
        logout = new JLabel("Log Out");
        toppanel.setBackground(new Color(52, 69, 150));
        toppanel.setPreferredSize(new Dimension(0, 100));
        
        //design top panel
        toppanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 40));
        
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        username.setForeground(Color.WHITE);
        
        logout.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        logout.setForeground(Color.WHITE);
        
        toppanel.add(username);
        toppanel.add(logout);
        
        //middle panel variables
        middlepanel = new JPanel();
        
        landidlabel = new JLabel("Land ID");
        landidlabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        landidlabel.setForeground(Color.WHITE);
        landid = new JTextField();
        landid.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        landid.setPreferredSize(new Dimension(200, 30));
        
        statelabel = new JLabel("State");
        statelabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        statelabel.setForeground(Color.WHITE);
        String []model ={""};
        state = new JComboBox<String>(model);
        state.setPreferredSize(new Dimension(200, 30));
        state.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        
        citylabel = new JLabel("City");
        citylabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        citylabel.setForeground(Color.WHITE);
        city = new JComboBox<String>(model);
        city.setPreferredSize(new Dimension(200, 30));
        city.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        
        arealabel = new JLabel("Area");
        arealabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        arealabel.setForeground(Color.WHITE);
        areato = new JLabel(" to ");
        areato.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        areato.setForeground(Color.WHITE);
        areamin = new JTextField();
        areamax = new JTextField();
        areamin.setPreferredSize(new Dimension(80, 30));
        areamax.setPreferredSize(new Dimension(80, 30));
        areamin.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        areamax.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        
        costlabel = new JLabel("Cost");
        costlabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        costlabel.setForeground(Color.WHITE);
        costto = new JLabel(" to ");
        costto.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        costto.setForeground(Color.WHITE);
        costmin = new JTextField();
        costmax = new JTextField();
        costmin.setPreferredSize(new Dimension(80, 30));
        costmax.setPreferredSize(new Dimension(80, 30));
        costmin.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        costmax.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
               
        typelabel = new JLabel("Type");
        typelabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 16));
        typelabel.setForeground(Color.WHITE);
        
        String []model2 = {"","Forest", "Agricultural","Residential","Industrial"};
        type = new JComboBox<String>(model2);
        type.setPreferredSize(new Dimension(200, 30));
        type.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        
        search = new JButton("Search");
        search.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        
        //design choice panel
        choicepanel = new JPanel();
        choicepanel.setPreferredSize(new Dimension(350, 0));
        choicepanel.setBackground(new Color(43, 122, 223));
        choicepanel.setLayout(new GridBagLayout());
        
        GridBagConstraints g;
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.weighty = 1;
        choicepanel.add(landidlabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 3;
        g.weighty = 1;
        choicepanel.add(landid , g);
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.weighty = 1;
        choicepanel.add(statelabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 3;
        g.weighty = 1;
        choicepanel.add(state , g);
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.weighty = 1;
        choicepanel.add(citylabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 3;
        g.weighty = 1;
        choicepanel.add(city , g);
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 3;
        g.weighty = 1;
        choicepanel.add(typelabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 3;
        g.weighty = 1;
        choicepanel.add(type , g);
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.weighty = 1;
        choicepanel.add(arealabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 4;
        g.weighty = 1;
        choicepanel.add(areamin , g);
        
        g= new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 4;
        g.weighty = 1;
        choicepanel.add(areato , g);
        
        g= new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 4;
        g.weighty = 1;
        choicepanel.add(areamax , g);
        
        g= new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.weighty = 1;
        choicepanel.add(costlabel , g);
        
        g= new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 5;
        g.weighty = 1;
        choicepanel.add(costmin , g);
        
        g= new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 5;
        g.weighty = 1;
        choicepanel.add(costto , g);
        
        g= new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 5;
        g.weighty = 1;
        choicepanel.add(costmax , g);
        
        g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.weighty = 1;
        g.gridwidth = 4;
        choicepanel.add(search , g);
        //design middle panel and add components
        middlepanel.setLayout(new BorderLayout());
        middlepanel.add(choicepanel , BorderLayout.LINE_START);
        
        //bottom panel variables
        bottompanel = new JPanel();
        bottompanel.setBackground(new Color(52, 69, 150));
        bottompanel.setPreferredSize(new Dimension(0, 50));
        
        //set Frame layout and add components
        setLayout(new BorderLayout());
        add(toppanel, BorderLayout.PAGE_START);
        add(middlepanel, BorderLayout.CENTER);
        add(bottompanel, BorderLayout.PAGE_END);
    
        //logout mouse clicked listener
        logout.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me)
            {
                logoutclicked();
            }
        });
        
        //state action listener for adding city
        state.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent a) 
            {
                city.setEnabled(true);
                city.removeAllItems();
                city.addItem("");
                String statename = (String) state.getSelectedItem();
                String code = statename.substring(0,2);
                try
                {
                    FileReader fr1 = new FileReader("Citylist.txt");
                    BufferedReader br1 = new BufferedReader(fr1);
                    String str1;
                    str1 = br1.readLine();
                    while(str1!=null)
                    {
                        String []cityarr;
                        cityarr = str1.split(",");
                        if(cityarr[1].equals(code))
                        {
                            city.addItem(cityarr[0]);
                        }
                        str1 = br1.readLine();
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }                
            }
        });
     
    }

    void logoutclicked()
    {
        dispose();
        l.setVisible(true);
    }
}
