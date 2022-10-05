package server;

import exceptionHelper.InvalidIsbnException;

public class MovieDescExtended extends MovieDesc{
    //String synopsis;
    byte[] teaser;

    //If no teaser given
    public MovieDescExtended(String movieName, String isbn, String synopsis, Bill bill) throws InvalidIsbnException {
        super(movieName, isbn, synopsis, bill);
        teaser = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    }

    //If a teaser is given
    public MovieDescExtended(String movieName, String isbn, String synopsis, String price, String teaser) throws InvalidIsbnException {
        super(movieName, isbn, synopsis, price);
        this.teaser = teaser.getBytes();
    }

    @Override
    public String toString(){
        return "\nMovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", isbn='" + isbn + '\'' +
                "}";
    }
}
