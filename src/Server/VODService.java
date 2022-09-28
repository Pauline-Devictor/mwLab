package Server;

public class VODService implements IVODService {
    List<MovieDesc> viewCatalog();
    Bill playmovie(String isbn, IClientBox box);

}
