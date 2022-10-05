package server;

import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements Serializable {
    String movieName;
    BigInteger outrageousPrice; //(<--it mays not be that big but we choose to make it like that)

    public Bill(String movieName, BigInteger outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "movieName='" + movieName + '\'' +
                ", outrageousPrice='" + outrageousPrice + '\'' +
                '}';
    }

    public String getMovieName() {
        return movieName;
    }

    public BigInteger getOutrageousPrice() {
        return outrageousPrice;
    }

}
