package Charles;

public interface Client2 extends java.rmi.Remote {

    public void printOnClient(String s) throws java.rmi.RemoteException;
    public void menu() throws java.rmi.RemoteException;
}
