package server;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return "MovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getIbsn() {
        return ibsn;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
