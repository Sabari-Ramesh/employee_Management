package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete,back;
    RemoveEmployee(){

        JLabel label=new JLabel("Employee Id");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Thahmo",Font.BOLD,15));
        add(label);

        choiceEMPID =new Choice();
        choiceEMPID.setBounds(200,50,150,30);
        add(choiceEMPID);
        try{
            comm c=new comm();
            ResultSet resultset=c.statement.executeQuery("select * from employee");
            while(resultset.next()){
                choiceEMPID.add(resultset.getString("empid"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        JLabel labelName=new JLabel("Name");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("Thahmo",Font.BOLD,15));
        add(labelName);

        JLabel textName=new JLabel();
        textName.setBounds(200,100,100,30);
        add(textName);

        JLabel labelphone=new JLabel("Phone");
        labelphone.setBounds(50,150,100,30);
        labelphone.setFont(new Font("Thahmo",Font.BOLD,15));
        add(labelphone);

        JLabel textphone=new JLabel();
        textphone.setBounds(200,150,100,30);
        add(textphone);

        JLabel labelemail=new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        labelemail.setFont(new Font("Thahmo",Font.BOLD,15));
        add(labelemail);

        JLabel textemail=new JLabel();
        textemail.setBounds(200,200,100,30);
        add(textemail);

        try{
            comm c=new comm();
            ResultSet resultset=c.statement.executeQuery("select * from employee where empid='"+choiceEMPID.getSelectedItem()+"'");
            while(resultset.next()){
                textName.setText(resultset.getString("nmae"));
                textphone.setText(resultset.getString("phone"));
                textemail.setText(resultset.getString("email"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    comm c=new comm();
                    ResultSet resultset=c.statement.executeQuery("select * from employee where empid='"+choiceEMPID.getSelectedItem()+"'");
                    while(resultset.next()) {
                        textName.setText(resultset.getString("nmae"));
                        textphone.setText(resultset.getString("phone"));
                        textemail.setText(resultset.getString("email"));
                    }
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        delete=new JButton("DELETE");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.BLUE);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back=new JButton("BACK");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon((ClassLoader.getSystemResource("icons/delete.png")));
        Image i2=i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img=new JLabel(i3);
        img.setBounds(700,80,200,200);
        add(img);




        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==delete){
            try{
                comm c=new comm();
                String query="delete from employee where empid ='"+choiceEMPID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee delete Sucessfully");
            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
