package mysqlinsertrecords;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class MysqlInsertRecords {
    public static Connection con;
    
    public static void main(String[] args) {
        MysqlInsertRecords pro = new MysqlInsertRecords();
        pro.createConnection();
        
        String name = JOptionPane.showInputDialog("Players name:", "");
        int id = Integer.parseInt(JOptionPane.showInputDialog(name+"'s player ID:", "0"));
        int age = Integer.parseInt(JOptionPane.showInputDialog(name+"'s age:", "0"));
        int match = Integer.parseInt(JOptionPane.showInputDialog("Number of matches "+ name+" has played:", "0"));
        
        InsertRecord(id, name, age, match);        
    }

    private static void InsertRecord(int id, String name, int age, int match) {
        try { 
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO player values ("+id+", '"+name+"', "+age+", "+match+")";
            stmt.executeUpdate(sql);
            
            System.out.println("Player is added");
            
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
    void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foosball", "root", "J3nnyCol3man!");
            System.out.println("Connection successful...");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(MysqlInsertRecords.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInsertRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
