package interfaces;
import exceptionHelper.*;
import server.Client;

import java.io.IOException;
import java.rmi.Remote;

public interface IConnection extends Remote {
     boolean signIn(String mail, String pwd) throws IOException, SignInFailed;
     IVODService login(String mail, String pwd) throws java.rmi.RemoteException, InvalidCredentialsException;
     void addClient(Client c) throws java.rmi.RemoteException;
}
