package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame implements ActionListener {

    Choice choiceEMP;
    JButton searchbt,print,update,back;
    JTable table;

    ViewEmployee(){
        getContentPane().setBackground(new Color(253,131,122));
        JLabel search=new JLabel("Search by employee id");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP=new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try{
            comm c=new comm();
            ResultSet resultset=c.statement.executeQuery("select * from employee");
            while(resultset.next()){
                choiceEMP.add(resultset.getString("empid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        table =new JTable();
        try{
            comm c=new comm();
            ResultSet resultSet=c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane jp= new JScrollPane(table);
        jp.setBounds(0,100,900,800);
        add(jp);
        searchbt =new JButton("Search");
        searchbt.setBounds(20,70,80,20);
        searchbt.addActionListener(this);
        add(searchbt);

        print =new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update =new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back =new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);


        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==searchbt){
            String query="select * from employee where empid='"+choiceEMP.getSelectedItem()+"'";
            try{
                comm c=new comm();
                ResultSet resultSet=c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource()==print){
            try{
                table.print();
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        else if(e.getSource()==update){
            setVisible(false);
            new UpdateEmplyee(choiceEMP.getSelectedItem());
        }
        else{
            setVisible(false);
           new Main_class();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
