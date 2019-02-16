/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasksoft1;

import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 *
 * @author asd
 */
public class FXMLDocumentController implements Initializable {
    
  @FXML
    private Pane insert, update,delete,search,main;

    @FXML
    private JFXTextField FN_Insert,LN_insert,g_insert,g_update,LN_update,FN_update
           ,g_delete,LN_delete,FN_delete,g_search1
           ,LN_search1,FN_search1, FN_main_search1 ;
@FXML
    private void switch_main(ActionEvent event) {
        main.setVisible(true);
        insert.setVisible(false);
        search.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        }
 @FXML
    private void switch_insert(ActionEvent event) {
        main.setVisible(false);
        insert.setVisible(true);
        search.setVisible(false);
        delete.setVisible(false);
        update.setVisible(false);
        }
  @FXML
    private void switch_search(ActionEvent event) {
        main.setVisible(false);
        insert.setVisible(false);
        search.setVisible(true);
        delete.setVisible(false);
        update.setVisible(false);
        }
  @FXML
    private void switch_delete(ActionEvent event) {
        main.setVisible(false);
        insert.setVisible(false);
        search.setVisible(false);
        delete.setVisible(true);
        update.setVisible(false);
        }
  @FXML
    private void switch_update(ActionEvent event) {
        main.setVisible(false);
        insert.setVisible(false);
        search.setVisible(false);
        delete.setVisible(false);
        update.setVisible(true);
        }
    
    
    
       @FXML
    private void insert(ActionEvent event) {
       
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/exa","root","");
            Statement statement=connection.createStatement();
            statement.executeUpdate("INSERT INTO `movie` "
            + "( `FName`, `LName`, `grade`) "
            + "VALUES ('"+FN_Insert.getText()+"', '"+LN_insert.getText()+"', '"+g_insert.getText()+"' );");
            
            Notifications not =Notifications.create().
            title("Massege")
            .text("insert is succeeded")
            .graphic(null).hideAfter(Duration.seconds(15)).position(Pos.TOP_LEFT)
            .onAction((e) -> {
            System.out.println("Done");
            });
  
          not.showConfirm();
            
            connection.close();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
         }
    
           @FXML
         public void search(ActionEvent e){
    
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/exa","root","");
             Statement statement=connection.createStatement();
           ResultSet i = statement.executeQuery("SELECT title,description"
                   + ",genre FROM movie where id ='"+FN_main_search1.getText()+"'");
           if(!i.next()){
         JOptionPane.showMessageDialog(null, "The first name is not exist Please try agin");
        
         }
           else{
               i.previous();
           while (i.next()){
        FN_search1.setText(i.getString("title"));LN_search1.setText(i.getString("description"));
        g_search1.setText(i.getString("genre")); }}
            connection.close();
         } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }}
         
       public void update(ActionEvent e){
    
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/exa","root","");
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE movie SET title='"+FN_update.getText()
            +"',description='"+LN_update.getText()+"',genre='"+g_update.getText()
            +"' WHERE id=1");
          
            connection.close();
         } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }}
    
          public void delete(ActionEvent e){
    
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/exa","root","");
            Statement statement=connection.createStatement();
             statement.executeUpdate("DELETE FROM `movie` WHERE id= 16"); 
            connection.close();
         } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }}
       
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
