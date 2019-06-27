import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ServerSocket serverSocket ;
    private int serverPort;
    private ArrayList<Thread> threads ;
    private HashMap<String, ClientManager> clientsMap ;
    private Socket client;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ExecutorService executorService = Executors.newCachedThreadPool();


    public void setClientServerSet(ClientServerSet clientServerSet) {
        this.clientServerSet = clientServerSet;
    }

    private ClientServerSet clientServerSet;

    public HashMap<String, ClientManager> getClientsMap() {
        return clientsMap;
    }

    public void setClientsMap(HashMap<String, ClientManager> clientsMap) {
        this.clientsMap = clientsMap;
    }


    public Server() throws IOException, ClassNotFoundException, JavaLayerException {

    }


    public void addClientManager(String name , ClientManager clientManager){
        clientsMap.put(name , clientManager);
    }

//    public static void main(String[] args) throws IOException, JavaLayerException, ClassNotFoundException {
//        InetAddress inetAddress = InetAddress.getLocalHost();
//        System.out.println(inetAddress.getHostAddress().trim());
//        new Server();
//    }


    @Override
    public void run() {
        threads = new ArrayList<>();
        clientsMap = new HashMap<>();
        this.serverPort = 1630;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                System.out.println("waiting for client");
                client = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("new client connected");
            ClientManager temp = new ClientManager(this ,client);
            clientServerSet.clientServerSet(temp);
            executorService.submit(temp);

            /*Thread thread = new Thread(temp);
            threads.add(thread);
            thread.start();*/
        }
    }
}
