package client;


import exceptionHelper.InvalidCredentialsException;
import exceptionHelper.SignInFailed;

public interface IConnection extends java.rmi.Remote {
     boolean signIn(String mail, String pwd) throws java.rmi.RemoteException, SignInFailed;
     IVODService login(String mail, String pwd) throws java.rmi.RemoteException, InvalidCredentialsException;
}
