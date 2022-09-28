package client;

import server.Bill;
import server.IClientBox;
import server.MovieDesc;

import java.util.List;

public interface IVODService extends java.rmi.Remote {
    List<MovieDesc> viewCatalog();
    Bill playmovie(String isbn, IClientBox box);
}

