package Charles;

public interface Server2 extends java.rmi.Remote {

    public void printOnServer(String s, Client2 c) throws java.rmi.RemoteException;
    public void subscribe(String s, Client2 c) throws java.rmi.RemoteException;
    public void add(Client2 c, String nam, String rul, String creat, int pub) throws java.rmi.RemoteException;
    public void list(Client2 c) throws java.rmi.RemoteException;
    public void request(Client2 c, int id) throws java.rmi.RemoteException;
    public void deliver(Client2 c, int id) throws java.rmi.RemoteException;
    public void consult(Client2 c, String Board) throws java.rmi.RemoteException;
}

