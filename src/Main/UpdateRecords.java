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
 * @author Dell
 */
public class UpdateRecords {
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
     
      public void editTask(String task_name, String deadline , String status, int task_id) {
     
        String sql = "UPDATE TASK SET  deadline = ?, status = ? , task_name = ? WHERE task_id = ?";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deadline);
            pstmt.setString(2, status);
            pstmt.setString(3, task_name);
            pstmt.setInt(4, task_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
}
