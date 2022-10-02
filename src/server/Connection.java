package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import exceptionHelper.*;
import interfaces.IConnection;
import interfaces.IVODService;


public class Connection extends UnicastRemoteObject implements IConnection {
    List<Client> clientlist = new ArrayList<>();
    private static Connection instance = null;

    protected Connection(int numport) throws RemoteException {
        super(numport);
    }

    public static IConnection getInstance(int numport) throws RemoteException {
        if (instance == null) {
            instance = new Connection(numport);
        }
        return instance;
    }

    public boolean signIn(String mail, String pwd) throws SignInFailed {
        if (mail == null || pwd == null) {
            throw new SignInFailed("Invalid mail or password");
        }
        for (Client client : clientlist) {
            if (client.getmail().equals(mail)) {
                throw new SignInFailed("Mail already in use");
            }
        }
        Client c = new Client(mail,pwd);
        clientlist.add(c);
        Main.csvManager.writeClientData(c);
        return true;
    }

    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException {
        if (mail == null || pwd == null) {
            throw new InvalidCredentialsException("Invalid mail or password");
        }
        for (Client client : clientlist){
            if (client.getmail().equals(mail) && client.getpwd().equals(pwd)) {
                return VODService.getInstance(1002);
            }
        }
        throw new InvalidCredentialsException("Invalid mail or password");
    }

    public void addClient(Client c) throws java.rmi.RemoteException {
        clientlist.add(c);
    }
}
