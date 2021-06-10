import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class is the main server and contains clientmanagers
 */
public class Server implements Runnable  {

    private ServerSocket serverSocket ;
    private int serverPort;
    private ArrayList<Thread> threads ;
    private HashMap<String, ClientManager> clientsMap ;



    private HashMap<String , String> friendsList;
    private Socket client;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public HashMap<String, ArrayList<byte[]>> getFriendsFiles() {
        return friendsFiles;
    }

    private HashMap<String , ArrayList<byte[]>> friendsFiles;
    public HashMap<String, String> getFriendsList() {
        return friendsList;
    }
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
        friendsFiles=new HashMap<>();
        this.serverPort = 1622;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adding a new client manager
     * @param name the name of the new client
     * @param clientManager clientmanager of each manager
     */
    public void addClientManager(String name , ClientManager clientManager){
        clientsMap.put(name , clientManager);
    }

    /**
     * adding a new client to friendList of other clients
     * @param userName username of client
     * @param songName the song that at the current time is playing
     */
    public void addFriend(String userName , String songName){
        friendsList.put(userName , songName);
    }

    /**
     * saving any client songs file
     * @param name username of client
     * @param files files of songs
     */
    public void addFriendsFiles(String name , ArrayList<byte[]> files) {friendsFiles.put(name,files);}

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("waiting for client");
                client = serverSocket.accept();
                System.out.println("new client connected");
                ClientManager temp = new ClientManager(this ,client , friendsList,friendsFiles);
                Thread thread = new Thread(temp);
                threads.add(thread);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * it returns all clientManagers
     * @return all clientManagers
     */
    public ArrayList<ClientManager> findAllClientManagers(){
        ArrayList<ClientManager> result=new ArrayList<>();
        for(Map.Entry<String,ClientManager> entry:clientsMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;

    }



}