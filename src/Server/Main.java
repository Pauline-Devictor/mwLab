package Server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Main {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Hello World! Work in progress ! :)");
        Registry reg = LocateRegistry.createRegistry(2001);
        Connection connection = new Connection(1001);

        reg.rebind("Connection", connection);
    }
}
