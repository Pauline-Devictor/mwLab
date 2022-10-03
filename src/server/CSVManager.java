package server;

import interfaces.IConnection;

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
}

