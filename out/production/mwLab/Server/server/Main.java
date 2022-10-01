package server;

import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Main {
    public static IConnection connection;
    public static IVODService ivodService;
    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(2001);

        connection = Connection.getInstance(1001);
        System.out.println("Server ready");
        reg.rebind("Connection", connection);

        ivodService = VODService.getInstance(1002);
        reg.rebind("VODService", ivodService);

    }
}
