package server;

import exceptionHelper.InvalidIsbnException;
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

    public Bill playmovie(String isbn, IClientBox box) throws InvalidIsbnException {
        for (MovieDesc m: catalog) {
            if(m.getIbsn().equals(isbn)) {
                byte[] chunk = {1,2,3,4,5}; //provisoire, pour tester
                box.stream(chunk);
                return m.getBill();
            }
        }
        throw new InvalidIsbnException(isbn);
    }
}
