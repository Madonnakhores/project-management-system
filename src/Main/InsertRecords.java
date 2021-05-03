/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import myoopproject.Task.Status;
/**
 *
 * @author Madonna
 */
public class InsertRecords {
   private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:mydb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }  
   
    public void insertTeamMember(String email, String name, String hash, byte[] salt, String role, int team_id) {
        //ir.insertTeamMember(email, name, hash_password,salt,role , team_id);
        String sql = "INSERT INTO TEAM_MEMBER (name, hash, email, salt,role, team_id) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2, hash);
            pstmt.setString(3, email);
            pstmt.setBytes(4, salt);
            pstmt.setString(5, role);
            pstmt.setInt(6, team_id );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertTask(int team_member_id ,int team_id, String task_name , String deadline,String status){
        String sql= "INSERT INTO TASK(deadline, status , task_name , team_member_id, team_id) VALUES (?,?,?,?,?)";
        
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deadline);
            pstmt.setString(2, status);
            pstmt.setString(3, task_name);
            pstmt.setInt(4, team_member_id);
            pstmt.setInt(5, team_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void insertTaskToTeamMember(int team_member_id, int task_id,int team_id){
       String sql= "INSERT INTO TEAM_MEMBER_TASK( team_member_id , task_id, team_id) VALUES(?,?,?)";
       
       try{
           Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, team_member_id);
            pstmt.setInt(2, task_id);
            pstmt.setInt(3, team_id);
            pstmt.executeUpdate();
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
   } 
   public void insertTeam( String name){
        String sql= "INSERT INTO TEAM ( team_name) VALUES(?)";
       
       try{
           Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
   }
   
}
