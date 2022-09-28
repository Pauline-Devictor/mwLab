package client;

public interface IClientBox extends java.rmi.Remote {
    public void stream(byte[] chunk) throws java.rmi.RemoteException;
}

