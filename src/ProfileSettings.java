
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ProfileSettings extends JFrame implements UsernameLinker1 {
    public JPanel getPhoto() {
        return photo;
    }

    public JLabel getUserPhoto() {
        return userPhoto;
    }


    public JButton getChangeName() {
        return changeName;
    }

    public JLabel getUserNameText() {
        return userNameText;
    }

    public void setUser(SignNewUser user) {
        this.user = user;
    }

    private final int imageSize = 60;

    private final int WIDTH = 210, HEIGHT = 200;
    private final String WINDOWS_TITLE = "Profile";
    private JPanel photo;
    private JLabel userPhoto;
    private JLabel userNameText;
    private JButton changeName;
    private JButton camera;

    private JButton signOut;

    public JButton getCamera() {
        return camera;
    }

    private ProfilePhotoLinker1 changePhoto1;
    private ProfilePhotoLinker2 changePhoto2;
    private ProfileNameLinker nameLinker;

    public void setNameLinker(ProfileNameLinker nameLinker) {
        this.nameLinker = nameLinker;
    }

    private SignNewUser user;

    private JpotifyVisibility jpotifyVisibility;
    private SignpageVisibility signpageVisibility;

    public void setJpotifyVisibility(JpotifyVisibility jpotifyVisibility) {
        this.jpotifyVisibility = jpotifyVisibility;
    }

    public void setSignpageVisibility(SignpageVisibility signpageVisibility) {
        this.signpageVisibility = signpageVisibility;
    }

    public ProfileSettings() {
        super();


        this.setDefaultCloseOperation(ProfileSettings.HIDE_ON_CLOSE);

        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setVisible(false);
        this.setLayout(new GridLayout(2, 1));


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

        camera = new JButton();
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
                JFileChooser photoChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                photoChooser.setDialogTitle("Select an image");
                photoChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpg");
                photoChooser.addChoosableFileFilter(filter);

                int returnValue = photoChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println(photoChooser.getSelectedFile().getPath());
                    try {
                        Image img = ImageIO.read(photoChooser.getSelectedFile());
                        Image image = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                        userPhoto.setIcon(new ImageIcon(image));
                        changePhoto1.linker(photoChooser.getSelectedFile());
                        changePhoto2.linker(photoChooser.getSelectedFile());
                    } catch (Exception ex) {
                        System.out.println(ex);
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
        userName.setLayout(new GridLayout(3, 1));

        this.setLocationRelativeTo(null);

        userNameText = new JLabel();
        userNameText.setOpaque(false);
        //userNameText.setText("tuem");

        changeName = new JButton("Change Your Name");
        changeName.setBackground(new Color(0));
        changeName.setForeground(new Color(0xFFFFFF));
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newNameDialog = new JOptionPane();
                String newNameText = newNameDialog.showInputDialog(userNameText, "new name");
                if (newNameText != null && !newNameText.equals("")) {
                    userNameText.setText(newNameText);
                    nameLinker.nameLinker(newNameText);
                }
            }
        });

        signOut = new JButton("sign out");
        signOut.setOpaque(false);
        signOut.setBackground(new Color(0));
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                user.newUser();
//                jpotifyVisibility.changeVisibility(false);
//                signpageVisibility.changeVisibility(true);
            }
        });

        userName.add(userNameText);
        userName.add(changeName);
        userName.add(signOut);
        this.add(userName);


        this.setResizable(false);
        this.setBackground(new Color(0x410717));
    }

    public void setChangePhoto(ProfilePhotoLinker1 changePhoto) {
        this.changePhoto1 = changePhoto;
    }

    public void setChangePhoto2(ProfilePhotoLinker2 changePhoto2) {
        this.changePhoto2 = changePhoto2;
    }

    @Override
    public void linker(String newName) {
        if (newName != null && !newName.equals(""))
            this.getUserNameText().setText(newName+ " ");
        else
            this.getUserNameText().setText("tuem");
    }

}
