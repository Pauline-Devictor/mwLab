package server;

import interfaces.IConnection;
import interfaces.IVODService;

import java.io.*;

public class CSVManager {
    public CSVManager() {
    }

    public void readClientData(IConnection connection) {
        String line;
        try
        {
        //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("./src/server/ClientData.csv"));
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

    public void readMovieData(IVODService ivodService) {
        String line;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("./src/server/MovieData.csv"));
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
        }
    }

    public void writeClientData(Client client) {
        try {
            FileWriter csvWriter = new FileWriter("./src/server/ClientData.csv", true);
            csvWriter.append(client.getmail());
            csvWriter.append(",");
            csvWriter.append(client.getpwd());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Le client \""+client.getmail()+"\" a bien été ajouté au fichier CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMovieData(MovieDesc movie) {
        try {
            FileWriter csvWriter = new FileWriter("./src/server/MovieData.csv", true);
            csvWriter.append(movie.getMovieName());
            csvWriter.append(",");
            csvWriter.append(movie.getIbsn());
            csvWriter.append(",");
            csvWriter.append(movie.getSynopsis());
            csvWriter.append(",");
            csvWriter.append(movie.getBill().getOutrageousPrice().toString());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Le film \""+movie.getMovieName()+"\" a bien été ajouté au fichier CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

