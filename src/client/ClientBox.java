package client;

import interfaces.IClientBox;

import java.io.Serializable;

public class ClientBox implements IClientBox, Serializable {
    @Override
    public void stream(byte[] chunk) {
        for (byte b: chunk) {
            System.out.print(b);
        }
        System.out.println("");
    }
}
