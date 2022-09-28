package server;

import java.math.BigInteger;

public class Bill {
    String movieName;
    BigInteger outrageousPrice; //(<--it mays not be that big)

    public Bill(String movieName, BigInteger outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public BigInteger getOutrageousPrice() {
        return outrageousPrice;
    }

}
