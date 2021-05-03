/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class TeamMemberMenu {
    protected TeamMember teamMember;
    
    Scanner input = new Scanner(System.in);

    public TeamMemberMenu(TeamMember teamMember ) {
        this.teamMember = teamMember;
        initial();
    }
    public void initial(){
         System.out.println("__________________________________");
         System.out.println("Welcome to main menu, " + teamMember.getName() + "!");
         System.out.println("__________________________________");
         System.out.println("What would you like to do?");
         System.out.println("[1] List all the tasks");
         System.out.println("[2] List your own tasks");
         System.out.println("[3] Edit the tasks assigned to you");
         System.out.println("[4] Log out");
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
                break;
            default:
                System.out.println("Invalid choice, please try again!");
                initial();
                break;
        }
    }
    public void viewTasks(){
        System.out.println("\n__________________________________");
        System.out.println("          ALL TEAM TASKS         ");
        System.out.println("__________________________________");

        ArrayList<Task> tasks = Task.getTasksList(teamMember);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).getTask_name()+" "+tasks.get(i).getStatus()+" "+tasks.get(i).getDeadline());
        }
    }
    public void viewYourTasks(){
        System.out.println("\n__________________________________");
        System.out.println("          YOUR TASKS         ");
        System.out.println("__________________________________");

        ArrayList<Task> tasks = Task.getTeamMemberTaskList(teamMember);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).getTask_name()+" "+tasks.get(i).getStatus()+" "+tasks.get(i).getDeadline());
        }
        
    }
    public void editTask(){
        Task.displayTeamMemberTaskList(teamMember);
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.print("Choose task ID: ");
        int userChoice = input.nextInt();
        Task task = Task.getTeamMemberTaskList(teamMember).get(userChoice - 1);

        Scanner nameInput = new Scanner(System.in);
        System.out.println("Leave fields empty if you want them unchanged.");
        System.out.print("Enter new task name: ");
        String taskName = nameInput.nextLine();
        if (!taskName.isEmpty()) {
            task.setTask_name(taskName);
        }

        System.out.print("Enter new task deadline:" );
        String taskDeadline = input.next();
        if (!taskDeadline.isEmpty()) {
            task.setDeadline(taskDeadline);
        }

        Scanner statusInput = new Scanner(System.in);
        System.out.print("Enter new task status: ");
        String taskStatus = statusInput.next();
        if (!taskStatus.isEmpty()) {
            task.setStatus(taskStatus);
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
    
}
