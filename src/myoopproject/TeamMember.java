/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
/**
 *
 * @author Dell
 */
public class TeamMember {

   
    private int team_member_id;
    private int team_id;
    private String email;
    private String name;
    private String hash= "";
    private String role;
    private byte[] salt;
    private Team team;
    
    

    public TeamMember() {
    }

    public TeamMember(String email, String name, String hash, String role, Team team) throws NoSuchAlgorithmException {
        this.email = email;
        this.name = name;
        this.salt=PasswordHashing.generateSalt();
        this.hash=PasswordHashing.generateHash(hash, salt);
        this.role=role;
        this.team=team;
        
      
    }
      

    public TeamMember( String name, String role , int team_member_id ) throws NoSuchAlgorithmException {
        this.name = name;
        this.role=role;
        this.team_member_id = team_member_id;
       
    }

    public TeamMember(String name , String role,int team_member_id, int team_id) {
        this.team_member_id = team_member_id;
        this.team_id = team_id;
        this.name = name;
        this.role = role;
    }
    
    

    public int getTeam_id() {
        return team_id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
    
    
    public void insertIntoDB(Team team){
        team_id = team.getTeam_id();
        InsertRecords ir= new InsertRecords();
        ir.insertTeamMember(email, name, hash, salt,role,team_id);
      
    }

    public int getTeam_member_id() {
        return team_member_id;
    }
    
    public static TeamMember login(String email, String trypassword){
        SelectRecords sr = new SelectRecords();
        if(ValidateLogin.user(email,trypassword)){
            return sr.retrieveTeamMember(email);
        }
        return null;
    }
    
    public static ArrayList<TeamMember> getTeamMembersList(int team_id){
        SelectRecords sr = new SelectRecords();
        return sr.retrieveTeamMembersList(team_id);
    }
    
    public static void displayTeamMembersList(int team_id){
          ArrayList<TeamMember> teamMembers = getTeamMembersList(team_id);
        for (int i = 0; i < teamMembers.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + teamMembers.get(i).name );
            System.out.println("__________________________________");
        }
    }

}
