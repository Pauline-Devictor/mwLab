package client;

import exceptionHelper.InvalidCredentialsException;
import server.Connection;
import server.IConnection;
import exceptionHelper.SignInFailed;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws NotBoundException, RemoteException, SignInFailed, MalformedURLException, InvalidCredentialsException {
        System.out.println("Hello World!");

    //distance
       /* Registry reg = LocateRegistry.getRegistry("192.168.90.117", 6542);
        IConnection connection= (IConnection)reg.lookup("Connection");*/
    //local
        IConnection connection = (IConnection) Naming.lookup("rmi://localhost:2001/Connection");

        System.out.println("Bob will have a new account");

        boolean result = connection.signIn("Bob.gmail", "1234");
        System.out.println("Result of trying to signIn = " + result);

        System.out.println("Let's try to signIn again ! ");
        try{connection.signIn("Bob.gmail", "1234");}
        catch (Exception e){
            System.out.println("Ooooops Bob already have an account with his mail = 'Bob.gmail'");
        }

        System.out.println("Let's try to login ! ");
       // IVODService ivodService = (IVODService) connection.login("Bob.gmail", "1234"); //TODO Login qui marche :(

    }

}
