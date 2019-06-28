import java.io.*;
import java.util.ArrayList;

public class ClientReciever extends Thread{

    InputStream reader;
    ObjectInputStream objectInputStream;
    public void setSingleUserToServerPanel(AddSingleUserToServerPanel singleUserToServerPanel) {
        this.singleUserToServerPanel = singleUserToServerPanel;
    }

    private AddSingleUserToServerPanel singleUserToServerPanel;

    public ClientReciever(InputStream inputStream) throws IOException {
        this.reader = inputStream;
    }

    @Override
    public void run(){

            DataInputStream dataInputStream = new DataInputStream(reader);
            String name = "";
            String songName = "";
            ArrayList<File> arrayList;
            try {
                name = dataInputStream.readUTF();
                songName = dataInputStream.readUTF();

               // arrayList=(ArrayList<File>) objectInputStream.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            }// catch (ClassNotFoundException e) {
            //    e.printStackTrace();
          //  }

            SingleUser newSingleUser = new SingleUser(name, songName);
            singleUserToServerPanel.addSingleUserToServer(newSingleUser);

    }
}
