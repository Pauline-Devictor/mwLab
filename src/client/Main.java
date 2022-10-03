package client;

import exceptionHelper.InvalidCredentialsException;
import exceptionHelper.InvalidIsbnException;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;
import exceptionHelper.SignInFailed;
import server.Connection;

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

        //System.out.println("Let's have a new account");

        IVODService ivodService = connection(connection);

        IClientBox myBox = new ClientBox();
        Registry reg = LocateRegistry.createRegistry(2002);

        clientBox = ClientBox.getInstance(1003);
        reg.rebind("ClientBox", clientBox);
        String choice = null;
        while(choice != "0"){
            System.out.println("Do you want to see the catalog (1), to rent a movie (2), or to quit (0) ?");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Now let's see the catalog !");
                System.out.println(ivodService.viewCatalog());
            } else if (choice.equals("2")) {
                System.out.println("And now let's try to rent a movie ! Enter the ISBN of the movie :");
                String isbn = scanner.nextLine();
                //String isbn = "14325426235324-2132";
                System.out.println(ivodService.playmovie(isbn, myBox));
            }
        }

        reg.unbind("ClientBox");
    }

    private static IVODService connection(IConnection connection) {
        System.out.println("Welcome ! Do you want to create a account (1) or to login (2) ? ");
        while(true) {
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                signin(connection);
            } else if (choice.equals("2")) {
                return login(connection);
            }
        }
    }

    private static void signin(IConnection connection){
        boolean signedIn = false;
        while (!signedIn){
            System.out.println("Please enter your mail");
            String mail = scanner.nextLine();
            System.out.println("Please enter your password");
            String pwd = scanner.nextLine();
            try {
                signedIn = connection.signIn(mail,pwd);
            } catch (SignInFailed | IOException signInFailed) {
                System.out.println(signInFailed.getMessage());
            }
        }
        connection(connection);
    }

    private static IVODService login(IConnection connection){
        IVODService ivodService = null;
        do {
            try {
                System.out.println("Please enter your mail");
                String mail = scanner.nextLine();
                System.out.println("Please enter your password");
                String pwd = scanner.nextLine();
                ivodService = connection.login(mail, pwd);
            } catch (InvalidCredentialsException | RemoteException e) {
                System.out.println(e.getMessage());
            }
        } while (ivodService == null);
        return ivodService;
    }

}
