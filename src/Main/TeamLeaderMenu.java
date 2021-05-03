/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;


import java.util.Scanner;
import myoopproject.Task.Status;

/**
 *
 * @author Dell
 */
public class TeamLeaderMenu extends TeamMemberMenu{
    private final Status statusEnum []= Status.values();
    public TeamLeaderMenu(TeamMember teamMember) {
        super(teamMember);
        
    }
    @Override
   public void initial(){
        System.out.println("__________________________________");
        System.out.println("Welcome to main menu, " + teamMember.getName()+ "!");
        System.out.println("__________________________________");
        System.out.println("What would you like to do?");
        System.out.println("[1] List all the tasks");
        System.out.println("[2] list your own tasks");
        System.out.println("[3] edit  tasks assigned to anyone");
        System.out.println("[4] add task");
        System.out.println("[5] delete task");
        System.out.println("[6] assign task to anyone");
        System.out.println("[7] unassign task to anyone ");
        System.out.println("[8] log out ");
        System.out.println("__________________________________");
        System.out.print("Your choice: ");
        int userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                viewTasks();
                break;
            case 2:
                viewYourTasks();
                break;
            case 3:
                editTask();
                break;
            case 4:
                addTask();
                break;
            case 5:
                deleteTask();
                break;
            case 6:
                assignTask();
                break;
            case 7:
                unassignTask();
                break;
            case 8:
                break;
            default:
                System.out.println("Invalid choice, please try again!");
                initial();
                break;
        }
   }
  public void addTask(){
       Task task;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String task_name = input.nextLine();

        System.out.print("Enter task deadline: ");
        String deadline = input.nextLine();
       
        System.out.print("Enter task status: ");
        String status = input.nextLine();
        
          task = new Task(task_name, deadline,status);
          
        System.out.println("__________________________________");
        System.out.println("Are you sure you want to add " + task.getTask_name() + " ?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
        System.out.print("Your choice: ");
        int userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                task.insertIntoDB(teamMember);
                System.out.println("Task added successfully!\n\n");
            case 2:
                initial();
                break;
          
          }
       
      
}
   public void deleteTask(){
         Task.displayTasksList(teamMember);
         Scanner input = new Scanner(System.in);
         System.out.print("Choose task ID: ");
         int userChoice = input.nextInt();
         Task task = Task.getTasksList(teamMember).get(userChoice - 1);

         System.out.println("__________________________________");
         System.out.println("Are you sure you want to remove " + task.getTask_name() + " ?");
         System.out.println("[1] YES");
         System.out.println("[2] NO");
         System.out.print("Your choice: ");
         userChoice = input.nextInt();
         switch (userChoice) {
            case 1:
                TeamMemberTask tmt = new TeamMemberTask();
                tmt.deleteTeamMemberTask(task);
                task.deleteTask();
            case 2:
                initial();
                break;
        }
   } 
    @Override
   public void editTask(){
        Task.displayTasksList(teamMember);
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.print("Choose task ID: ");
        int userChoice = input.nextInt();
        Task task = Task.getTasksList(teamMember).get(userChoice - 1);

        Scanner nameInput = new Scanner(System.in);
        System.out.println("Leave fields empty if you want them unchanged.");
        System.out.print("Enter new task name: ");
        String taskName = nameInput.nextLine();
        if (!taskName.isEmpty()) {
            task.setTask_name(taskName);
        }

        System.out.print("Enter new task deadline:" );
        String taskDeadline = input.nextLine();
        if (!taskDeadline.isEmpty()) {
            task.setDeadline(taskDeadline);
        }

        Scanner statusInput = new Scanner(System.in);
        System.out.print("Enter new task status: ");
        String taskStatus = statusInput.nextLine();
        if (!taskStatus.isEmpty()) {
            task.setStatus(taskStatus.toUpperCase());
        }

        System.out.println("__________________________________");
        System.out.println("Are you sure you want to update " + task.getTask_name() + " ?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
        System.out.print("Your choice: ");
        userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                task.editTask();
            case 2:
                initial();
                break;
        }
   }
   public void assignTask(){
       Scanner input = new Scanner(System.in);
       TeamMember.displayTeamMembersList(teamMember.getTeam_id());
       System.out.print("Enter team member id: ");
       int team_member_id= input.nextInt();
 
               
       Task.displayTasksList(teamMember);
       System.out.print("Enter task id: ");
       int task_id=input.nextInt();
      
       
       Team.displayTeamsList();
       System.out.print(" Enter team id");
       int team_id = input.nextInt();
       
       Task task = new Task(task_id-1, team_id-1 , team_member_id-1);
       TeamMemberTask tmt= new TeamMemberTask();
       tmt.insertIntoDB(task);
       System.out.println("task is assigned successfully");
       initial();
   }
   
   public void unassignTask(){
       Scanner input = new Scanner(System.in);
       TeamMember.displayTeamMembersList(teamMember.getTeam_id());
       System.out.print("Enter team member id: ");
       int team_member_id= input.nextInt();
       
       Task.displayTasksList(teamMember);
       System.out.print("Enter task id: ");
       int task_id=input.nextInt();
       
       Team.displayTeamsList();
       System.out.print(" Enter team id");
       int team_id = input.nextInt();
       
       Task task = new Task(task_id, team_id , team_member_id);
       TeamMemberTask tmt= new TeamMemberTask();
       tmt.deleteTeamMemberTask(task);
       System.out.println("task is unassigned successfully");
       initial();
   }
}
