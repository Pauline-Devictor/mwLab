package server;
import client.IVODService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Main {
    public static IConnection connection;
    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(2001);

        connection = Connection.getInstance(1001);
        System.out.println("Server ready");
        reg.rebind("Connection", connection);


    }
}
