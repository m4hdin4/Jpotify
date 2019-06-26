import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.net.Socket;

public class ClientManager implements Runnable {

    private Socket clientHolder;
    private Server serverHolder;
    private InputStream fromClientStream;
    private OutputStream toClientStream;
    private DataInputStream reader;
    private DataOutputStream writer;

    public void setAddUserToServerPanel(AddUserToServerPanel addUserToServerPanel) {
        this.addUserToServerPanel = addUserToServerPanel;
    }


    private AddUserToServerPanel addUserToServerPanel;





    public ClientManager(Server server , Socket socket){
        this.serverHolder = server;
        clientHolder = socket;
    }

    @Override
    public void run() {
        try {
            fromClientStream = clientHolder.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            toClientStream = clientHolder.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer = new DataOutputStream(toClientStream);

        String name="";
        try {
            reader = new DataInputStream(fromClientStream);
            name = reader.readUTF();
            System.out.println(name);
            serverHolder.addClientManager(name , this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SingleUser newSingleUser = new SingleUser(name);
        addUserToServerPanel.addUserToServerPanel(newSingleUser);
        File file = new File("C:\\Users\\mm\\Downloads\\Music\\Bigharar.mp3");
        long fileLength = file.length();
        byte[] fileData = new byte[(int)fileLength];
        try {
            reader.readFully(fileData , 0 , fileData.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream b = new ByteArrayInputStream(fileData);
        File f = new File("replace.mp3");
        try {
            OutputStream os = new FileOutputStream(f);
            os.write(fileData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("replace.mp3");
            MusicPlayer musicPlayer = new MusicPlayer(fileInputStream);
            musicPlayer.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }
}
