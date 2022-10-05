package client;

import exceptionHelper.InvalidCredentialsException;
import exceptionHelper.InvalidIsbnException;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;
import exceptionHelper.SignInFailed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    public static IClientBox clientBox;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws NotBoundException, RemoteException, SignInFailed, MalformedURLException, InvalidCredentialsException, InvalidIsbnException {
        //distance
       /* Registry reg = LocateRegistry.getRegistry("192.168.90.117", 6542);
        IConnection connection= (IConnection)reg.lookup("Connection");*/
        //local
        IConnection connection = (IConnection) Naming.lookup("rmi://localhost:2001/Connection");

        //System.out.println("Let's have a new account");

        IVODService ivodService = connection(connection);

        IClientBox myBox = new ClientBox();
        Registry reg = LocateRegistry.createRegistry(2002);

        clientBox = ClientBox.getInstance(1003);
        reg.rebind("ClientBox", clientBox);
        boolean stop = false;
        while(!stop){
            System.out.println("Do you want to see the catalog (1), to rent a movie (2), to add a movie (3), or to quit (0) ?");
            String choice = scanner.nextLine();
            if (choice.equals("1")){
                System.out.println("Now let's see the catalog !");
                System.out.println(ivodService.viewCatalog());
            }
            else if (choice.equals("2")){
                System.out.println("And now let's try to rent a movie ! Enter the ISBN of the movie :");
                String isbn = scanner.nextLine();
                //String isbn = "14325426235324-2132";
                watchDetails(ivodService, clientBox, isbn);
                System.out.println("Here is the movie :");
                watchMovie(ivodService, clientBox, isbn);
            }
            else if (choice.equals("3")){
                System.out.println("Please enter the name of the movie");
                String name = scanner.nextLine();
                System.out.println("Please enter the isbn of the movie");
                String isbn = scanner.nextLine();
                System.out.println("Please enter the synopsis of the movie");
                String synopsis = scanner.nextLine();
                System.out.println("Please enter the price of the movie");
                String price = scanner.nextLine();
                System.out.println("Do you have a teaser for this movie ? (Y/n)");
                String rep = scanner.nextLine();
                if(rep.equals("Y")){
                    System.out.println("Please enter the teaser of the movie");
                    String teaser = scanner.nextLine();
                    ivodService.addmovie(name, isbn, synopsis, price, teaser);
                } else
                    ivodService.addmovie(name, isbn, synopsis, price);
            }
            else if(choice.equals("0")) stop = true;
            }

        reg.unbind("ClientBox");
        System.out.println("End of the client connection");
    }

    private static IVODService connection(IConnection connection) {
        System.out.println("Welcome ! Do you want to create a account (1) or to login (2) ? ");
        while(true) {
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                return signin(connection);
            } else if (choice.equals("2")) {
                return login(connection);
            }
        }
    }

    private static IVODService signin(IConnection connection){
        boolean signedIn = false;
        while (!signedIn){
            System.out.println("Please enter your mail");
            String mail = scanner.nextLine();
            System.out.println("Please enter your password");
            String pwd = scanner.nextLine();
            try {
                signedIn = connection.signIn(mail,pwd);
            } catch (SignInFailed | IOException signInFailed) {
                System.out.println(signInFailed.getMessage());
            }
        }
        return connection(connection);
    }

    private static IVODService login(IConnection connection){
        IVODService ivodService = null;
        do {
            try {
                System.out.println("Please enter your mail");
                String mail = scanner.nextLine();
                System.out.println("Please enter your password");
                String pwd = scanner.nextLine();
                ivodService = connection.login(mail, pwd);
            } catch (InvalidCredentialsException | RemoteException e) {
                System.out.println(e.getMessage());
            }
        } while (ivodService == null);
        return ivodService;
    }

    /**
     * Watch movie details like Synopsis and if there's one the Teaser
     * @param ivodService
     * @param myBox
     * @param isbn
     * @throws RemoteException
     * @throws InvalidIsbnException
     */
    private static void watchDetails(IVODService ivodService, IClientBox myBox, String isbn) throws RemoteException, InvalidIsbnException {
        System.out.println("Do you want to see the movie details ? Yes(1) or No(0)");
        String details = scanner.nextLine();
        if (details.equals("1")) {
            try{
                ivodService.getMovieDetails(isbn, myBox);
            }
            catch (InvalidIsbnException e){
                System.out.println(e.getMessage());
            }
        }
        else if (details.equals("0")) {
            System.out.println("Ok, let's go !");
        }
        else {
            System.out.println("Wrong input, try again");
        }
    }

    private static void watchMovie(IVODService ivodService, IClientBox myBox, String isbn) throws RemoteException, InvalidIsbnException {
        try {
            ivodService.playmovie(isbn, myBox);
        }catch (InvalidIsbnException e){
            System.out.println(e.getMessage());
        }
    }
}
