package server;

import exceptionHelper.InvalidIsbnException;
import interfaces.IClientBox;
import interfaces.IVODService;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    List<MovieDesc> catalog = new ArrayList<>();
    private static VODService instance = null;
    MovieDesc movie = new MovieDesc("Jesuisunfilm","14325426235324-2132","JESUISLEDETAILDUFILM",new Bill("Jesuisunfilm", new BigInteger(String.valueOf(11213141))));
    MovieDesc movie2 = new MovieDesc("AAAAAAA","12-2132","ADETAILS",new Bill("AAAAAAA", new BigInteger(String.valueOf(11213141))));
    MovieDesc movie3 = new MovieDescExtended("Test","12-2133","synopsis",new Bill("Test", new BigInteger(String.valueOf(1234567))));

    public VODService(int numport) throws RemoteException {
        super(numport);
        catalog.add(movie);
        catalog.add(movie2);
        catalog.add(movie3);
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

    synchronized public Bill playmovie(String isbn, IClientBox box) throws InvalidIsbnException, RemoteException {
        for (MovieDesc m: catalog) {
            if(m.getIbsn().equals(isbn)) {
                byte[] chunk = {1,2,3,4,5,'a', 'b', 'c', 'd', 'e',1, 'a', 2, 'b', 3}; //provisoire, pour tester
                for(int i =0;i<3;i++) {
                    int finalI = i;
                    new Thread(() -> {
                        try {
                            //Pour simuler le chargement du film
                            byte[] a = Arrays.copyOfRange(chunk, finalI *chunk.length/3, Math.min(chunk.length/3* (finalI +1), chunk.length));
                            box.stream(a);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
                return m.getBill();
            }
        }
        throw new InvalidIsbnException("Movie not found with isbn : "+isbn);
    }
}
