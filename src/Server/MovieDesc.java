package Server;

public class MovieDesc {
    String movieName;
    String ibsn;
    String synopsis;


    public MovieDesc(String movieName, String ibsn, String synopsis) {
        this.movieName = movieName;
        this.ibsn = ibsn;
        this.synopsis = synopsis;
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
}
