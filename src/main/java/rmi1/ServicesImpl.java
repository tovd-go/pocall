package rmi1;

import java.rmi.RemoteException;

   public class ServicesImpl implements Services {
       public ServicesImpl() throws RemoteException {
       }

       @Override
       public String sendMessage(Message msg) throws RemoteException {
           return msg.getMessage();
       }

   }