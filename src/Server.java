import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket ;
    private int serverPort;
    private ArrayList<Thread> threads ;
    private HashMap<String, ClientManager> clientsMap ;
    private Socket client;

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
        threads = new ArrayList<>();
        clientsMap = new HashMap<>();
        this.serverPort = 1658;
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

            ObjectInputStream i = new ObjectInputStream(client.getInputStream());
            URL o = (URL)i.readObject();
            AdvancedPlayer advancedPlayer=new AdvancedPlayer(o.openStream());
            advancedPlayer.play();
            ClientManager temp = new ClientManager(this ,client);
            clientServerSet.clientServerSet(temp);
            Thread thread = new Thread(temp);
            threads.add(thread);
            thread.start();
        }
    }


    public void addClientManager(String name , ClientManager clientManager){
        clientsMap.put(name , clientManager);
    }

//    public static void main(String[] args) throws IOException, JavaLayerException, ClassNotFoundException {
//        InetAddress inetAddress = InetAddress.getLocalHost();
//        System.out.println(inetAddress.getHostAddress().trim());
//        new Server();
//    }
}
