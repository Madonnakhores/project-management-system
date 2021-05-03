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
public class WelcomeMenu {
    public static void welcome() throws NoSuchAlgorithmException{
        Scanner input = new Scanner(System.in);
        
        System.out.println(" What would you like to do?");
        System.out.println(" [1] SignUP ");
        System.out.println(" [2] SignIn ");
        System.out.println(" [3] Exit");
        System.out.println("_____________");
        System.out.println(" Eenter your choice please ");
        int userChoice= input.nextInt();
        switch(userChoice){
            case 1:
                Signup.teamMember();
                break;
            case 2:
                Signin.teamMember();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice , please try again!");
                break;
        }
    }
}
