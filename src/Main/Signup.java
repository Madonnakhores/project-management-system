/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoopproject;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Signup {
  
   
    
    public static void teamMember() throws NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);
        Team team;
        String email, name, hash_password;
        System.out.println("__________________________________");
        System.out.println("       SIGN UP       ");
        System.out.print("Enter name: ");
        name = input.next();
        System.out.print("Enter email: ");
        email = input.next();
        System.out.print("Enter password: ");
        hash_password = input.next();
        System.out.println(" TEAM LEADER press 'L'  TEAM MEMBER press 'M' ");
        String role =input.next().toUpperCase();
        if(!(role.equals("L")||role.equals("M"))){
          System.out.println("Invalid entry ,Try agin!");
        }
        else if(role.equals("L")){
          System.out.println("Enter team name: ");
          String teamName = input.next();
          team = new Team(teamName);
          team.insertIntoDB();
          
          TeamMember tm = new TeamMember(email, name, hash_password ,role,team);
          tm.insertIntoDB(team);
        }
        else{
           System.out.println("Enter team name: ");
           String teamName = input.next();
           team = new Team(teamName);
           TeamMember tm = new TeamMember(email, name, hash_password ,role,team); 
           tm.insertIntoDB(team);
        }

        System.out.println("User created successfully!");
        //DISPLAY teamMember MENU
        WelcomeMenu.welcome();
    }
}
