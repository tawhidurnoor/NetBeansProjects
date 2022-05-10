/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.logworkclone;

import java.util.Arrays;

/**
 *
 * @author User
 */
public class LogWorkClone {

    public static void main(String[] args) {
        MainInterface.main(args);
//        ApiTest apiTest = new ApiTest();
//        apiTest.login();
        System.out.println(Arrays.toString(new ApiTest().login()));
        
        String[] strings = {"Noor", "Tawhidur", "Badhan"};
        //System.out.println(Arrays.toString(strings));
        //new ApiTest().login();
    }
}
