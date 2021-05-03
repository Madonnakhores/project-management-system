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
public class Task {

    private int task_id;
    private String task_name;
    private String deadline;
    private String status;
    private int team_member_id;
    private int team_id;

    public enum Status {
        TODO,
        ONGOING,
        DONE;
    }

    public Task(int task_id, int team_member_id, int team_id, String task_name, String deadline, String status) {
        this.task_id = task_id;
        this.team_member_id = team_member_id;
        this.task_name = task_name;
        this.deadline = deadline;
        this.status = status;
        this.team_id = team_id;
    }

    public Task(String task_name, String deadline, String status) {
        this.task_name = task_name;
        this.deadline = deadline;
        this.status = status;
    }

    public void insertIntoDB(TeamMember teamMember) {
        team_id = teamMember.getTeam_id();
        team_member_id = teamMember.getTeam_member_id();
        InsertRecords ir = new InsertRecords();
        ir.insertTask(team_member_id, team_id, task_name, deadline, status);

    }

    public void editTask() {
        UpdateRecords ur = new UpdateRecords();
        ur.editTask(task_name, deadline, status, task_id);
    }

    public void deleteTask() {
        DeleteRecords dr = new DeleteRecords();
        dr.deleteTask(task_id);
    }

    public static ArrayList<Task> getTeamMemberTaskList(TeamMember teamMember) {
        SelectRecords sr = new SelectRecords();
        return sr.retrieveTeamMemberTasksList(teamMember.getTeam_id(), teamMember.getTeam_member_id());
    }

    public static void displayTeamMemberTaskList(TeamMember teamMember) {
        ArrayList<Task> tasks = getTeamMemberTaskList(teamMember);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).task_name + "  " + tasks.get(i).deadline + " " + tasks.get(i).status);
            System.out.println("__________________________________");
        }
    }

    public static ArrayList<Task> getTasksList(TeamMember teamMember) {
        SelectRecords sr = new SelectRecords();
        return sr.retrieveTasksList(teamMember.getTeam_id());
    }

    public static void displayTasksList(TeamMember teamMember) {
        ArrayList<Task> tasks = getTasksList(teamMember);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).task_name + "  " + tasks.get(i).deadline + " " + tasks.get(i).status);
            System.out.println("__________________________________");

        }
    }

    public Task(int task_id, int team_member_id, int team_id) {
        this.task_id = task_id;
        this.team_member_id = team_member_id;
        this.team_id = team_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setStatus(String status) {

        this.status = status;

    }

    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public int getTeam_member_id() {
        return team_member_id;
    }

    public int getTeam_id() {
        return team_id;
    }

}
