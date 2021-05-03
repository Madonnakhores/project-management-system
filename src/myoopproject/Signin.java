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
public class Signin {
   
    
    public static void teamMember() throws NoSuchAlgorithmException{
        System.out.println("__________________________________");
        System.out.println("         TEAM MEMBER / TEAM LEADER SIGN IN        ");
        System.out.println("__________________________________");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = input.next();
        System.out.print("Enter your password: ");
        String trypassword = input.next();
        TeamMember tm = TeamMember.login(email, trypassword);
        if ( tm == null){
            System.out.println("Invalid email or password, please try again!");
        }
        
        else if(tm.getRole().equals("L")){
           TeamLeaderMenu teamLeaderMenu = new TeamLeaderMenu(tm );
        }
        else if(tm.getRole().equals("M")){
            TeamMemberMenu teamMemberMenu= new TeamMemberMenu(tm );
        }
    }
}
