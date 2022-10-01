package server;

public class Client {
    private final String mail;
    private final String pwd;
    public Client(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getmail() {
        return mail;
    }

    public String getpwd () {
        return pwd;
    }
}
