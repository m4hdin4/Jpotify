
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class JPotify implements SignNewUser,ClientServerSet , Serializable {
    private FirstFrame firstFrame ;
    private JPotifyUser jPotifyUser ;
    private Server server;
    private Client client;
    public JPotify() throws IOException, JavaLayerException, ClassNotFoundException, InterruptedException {
        try {
            firstFrame = new FirstFrame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            try{
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        jPotifyUser = new JPotifyUser();
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress().trim());
        server = new Server();
        Thread t1 = new Thread(server);
        t1.start();
        server.setClientServerSet(this);
        System.out.println("yeah");
//        TimeUnit.SECONDS.sleep(15);
//        client = new Client();
//        Thread t = new Thread(client);
//        t.start();
//        client.setGetUserNameToServer(jPotifyUser.getJpotifyFrame().getSearch().getProfileSettings());
//        client.setGetCurrentSongToServer(jPotifyUser.getJpotifyFrame().getPlayMusic());
//        jPotifyUser.getJpotifyFrame().getSearch().getProfileSettings().setUser(this);
        jPotifyUser.getJpotifyFrame().getSearch().getProfileSettings().setUser(this);
    }

    /**
     * handle signing a new user
     */
    @Override
    public void newUser() {
        jPotifyUser.getJpotifyFrame().dispose();
        jPotifyUser.getSignPage().dispose();
        jPotifyUser = new JPotifyUser();
    }

    /**
     * set the client
     */
    @Override
    public void clientServerSet(ClientManager manager) {
        manager.setAddUserToServerPanel(jPotifyUser.getJpotifyFrame().getServerPanel());
    }
}
