import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

public class Search extends JPanel implements ProfilePhotoLinker1 {
    public JTextField getSearchArea() {
        return searchArea;
    }

    public JButton getSearch() {
        return search;
    }

    public JButton getProfile() {
        return profile;
    }

    public ProfileSettings getProfileSettings() {
        return profileSettings;
    }

    private final int imageSizeSmall = 30;
    private JTextField searchArea;
    private JButton search;
    private JButton profile;
    private ProfileSettings profileSettings;



    public Search() {
        super();
        profileSettings = new ProfileSettings();
        profileSettings.setChangePhoto(this);

        this.setBackground(new Color(0x676767));

        search = new JButton("search");
        search.setOpaque(false);
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/Search.png"));


            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);

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

                profileSettings.setVisible(true);

            }
        });

        try {
            Image img = ImageIO.read(getClass().getResource("/user.png"));

            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);

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


    @Override
    public void linker(File imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, img.SCALE_SMOOTH);
            profile.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            System.out.println(ex);
       }
    }

}
