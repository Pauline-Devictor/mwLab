package client;

import exceptionHelper.InvalidCredentialsException;
import server.IConnection;
import exceptionHelper.SignInFailed;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws NotBoundException, RemoteException, SignInFailed, MalformedURLException, InvalidCredentialsException {
        System.out.println("Hello World!");

    //distance
       /* Registry reg = LocateRegistry.getRegistry("192.168.90.117", 6542);
        IConnection connection= (IConnection)reg.lookup("Connection");*/
    //local
        IConnection connection = (IConnection) Naming.lookup("rmi://localhost:2001/Connection");

        System.out.println("Bob will have a new account");

        boolean signedIn = false;
        String mail = null;
        String pwd = null;
        while (!signedIn){
            System.out.println("Please enter your mail");
            mail = scanner.nextLine();
            pwd = scanner.nextLine();
            try {
                signedIn = connection.signIn(mail,pwd);
            } catch (SignInFailed signInFailed) {
                System.out.println("Invalid mail or password");
            }
        }

        System.out.println("Let's try to signIn again ! ");
        try{connection.signIn(mail, pwd);}
        catch (Exception e){
            System.out.println("Ooooops there's already an account for mail : '"+mail+"'");
        }

        System.out.println("Then ..... Let's try to login ! ");
        IVODService ivodService = null;
        while (true){
            try{
                System.out.println("Please enter your mail");
                mail = scanner.nextLine();
                System.out.println("Please enter your password");
                pwd = scanner.nextLine();
                ivodService = (IVODService) connection.login(mail, pwd);
            }catch (Exception e){
                System.out.println("Invalid mail or password");
            }
            if (ivodService != null){
                break;
            }
        }
        ivodService.viewCatalog();


       // IVODService ivodService = (IVODService) connection.login(mail, pwd);

    }

}
