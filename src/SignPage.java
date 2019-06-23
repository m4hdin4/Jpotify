import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;


/**
 * SignPage class is the second frame that shows to user
 * user will sing in application and entered the username
 * in this frame
 */
public class SignPage extends JFrame implements ProfilePhotoLinker2,SignpageVisibility {
    public void setChangeName(UsernameLinker1 changeName) {
        this.changeName1 = changeName;
    }

    private JButton profilePhoto;
    private JPanel down ;
    private JButton signIn;
    private JTextField nameField;

    private UsernameLinker1 changeName1;
    private UsernameLinker2 changeName2;

    public void setChangeName2(UsernameLinker2 changeName2) {
        this.changeName2 = changeName2;
    }

    private ShowNextFrame show;
    private ExecutorService executorService;

    public void setShow(ShowNextFrame show) {
        this.show = show;
    }

    public SignPage() {
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        profilePhoto = new JButton();
        profilePhoto.setEnabled(false);
        profilePhoto.setOpaque(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/user3.png"));
            Image image = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            profilePhoto.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        profilePhoto.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//                jfc.setDialogTitle("Select an image");
//                jfc.setAcceptAllFileFilterUsed(false);
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpg");
//                jfc.addChoosableFileFilter(filter);
//
//                int returnValue = jfc.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    System.out.println(jfc.getSelectedFile().getPath());
//                    try {
//                        Image img = ImageIO.read(jfc.getSelectedFile());
//                        Image image = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
//                        profilePhoto.setIcon(new ImageIcon(image));
//                    } catch (Exception ex) {
//                        System.out.println(ex);
//                    }
//
//                }
//            }
//        });
        this.add(profilePhoto, BorderLayout.NORTH);
        this.setBackground(new Color(0xA2A2A2));
        down = new JPanel();
        down.setBackground(new Color(0xAEAEAE));
        down.setLayout(new GridLayout(2,1));
        JLabel selectName = new JLabel("Enter Your Username : ");
        selectName.setOpaque(false);
        nameField = new JTextField();
        down.add(selectName );
        down.add(nameField);

        signIn = new JButton("sign in ");
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeName1.linker(nameField.getText());
                changeName2.linker(nameField.getText());
                nameField.setText("");
                show.showFrame();
                hideFrame();
            }
        });
        signIn.setFont(new Font("bold" , Font.BOLD , 15));
        signIn.setForeground(new Color(0xFFFFFF));
        signIn.setBackground(new Color(0));
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JpotifyFrame jpotifyFrame = new JpotifyFrame();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(down , BorderLayout.CENTER);
        this.add(signIn , BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setTitle("Profile");
        this.setVisible(true);
        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);

    }

    /**
     * hide signpage
     */
    public void hideFrame(){
        this.setVisible(false);
    }

    @Override
    public void linker(File imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            Image image = img.getScaledInstance(60, 60, img.SCALE_SMOOTH);
            profilePhoto.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void changeVisibility(boolean b) {
        setVisible(b);
    }
}
