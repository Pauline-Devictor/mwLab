package server;

import interfaces.IClientBox;

import java.io.Serializable;
import java.math.BigInteger;
import java.rmi.RemoteException;

public class MovieDesc implements Serializable {
    String movieName;
    String ibsn;
    String synopsis;
    Bill bill;


    public MovieDesc(String movieName, String ibsn, String synopsis, Bill bill) {
        this.movieName = movieName;
        this.ibsn = ibsn;
        this.synopsis = synopsis;
        this.bill = bill;
    }

    public MovieDesc(String movieName, String ibsn, String synopsis, String price) {
        this.movieName = movieName;
        this.ibsn = ibsn;
        this.synopsis = synopsis;
        this.bill = new Bill(movieName, new BigInteger(price));
    }

    public String toString(IClientBox box) throws RemoteException {
        return toString();
    }

    @Override
    public String toString(){
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                "}";
    }
    public String toStringDetails() throws RemoteException {
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                "}";
    }

    public String getMovieName() {
        return movieName;
    }

    public String getIbsn() {
        return ibsn;
    }
    public String getSynopsis() {
        return synopsis;
    }

    public Bill getBill() {
        return bill;
    }
}
