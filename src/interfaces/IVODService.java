package interfaces;

import exceptionHelper.InvalidIsbnException;
import server.Bill;
import server.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws java.rmi.RemoteException;
    Bill playmovie(String isbn, IClientBox box) throws java.rmi.RemoteException, InvalidIsbnException;
    boolean addmovie(String name, String isbn, String synopsis, String price) throws java.rmi.RemoteException;
    void addMovie(MovieDesc movieDesc) throws java.rmi.RemoteException;
    public void initMovie(MovieDesc movie) throws java.rmi.RemoteException;
    public void getMovieDetails(String isbn, IClientBox box) throws RemoteException, InvalidIsbnException;
}
