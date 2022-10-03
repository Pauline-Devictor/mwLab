package server;

import interfaces.IClientBox;

import java.rmi.RemoteException;

public class MovieDescExtended extends MovieDesc{
    String synopsis;
    byte[] teaser;

    public MovieDescExtended(String movieName, String ibsn, String synopsis, Bill bill) {
        super(movieName, ibsn, synopsis, bill);
        teaser = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}; //provisoire, pour tester
    }

    public String toString(IClientBox box) throws RemoteException {
        box.stream(teaser);
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                "}";
    }
    @Override
    public String toString(){
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", teaser='" + teaser + '\'' +
                "}";
    }
}
