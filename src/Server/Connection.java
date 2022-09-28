package Server;

import java.lang.reflect.Array;

public class Connection implements IVODService {
    Array clientlist;
    public boolean signIn(String mail, String pwd) throws SignInFailed {
        return true;
    }
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException {
        return new VODService();
    }
}
