import javazoom.jl.player.advanced.PlaybackListener;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void setGetUserNameToServer(GetUserNameToServer getUserNameToServer) {
        this.getUserNameToServer = getUserNameToServer;
    }

    private GetUserNameToServer getUserNameToServer;
    private String USERNAME;
    private String SONGNAME;


    public Client() throws IOException {
        USERNAME = getUserNameToServer.getUserNameToServer();
        socket = new Socket("172.20.10.2" ,1601);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        //dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //dataInputStream = new DataInputStream(socket.getInputStream());
        String question = dataInputStream.readUTF();
        System.out.println(question);
        dataOutputStream.writeUTF(name);
        File file = new File("C:\\Users\\mm\\Downloads\\Music\\Bigharar.mp3");
        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
        URL url = file.toURL();
        o.writeObject(url);
//        long fileLength = file.length();
//        byte[] fileData = new byte[(int)fileLength];
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//        bis.read(fileData, 0, fileData.length);
//        OutputStream os = socket.getOutputStream();
//        os.write(fileData, 0, fileData.length);
//        os.flush();
        socket.close();


    }

//    public static void main(String[] args) throws IOException {
//        new Client();
//    }
}
