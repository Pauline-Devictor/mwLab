package server;

import exceptionHelper.InvalidIsbnException;

import java.io.Serializable;
import java.math.BigInteger;
import java.rmi.RemoteException;

public class MovieDesc implements Serializable {
    String movieName;
    String isbn;
    String synopsis;
    Bill bill;

    //If a Bill is given
    public MovieDesc(String movieName, String isbn, String synopsis, Bill bill) throws InvalidIsbnException {
        this.movieName = movieName;
        long count = isbn.chars().filter(ch -> ch == '-').count();
        String isbnNb = isbn.replace("-","");
        if((count != 5) || (isbnNb.length() != 13) || (!isbnNb.chars().allMatch(Character::isDigit)))
            throw new InvalidIsbnException(isbn);
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.bill = bill;
    }
    //If we give a price instead of the Bill
    public MovieDesc(String movieName, String isbn, String synopsis, String price) throws InvalidIsbnException {
        this.movieName = movieName;
        long count = isbn.chars().filter(ch -> ch == '-').count();
        String isbnNb = isbn.replace("-","");
        if((count != 5) || (isbnNb.length() != 13) || (!isbnNb.chars().allMatch(Character::isDigit)))
            throw new InvalidIsbnException(isbn);
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.bill = new Bill(movieName, new BigInteger(price));
    }

    @Override
    public String toString(){
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", isbn='" + isbn + '\'' +
                "}";
    }
    //To print when Client ask for movie details
    public String toStringDetails() throws RemoteException {
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                "}";
    }

    public String getMovieName() {
        return movieName;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getSynopsis() {
        return synopsis;
    }

    public Bill getBill() {
        return bill;
    }
}
