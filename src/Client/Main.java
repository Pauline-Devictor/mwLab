package Client;

import Server.Connection;
import Server.IConnection;
import Server.SignInFailed;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, SignInFailed {
        System.out.println("Hello World!");
        IConnection connection = (IConnection) Naming.lookup("rmi://localhost:2001/Connection");
        //s'affiche côté server car n'existe pas dans le Client
        connection.signIn("Bob", "1234");
        System.out.println("After invoking echo");

        connection.signIn("Bob", "1234");
    }

}
