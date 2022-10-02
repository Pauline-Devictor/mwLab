package client;

import exceptionHelper.InvalidCredentialsException;
import exceptionHelper.InvalidIsbnException;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;
import exceptionHelper.SignInFailed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    public static IClientBox clientBox;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws NotBoundException, RemoteException, SignInFailed, MalformedURLException, InvalidCredentialsException, InvalidIsbnException {
        //distance
       /* Registry reg = LocateRegistry.getRegistry("192.168.90.117", 6542);
        IConnection connection= (IConnection)reg.lookup("Connection");*/
        //local
        IConnection connection = (IConnection) Naming.lookup("rmi://localhost:2001/Connection");

        System.out.println("Let's have a new account");

        boolean signedIn = false;
        String mail = null;
        String pwd = null;
        while (!signedIn){
            System.out.println("Please enter your mail");
            mail = scanner.nextLine();
            System.out.println("Please enter your password");
            pwd = scanner.nextLine();
            try {
                signedIn = connection.signIn(mail,pwd);
            } catch (SignInFailed | IOException signInFailed) {
                System.out.println(signInFailed.getMessage());
            }
        }

        System.out.println("Let's try to signIn again ! ");
        try{connection.signIn(mail, pwd);}
        catch (Exception e){
            System.out.println("Ooooops there's already an account for mail : '"+mail+"'");
        }

        System.out.println("Then ..... Let's try to login ! ");
        IVODService ivodService = null;
        do {
            try {
                System.out.println("Please enter your mail");
                mail = scanner.nextLine();
                System.out.println("Please enter your password");
                pwd = scanner.nextLine();
                ivodService = connection.login(mail, pwd);
            } catch (InvalidCredentialsException e) {
                System.out.println(e.getMessage());
            }
        } while (ivodService == null);


        IClientBox myBox = new ClientBox();
        Registry reg = LocateRegistry.createRegistry(2002);

        clientBox = ClientBox.getInstance(1003);
        reg.rebind("ClientBox", clientBox);


        System.out.println("Now let's see the catalog !");
        System.out.println(ivodService.viewCatalog());
        System.out.println("And now let's try to rent a movie !");
        System.out.println(ivodService.playmovie("14325426235324-2132", myBox));
        reg.unbind("ClientBox");
    }

}
