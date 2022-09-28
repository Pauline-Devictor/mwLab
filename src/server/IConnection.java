package server;
import exceptionHelper.*;
public interface IConnection {
    public boolean signIn(String mail, String pwd) throws java.rmi.RemoteException,SignInFailed;
    public IVODService login(String mail, String pwd) throws java.rmi.RemoteException, InvalidCredentialsException;
}
