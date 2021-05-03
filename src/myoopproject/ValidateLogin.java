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
public class ValidateLogin {
        public static boolean user(String email, String tryPassword) {
        SelectRecords sr = new SelectRecords();
        String hash = sr.retrieveString("hash", "team_member", "email", email);
        byte[] salt = sr.retrieveByte("salt", "team_member", "email", email);

        if (PasswordHashing.validatePassword(hash, salt, tryPassword)) {
            return true;
        }
        return false;
    }
}
