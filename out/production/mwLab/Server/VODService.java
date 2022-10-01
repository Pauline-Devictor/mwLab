package server;

import interfaces.IClientBox;
import interfaces.IVODService;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    List<MovieDesc> catalog = new ArrayList<>();
    private static VODService instance = null;
    MovieDesc movie = new MovieDesc("Jesuisunfilm","14325426235324-2132","JESUISLEDETAILDUFILM",new Bill("Jesuisunfilm", new BigInteger(String.valueOf(11213141))));
    public VODService(int numport) throws RemoteException {
        super(numport);
        catalog.add(movie);
    }

    public static IVODService getInstance(int numport) throws RemoteException {
        if (instance == null) {
            instance = new VODService(numport);
        }
        return instance;
    }

    public List<MovieDesc> viewCatalog() {
        return catalog;
    }

    public Bill playmovie(String isbn, IClientBox box) { //TODO
        return movie.getBill(); //provisoire, pour tester
    }
}
