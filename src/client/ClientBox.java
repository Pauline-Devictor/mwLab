package client;

import interfaces.IClientBox;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox, Serializable {

    private static ClientBox instance = null;
    protected ClientBox() throws RemoteException {
    }

    protected ClientBox(int port) throws RemoteException {
        super(port);
    }

    public static IClientBox getInstance(int numport) throws RemoteException {
        if (instance == null) {
            instance = new ClientBox(numport);
        }
        return instance;
    }
    @Override
    public void stream(byte[] chunk) {
        for (byte b: chunk) {
            System.out.print(b);
        }
        System.out.println();
    }

    /**
     * Print Infos from the server
     * @param details
     * @throws java.rmi.RemoteException
     */
    @Override
    public void printInfos(String details) throws java.rmi.RemoteException{
        System.out.println(details);
    };

}
