package server;
import exceptionHelper.*;

import java.rmi.Remote;

public interface IConnection extends Remote {
     boolean signIn(String mail, String pwd) throws java.rmi.RemoteException,SignInFailed;
     IVODService login(String mail, String pwd) throws java.rmi.RemoteException, InvalidCredentialsException;
}
