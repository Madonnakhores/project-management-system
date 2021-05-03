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

/**
 *
 * @author Madonna
 * 
 */
public class DeleteRecords {
    private Connection connect() {
      
        String url = "jdbc:sqlite:mydb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }  
    
    public void deleteTask(int task_id){
        String sql = "DELETE FROM TASK WHERE task_id =?";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, task_id);
            pstmt.executeUpdate();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    public void deleteTaskFromTeamMember(int task_id, int team_member_id, int team_id){
        String sql= "DELETE FROM TEAM_MEMBER_TASK WHERE team_id=? AND task_id=? AND team_member_id=?";
        
        try{
            Connection conn= this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,team_id);
            pstmt.setInt(2,task_id);
            pstmt.setInt(3,team_member_id);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
