package Server;

import java.util.List;

public class Connection implements IVODService {
    List<Client> clientlist;
    public boolean signIn(String mail, String pwd) throws SignInFailed {
        if (mail == null || pwd == null) {
            throw new SignInFailed("Invalid mail or password");
        }
        for (Client client : clientlist) {
            if (client.getmail().equals(mail)) {
                throw new SignInFailed("Mail already in use");
            }
        }
        Client c = new Client(mail,pwd);
        clientlist.add(c);
        return true;
    }
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException {
        if (mail == null || pwd == null) {
            throw new InvalidCredentialsException("Invalid mail or password");
        }
        for (Client client : clientlist){
            if (client.getmail().equals(mail) && client.getpwd().equals(pwd)) {
                return this;
            }
        }
        throw new InvalidCredentialsException("Invalid mail or password");
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
