import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignPage extends JFrame {


    public SignPage() {
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        JButton profilePhoto = new JButton();
        profilePhoto.setOpaque(false);
        profilePhoto.setContentAreaFilled(false);
        profilePhoto.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/user3.png"));
            Image image = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            profilePhoto.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        profilePhoto.addActionListener(new ActionListener() {
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
                        Image image = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        profilePhoto.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }
        });
        this.add(profilePhoto, BorderLayout.NORTH);
        this.setBackground(new Color(0xA2A2A2));
        JPanel down = new JPanel();
        down.setBackground(new Color(0xAEAEAE));
        down.setLayout(new GridLayout(2,1));
        JLabel selectName = new JLabel("Enter Your Username : ");
        selectName.setOpaque(false);
        JTextField nameField = new JTextField();
        down.add(selectName );
        down.add(nameField);

        JButton signIn = new JButton("sign in ");
        signIn.setFont(new Font("bold" , Font.BOLD , 15));
        signIn.setForeground(new Color(0xFFFFFF));
        signIn.setBackground(new Color(0));
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JpotifyFrame jpotifyFrame = new JpotifyFrame();
            }
        });
        JButton signOut = new JButton("sign out");
        signOut.setFont(new Font("bold" , Font.BOLD , 15));
        signOut.setForeground(new Color(0xffffff));
        signOut.setBackground(new Color(0));
        JPanel sign = new JPanel();
        sign.setLayout(new GridLayout(1,2));
        sign.add(signIn );
        sign.add(signOut);
        this.add(down , BorderLayout.CENTER);
        this.add(sign , BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setTitle("Profile");
        this.setVisible(true);
        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);

    }
}
