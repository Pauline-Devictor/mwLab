package server;

import exceptionHelper.InvalidIsbnException;
import interfaces.IConnection;
import interfaces.IVODService;

import java.io.*;

public class CSVManager {
    public CSVManager() {
    }

    //Get Clients Datas from Database and add the list to clientList in Connection
    public void readClientData(IConnection connection) {
        String line;
        try
        {
        //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("./src/Database/ClientData.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] client = line.split(",");
                connection.addClient(new Client(client[0], client[1]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Get Movies Datas from Database and add the list to ivodService
    public void readMovieData(IVODService ivodService) {
        String line;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("./src/Database/MovieData.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] movie = line.split(",");
                if(movie.length == 4)
                    ivodService.initMovie(new MovieDesc(movie[0], movie[1], movie[2], movie[3]));
                else ivodService.initMovie(new MovieDescExtended(movie[0], movie[1], movie[2], movie[3], movie[4]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (InvalidIsbnException e) {
            throw new RuntimeException(e);
        }
    }

    //For new client, we add their infos in the Database
    public void writeClientData(Client client) {
        try {
            FileWriter csvWriter = new FileWriter("./src/Database/ClientData.csv", true);
            csvWriter.append(client.getmail());
            csvWriter.append(",");
            csvWriter.append(client.getpwd());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Le client \""+client.getmail()+"\" a bien ??t?? ajout?? au fichier CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //For new movies, we add their infos in the Database
    public void writeMovieData(MovieDesc movie) {
        try {
            FileWriter csvWriter = new FileWriter("./src/Database/MovieData.csv", true);
            csvWriter.append(movie.getMovieName());
            csvWriter.append(",");
            csvWriter.append(movie.getIsbn());
            csvWriter.append(",");
            csvWriter.append(movie.getSynopsis());
            csvWriter.append(",");
            csvWriter.append(movie.getBill().getOutrageousPrice().toString());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Le film \""+movie.getMovieName()+"\" a bien ??t?? ajout?? au fichier CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

