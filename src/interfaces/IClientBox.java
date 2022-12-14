package interfaces;

import java.rmi.Remote;

public interface IClientBox extends Remote {
    void stream(byte[] chunk)  throws java.rmi.RemoteException;
    void printInfos(String details) throws java.rmi.RemoteException;
}

