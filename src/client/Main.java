package client;

import server.IConnection;
import exceptionHelper.SignInFailed;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws  NotBoundException, RemoteException, SignInFailed {
        System.out.println("Hello World!");

        Registry reg = LocateRegistry.getRegistry("192.168.90.117", 2001);
        IConnection connection= (IConnection)reg.lookup("Connection");


        //IConnection connection = (IConnection) LocateRegistry.getRegistry("192.168.90.117", 2001);//(IConnection) Naming.lookup("rmi://localhost:2001/Connection");

        connection.signIn("Bob", "1234");
        System.out.println("After invoking echo");

        connection.signIn("Bob", "1234");
    }

}
