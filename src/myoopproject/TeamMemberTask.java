/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;

/**
 *
 * @author Dell
 */
public class TeamMemberTask {
    int team_id;
    int task_id;
    int team_member_id;
    public TeamMemberTask(){};
    public TeamMemberTask(int team_id, int task_id, int team_member_id) {
        this.team_id = team_id;
        this.task_id = task_id;
        this.team_member_id = team_member_id;
    }
    
    public void insertIntoDB(Task task){
        team_id= task.getTeam_id();
        task_id= task.getTask_id();
        team_member_id=task.getTeam_member_id();
        InsertRecords ir= new InsertRecords();
        ir.insertTaskToTeamMember(team_member_id, task_id,team_id);
    }
    public void deleteTeamMemberTask(Task task){
        DeleteRecords dr = new DeleteRecords();
        dr.deleteTaskFromTeamMember(task.getTask_id(), task.getTeam_member_id(), task.getTeam_id());
    }
  
}
