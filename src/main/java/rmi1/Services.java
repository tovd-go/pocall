package rmi1;

import java.rmi.RemoteException;
public interface Services extends java.rmi.Remote {
     String sendMessage(Message msg) throws RemoteException;

}