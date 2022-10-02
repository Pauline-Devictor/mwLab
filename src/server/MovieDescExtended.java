package server;

public class MovieDescExtended extends MovieDesc{
    String synopsis;
    byte[] teaser;

    public MovieDescExtended(String movieName, String ibsn, String synopsis, Bill bill) {
        super(movieName, ibsn, synopsis, bill);
        teaser = new byte[]{1, 2, 3, 4, 5, 'a', 'b', 'c', 'd', 'e', 1, 'a', 2, 'b', 3}; //provisoire, pour tester
    }

    @Override
    public String toString() {
        return "MovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", teaser='" + teaser + '\'' +
                "}\n";
    }
}
