
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import sun.java2d.cmm.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ProfileSettings extends JFrame {
    private final int imageSize = 60;

    private final int WIDTH = 210, HEIGHT = 180;
    private final String WINDOWS_TITLE = "Profile";
    private JPanel photo;
    private JLabel userPhoto;
    private JLabel name;
    private JButton changeName;

    ChangeProfileJpotifyFrame changePhoto;

    private Image personalImage;



    public ProfileSettings (){
        super();


        this.setDefaultCloseOperation(ProfileSettings.HIDE_ON_CLOSE);

        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setVisible(false);
        this.setLayout(new GridLayout(2 , 1));


        photo = new JPanel();
        photo.setLayout(new FlowLayout());
        userPhoto = new JLabel("    \n");

        userPhoto.setOpaque(false);

        try {
            Image img = ImageIO.read(getClass().getResource("/user1.png"));
            Image image = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            userPhoto.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        JButton camera = new JButton();
        camera.setOpaque(false);
        camera.setContentAreaFilled(false);
        camera.setBorderPainted(false);
        camera.setToolTipText("Tap to change photo");
        try {
            Image img = ImageIO.read(getClass().getResource("camera.png"));
            Image image = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            camera.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        camera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Select an image");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpg");
                jfc.addChoosableFileFilter(filter);

                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getSelectedFile().getPath());
                    try {
                        Image img = ImageIO.read(jfc.getSelectedFile());
                        personalImage = img;
                        Image image = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                        userPhoto.setIcon(new ImageIcon(image));
                        changePhoto.changeProfileJpotifyFrame(img);
                    } catch (Exception ex) {
                        System.out.println("error");
                    }

                }


            }
        });

        photo.setBackground(new Color(0x636363));
        photo.add(userPhoto);
        photo.add(camera);
        this.add(photo);

        JPanel userName = new JPanel();
        userName.setBackground(new Color(0x595B5D));
        userName.setLayout(new GridLayout(2,1));

        this.setLocationRelativeTo(null);

        name = new JLabel();
        name.setOpaque(false);
        name.setText("tuem");

        changeName = new JButton("Change Your Name");
        changeName.setBackground(new Color(0));
        changeName.setForeground(new Color(0xFFFFFF));
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newNameDialog = new JOptionPane();
                String newNameText = newNameDialog.showInputDialog(name , "new name");
                if (newNameText != null && !newNameText .equals(""))
                    name.setText(newNameText);
            }
        });
        userName.add(name);
        userName.add(changeName);

        this.add(userName);
        this.setResizable(false);
        this.setBackground(new Color(0x410717));
    }

    public void setChangePhoto(ChangeProfileJpotifyFrame changePhoto) {
        this.changePhoto = changePhoto;
    }

    public void setVisible(){
        this.setVisible(true);
    }
}
