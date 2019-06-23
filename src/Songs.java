import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Songs extends JPanel {


    public Songs (){
        this.setSize(500 , 500);
        this.setLayout(new BorderLayout());
        //this.setTitle("Songs");
        String data[][]={ {"Ghorse ghamar","bani","ghorse ghamar" , "3:54"},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"102","Jai","780000" , ""},
                {"101","Sachin","700000" , ""}};
        String column[]={"Track","Singer","Album","Time"};
        final JTable jt=new JTable(data,column);
        jt.setBackground(new Color(0xBBBBBB));
        jt.setRowSelectionAllowed(true);
        ListSelectionModel select= jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int row = jt.getSelectedRow();
                int[] columns = jt.getSelectedColumns();
                    for (int j = 0; j < columns.length; j++) {
                        Data = (String) jt.getValueAt(row, 0);
                     }
                System.out.println("Table element selected is: " + Data);
            }
        });
        jt.setRowSelectionInterval(0, 0);
        JScrollPane sp=new JScrollPane(jt);
        JPanel table = new JPanel();
        table.setBackground(new Color(0xA2A2A2));
        table.setLayout(new BorderLayout());
        table.add(sp , BorderLayout.CENTER);
        JPanel selection = new JPanel();
        selection.setLayout(new BorderLayout());
        JButton selecChoioce = new JButton("Select");

        selecChoioce.setBackground(new Color(0));
        selecChoioce.setForeground(new Color(0xFFFFFF));
        selection.add(selecChoioce , BorderLayout.CENTER);
        this.add(table ,BorderLayout.NORTH);
        this.add(selection , BorderLayout.SOUTH);
        this.setVisible(false);
        this.setLocation(300 ,60);
        //+this.setDefaultCloseOperation(JpotifyFrame.HIDE_ON_CLOSE);
    }

}
