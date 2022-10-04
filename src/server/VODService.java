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
    public VODService(int numport) throws RemoteException {
        super(numport);
    }

    public void initMovie(MovieDesc movie) throws RemoteException {
        catalog.add(movie);
    }

    public void addMovie(MovieDesc movie) throws RemoteException {
        Main.csvManager.writeMovieData(movie);
        catalog.add(movie);
    }

    public static IVODService getInstance(int numport) throws RemoteException {
        if (instance == null) {
            instance = new VODService(numport);
        }
        return instance;
    }

    public List<MovieDesc> viewCatalog(IClientBox box) throws RemoteException {
        for (MovieDesc movie: catalog) {
            System.out.println(movie.toString(box));
        }
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

    @Override
    public boolean addmovie(String name, String isbn, String synopsis, String price) throws RemoteException {
        MovieDesc newMovie = new MovieDesc(name, isbn, synopsis, new Bill(name, new BigInteger(price)));
        addMovie(newMovie);
        return true;
    }
}
