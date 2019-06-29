import java.io.DataInputStream;
import java.io.IOException;

public class ServerMassagesManager implements Runnable {
    DataInputStream readerHolder;

    public ServerMassagesManager(DataInputStream reader) {
        readerHolder = reader;
    }

    public void run() {

        while (true) {
            try {
                String command = readerHolder.readLine();
                if (command.equals("CHT")) {
                    String from = readerHolder.readUTF();
                    String text = readerHolder.readLine();

                    System.out.println(from + " : [" + text + "]");
                } else if (command.equals("RESULT")) {
                    String result = readerHolder.readLine();
                    System.out.println("ans :" + result);
                }
            } catch (IOException e) {
            }

        }
    }
}
