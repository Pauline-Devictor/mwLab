package interfaces;

import server.Bill;
import server.MovieDesc;

import java.rmi.Remote;
import java.util.List;

public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws java.rmi.RemoteException;
    Bill playmovie(String isbn, IClientBox box) throws java.rmi.RemoteException;
}