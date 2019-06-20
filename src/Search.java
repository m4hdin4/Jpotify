import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JPanel  {
    JTextField searchArea;
    JButton search;
    JButton profile;

    public Search() {
        super();
        this.setBackground(new Color(0x676767));

        search = new JButton("search");
        search.setOpaque(false);
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("/Search.png"));
            Image image = img.getScaledInstance(ControlPanel.imageSizeSmall, ControlPanel.imageSizeSmall, Image.SCALE_SMOOTH);
            search.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        profile = new JButton("  Profile      ");
        profile.setFont(new Font("fuck", Font.BOLD, 15));
        profile.setOpaque(false);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        profile.setToolTipText("Click to change profile information");
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileSettings profileSettings = new ProfileSettings();
                profileSettings.setVisible();

            }
        });

        try {
            Image img = ImageIO.read(getClass().getResource("/user.png"));
            Image image = img.getScaledInstance(ControlPanel.imageSizeSmall, ControlPanel.imageSizeSmall, Image.SCALE_SMOOTH);
            profile.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        setLayout(new BorderLayout());
        searchArea = new JTextField();
        searchArea.setBackground(new Color(0xBBBBBB));
        add(profile, BorderLayout.WEST);
        add(searchArea, BorderLayout.CENTER);
        add(search, BorderLayout.EAST);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searched;
                searched = searchArea.getText();
                searchArea.setText("");
                System.out.println(searched);
            }
        });
    }



}
