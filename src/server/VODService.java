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
import java.util.Random;

public class VODService extends UnicastRemoteObject implements IVODService {

    Random r = new Random(); //Serve to decide whether Client can access to Teaser or not
    List<MovieDesc> catalog = new ArrayList<>();
    private static VODService instance = null;
    public VODService(int numport) throws RemoteException {
        super(numport);
    }

    public void initMovie(MovieDesc movie) throws RemoteException {
        catalog.add(movie);
    }

    public static IVODService getInstance(int numport) throws RemoteException {
        if (instance == null) {
            instance = new VODService(numport);
        }
        return instance;
    }

    public List<MovieDesc> viewCatalog() throws RemoteException {
        return catalog;
    }

    synchronized public Bill playmovie(String isbn, IClientBox box) throws InvalidIsbnException, RemoteException {
        for (MovieDesc m: catalog) { //We search the movie with the given isbn
            if(m.getIsbn().equals(isbn)) {
                byte[] chunk = {1,2,3,4,5,'a', 'b', 'c', 'd', 'e',1, 'a', 2, 'b', 3}; //Simulation of the movie datas
                for(int i =0;i<3;i++) { //we choose to send the datas in 3 times for the beginning, middle and end of the movie
                    int finalI = i;
                    new Thread(() -> {
                        try {
                            //Simulate movie loading
                            byte[] a = Arrays.copyOfRange(chunk, finalI *chunk.length/3, Math.min(chunk.length/3* (finalI +1), chunk.length));
                            box.stream(a);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
                return m.getBill(); //we return the Bill
            }
        }
        throw new InvalidIsbnException("Movie not found with isbn : "+isbn);
    }

    //Serve if the client want to add a movieDesc
    @Override
    public boolean addmovie(String name, String isbn, String synopsis, String price) throws RemoteException, InvalidIsbnException {
        MovieDesc movie = new MovieDesc(name, isbn, synopsis, new Bill(name, new BigInteger(price)));
        Main.csvManager.writeMovieData(movie);
        catalog.add(movie);
        return true;
    }

    //Serve if the client want to add a movieDescExtended
    @Override
    public boolean addmovie(String name, String isbn, String synopsis, String price, String teaser) throws RemoteException, InvalidIsbnException {
        MovieDesc movie = new MovieDescExtended(name, isbn, synopsis, price, teaser);
        Main.csvManager.writeMovieData(movie);
        catalog.add(movie);
        return true;
    }

    //Method use to show the movie details to the client
    @Override
    public void getMovieDetails(String isbn, IClientBox box) throws RemoteException, InvalidIsbnException {
        for (MovieDesc m: catalog) { // First we look for the right movie
            if(m.getIsbn().equals(isbn)) {
                box.printInfos(m.toStringDetails()); //Print movieName, Synopsis and isbn
                if (m instanceof MovieDescExtended) { //If m is a MovieDescExtended
                    int launchTeaser = r.nextInt(2);//Has 50% chance to watch the teaser
                    if(launchTeaser == 1) {
                        box.printInfos("You're lucky today : let's see the teaser");
                        box.stream(((MovieDescExtended) m).teaser);
                    }
                    else {
                        box.printInfos("You're not lucky today : no teaser for you");
                    }
                }
                return;
            }
        }
        throw new InvalidIsbnException("Movie not found with isbn : "+isbn);
    }
}
