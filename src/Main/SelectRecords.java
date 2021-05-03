/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;


import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import myoopproject.Task.Status;

/**
 *
 * @author Dell
 */
public class SelectRecords {
    
      public Status statusEnum []= Status.values();
        
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

    public String retrieveString(String selectedColumnLabel, String tableName, String conditionColumnLabel, String searchKey){
        String sql = "SELECT ? FROM ? WHERE ? = ?";
        sql = sql.replaceFirst("\\?",selectedColumnLabel);
        sql = sql.replaceFirst("\\?",tableName);
        sql = sql.replaceFirst("\\?",conditionColumnLabel);
        String result = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,searchKey);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                result = rs.getString(selectedColumnLabel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public byte[] retrieveByte(String selectedColumnLabel, String tableName, String conditionColumnLabel, String searchKey){
        //SELECT hash FROM USERS where email = "email";
        String sql = "SELECT ? FROM ? WHERE ? = ?";
        sql = sql.replaceFirst("\\?",selectedColumnLabel);
        sql = sql.replaceFirst("\\?",tableName);
        sql = sql.replaceFirst("\\?",conditionColumnLabel);
        //+ selectedColumnLabel + " FROM " + tableName + " WHERE " + conditionColumnLabel + "=\""+ searchKey +"\"";
        byte[] result = new byte[16];
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,searchKey);
            ResultSet rs    = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                result = rs.getBytes(selectedColumnLabel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public int retrieveInteger(String selectedColumnLabel, String tableName, String conditionColumnLabel, String searchKey){
        String sql = "SELECT ? FROM ? WHERE ? = ?";
        sql = sql.replaceFirst("\\?",selectedColumnLabel);
        sql = sql.replaceFirst("\\?",tableName);
        sql = sql.replaceFirst("\\?",conditionColumnLabel);
        int result = -1;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,searchKey);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                result = rs.getInt(selectedColumnLabel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public ArrayList<Task> retrieveTeamMemberTasksList(int team_id , int team_member_id ){
        String sql = "SELECT * FROM TASK INNER JOIN TEAM_MEMBER_TASK ON TASK.task_id = TEAM_MEMBER_TASK.task_id WHERE TEAM_MEMBER_TASK.team_id=? AND TEAM_MEMBER_TASK.team_member_id=?";
        ArrayList<Task> tasks = new ArrayList<>();
        int task_id = -1;
        String task_name = "";
        String deadline = "";
        String status = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, team_id);
            pstmt.setInt(2, team_member_id);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                task_id = rs.getInt("task_id");
                task_name = rs.getString("task_name");
                deadline=rs.getString("deadline");
                status=rs.getString("status");
                
                    Task task = new Task (task_id, team_member_id ,team_id, task_name, deadline, status);  
                    tasks.add(task);
                    break;
                    
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
    
    public ArrayList<Task> retrieveTasksList(int team_id){
        String sql = "SELECT * FROM TASK  WHERE team_id=?";
        ArrayList<Task> tasks = new ArrayList<>();
        int task_id = -1;
        int team_member_id = -1;
        String task_name = "";
        String deadline = "";
        String status = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, team_id);
            ResultSet rs = pstmt.executeQuery();

           
            while (rs.next()) {
                task_id = rs.getInt("task_id");
                team_id=rs.getInt("team_id");
                task_name = rs.getString("task_name");
                deadline=rs.getString("deadline");
                status=rs.getString("status");
               
                Task task = new Task (task_id, team_member_id, team_id,task_name,deadline,status);
                tasks.add(task);
                 
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
     public TeamMember retrieveTeamMember(String email){
        String sql = "SELECT * FROM TEAM_MEMBER  WHERE email= ?";
        TeamMember tm= null;
        String name;
        String role;
        int team_member_id;
        int team_id;
        
       
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                name = rs.getString("name");
                team_member_id = rs.getInt("team_member_id");
                team_id=rs.getInt("team_id");
                role=rs.getString("role");
                
                tm = new TeamMember( name, role , team_member_id , team_id);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tm;
    }
    public ArrayList<Team> retrieveTeamsList(){
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "SELECT * From TEAM ";
        int team_id=-1;
        String team_name="";
        
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, team_id);
            pstmt.setString(2, team_name);
            ResultSet rs= pstmt.executeQuery();
            
            // loop through the result set
            while(rs.next()){
                team_id = rs.getInt("team_id");
                team_name=rs.getString(team_name);
                teams.add(new Team(team_id, team_name));
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return teams;
    }
    public ArrayList<TeamMember> retrieveTeamMembersList(int team_id){
        ArrayList<TeamMember> teamMembers = new ArrayList<>();
        String sql ="SELECT name, role , team_member_id , team_id From TEAM_MEMBER WHERE team_id=?";
        String name ="";
        String role = "";
        int team_member_id=-1;
        
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.setInt(3 , team_member_id);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                name= rs.getString(name);
                role=rs.getString(role);
                team_member_id=rs.getInt(team_member_id);
                teamMembers.add(new TeamMember(name, role , team_member_id));
            }
            
        }catch(SQLException  | NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        
        return teamMembers;
    }
  
}
