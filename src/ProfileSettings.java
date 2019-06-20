import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class ProfileSettings extends JFrame {
    private final int WIDTH = 210, HEIGHT = 180;
    private final String WINDOWS_TITLE = "Profile";


    public ProfileSettings (){
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setVisible(false);
        this.setLayout(new GridLayout(2 , 1));
        JPanel photo = new JPanel();
        photo.setLayout(new FlowLayout());
        JLabel userPhoto = new JLabel("    \n");
        userPhoto.setOpaque(false);

        try {
            Image img = ImageIO.read(getClass().getResource("/user1.png"));
            Image image = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
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
        photo.setBackground(new Color(0x636363));
        photo.add(userPhoto);
        photo.add(camera);
        this.add(photo);

        JPanel userName = new JPanel();
        userName.setBackground(new Color(0x595B5D));
        userName.setLayout(new GridLayout(2,1));
        JTextField name = new JTextField();
        name.setOpaque(false);
        name.setText("mohammad");

        JButton changeName = new JButton("Change Your Name");
        changeName.setBackground(new Color(0));
        changeName.setForeground(new Color(0xFFFFFF));
        userName.add(name);
        userName.add(changeName);
        this.setLocationRelativeTo(null);
        this.add(userName);
        this.setResizable(false);
        this.setBackground(new Color(0x410717));
    }
    public void setVisible(){
        this.setVisible(true);
    }

}
