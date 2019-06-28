import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable ,Serializable {

    private Socket clientHolder;
    private Server serverHolder;
    private InputStream fromClientStream;
    private OutputStream toClientStream;
    private DataInputStream reader;
    private DataOutputStream writer;
    private boolean flag ;



    public ClientManager(Server server , Socket socket){
        this.serverHolder = server;
        clientHolder = socket;
        flag=false;
    }

    @Override
    public void run() {
        String name;
            String songName;

        while(true) {
            try {
                writer = new DataOutputStream(clientHolder.getOutputStream());
                reader = new DataInputStream(clientHolder.getInputStream());
                name = reader.readUTF();

                songName = reader.readUTF();
                System.out.println(name);
                serverHolder.addClientManager(name , this);
                writer.writeUTF(name);
                writer.writeUTF(songName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void sendObjectToAllClients(Object o) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(o);
        }
    }
    private void sendTextToAllClients(String text) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(text);
        }
    }

    private void sendObject(Object o) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientHolder.getOutputStream());
        objectOutputStream.writeObject(o);
    }
    private void sendObject(String text) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientHolder.getOutputStream());
        dataOutputStream.writeUTF(text);
    }





}
