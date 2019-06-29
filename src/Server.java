import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable  {

    private ServerSocket serverSocket ;
    private int serverPort;
    private ArrayList<Thread> threads ;
    private HashMap<String, ClientManager> clientsMap ;

    public HashMap<String, String> getFriendsList() {
        return friendsList;
    }

    private HashMap<String , String> friendsList;
    private Socket client;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ExecutorService executorService = Executors.newCachedThreadPool();


    public HashMap<String, ClientManager> getClientsMap() {
        return clientsMap;
    }

    public void setClientsMap(HashMap<String, ClientManager> clientsMap) {
        this.clientsMap = clientsMap;
    }


    public Server() throws IOException, ClassNotFoundException, JavaLayerException {
        threads = new ArrayList<>();
        clientsMap = new HashMap<>();
        friendsList=new HashMap<>();
        this.serverPort = 1622;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addClientManager(String name , ClientManager clientManager){
        clientsMap.put(name , clientManager);
    }
    public void addFriend(String userName , String songName){
        friendsList.put(userName , songName);
    }


    @Override
    public void run() {
        while(true){
            try {
                System.out.println("waiting for client");
                client = serverSocket.accept();
                System.out.println("new client connected");
                ClientManager temp = new ClientManager(this ,client , friendsList);
                Thread thread = new Thread(temp);
                threads.add(thread);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public ArrayList<ClientManager> findAllClientManagers(){
        ArrayList<ClientManager> result=new ArrayList<>();
        for(Map.Entry<String,ClientManager> entry:clientsMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;

    }



}