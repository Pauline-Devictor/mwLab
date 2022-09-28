package Server;

import java.lang.reflect.Array;
import java.util.List;

public class Connection implements IVODService {
    Array clientlist;
    public boolean signIn(String mail, String pwd) throws SignInFailed {
        return true;
    }
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException {
        return new VODService();
    }

    @Override
    public List<MovieDesc> viewCatalog() {
        return null;
    }

    @Override
    public Bill playmovie(String isbn, IClientBox box) {
        return null;
    }
}
