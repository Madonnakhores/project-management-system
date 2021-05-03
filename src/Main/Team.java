/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Team {
    private int team_id;
    private String team_name;
    

    public Team(int team_id, String team_name) {
        this.team_id = team_id;
        this.team_name = team_name;
    }

    public Team(String team_name) {
        this.team_name = team_name;
    }
    

    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }
    public void insertIntoDB(){
        InsertRecords ir = new InsertRecords();
        ir.insertTeam(team_name);
    }
    public static ArrayList<Team> getTeamsList(){
        SelectRecords sr = new SelectRecords();
        return sr.retrieveTeamsList();
    }
    public static void displayTeamsList() {
           ArrayList<Team> teams= getTeamsList();
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + teams.get(i).team_name );
            System.out.println("__________________________________");
        }
    }
    
}
