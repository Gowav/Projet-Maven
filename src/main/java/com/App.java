package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
        try {
        	SpringApplication.run(App.class, args);
        	System.out.println("Application démarée !");
        }catch(Exception e) {
        	System.out.println("Application erreur \n"+e);	
        }
    }
}
