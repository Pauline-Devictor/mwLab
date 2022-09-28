package Client;

public interface IVODService extends java.rmi.Remote {
    public void playmovie(String isbn, IClientBox box) throws java.rmi.RemoteException;
}

