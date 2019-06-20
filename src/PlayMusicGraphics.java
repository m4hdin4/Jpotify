import javafx.scene.control.Slider;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMusicGraphics extends JPanel {
    public static final int imageSizeSmall = 30;
    public static final int imageSizeBig = 60;

    private JButton playPauseBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton repeatBtn;
    private JButton shuffleBtn;
    private JLabel timePassed;
    private JLabel timeSong;
    private JPanel songData;
    private JLabel songAuthorName;
    private JLabel songAlbumName;
    private JPanel centerButtons;
    private JSlider musicSlider;
    private JPanel soundBar;
    private JButton soundIcon;
    private JSlider soundSlider;
    private int shuffleCounter;
    private int playPauseCounter;
    private int repeatCounter;



    public PlayMusicGraphics(){
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x636363));
        centerButtons = new JPanel();
        centerButtons.setOpaque(false);
        centerButtons.setLayout(new FlowLayout());
        songData = new JPanel();
        songData.setOpaque(false);
        songData.setLayout(new BorderLayout());
        songAuthorName = new JLabel("Author name");
        songAuthorName.setOpaque(false);
        songAlbumName = new JLabel("Album name");
        songAuthorName.setFont(new Font("BOLD" , Font.BOLD , 20));
        songAlbumName.setOpaque(false);
        songData.add(songAuthorName , BorderLayout.NORTH);
        songData.add(songAlbumName , BorderLayout.CENTER);
        this.add(songData , BorderLayout.WEST);
        JLabel space = new JLabel("                              ");
        space.setOpaque(false);
        centerButtons.add(space);
        timePassed = new JLabel("0:00");
        timePassed.setEnabled(false);
        timePassed.setOpaque(false);
        centerButtons.add(timePassed);
        shuffleBtn = new JButton();
        shuffleBtn.setOpaque(false);
        shuffleBtn.setContentAreaFilled(false);
        shuffleBtn.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/unshuffled.png"));
            Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
            shuffleBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        shuffleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shuffleCounter % 2 == 0){
                    try {
                        Image img = ImageIO.read(getClass().getResource("/shuffled.png"));
                        Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
                        shuffleBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    shuffleCounter++;
                }
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unshuffled.png"));
                        Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
                        shuffleBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    shuffleCounter++;
                }
            }
        });
        centerButtons.add(shuffleBtn);
        lastBtn = new JButton();
        lastBtn.setOpaque(false);
        lastBtn.setContentAreaFilled(false);
        lastBtn.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/last-track.png"));
            Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
            lastBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        centerButtons.add(lastBtn);
        playPauseBtn = new JButton();
        playPauseBtn.setOpaque(false);
        playPauseBtn.setContentAreaFilled(false);
        playPauseBtn.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/pause.png"));
            Image image = img.getScaledInstance(imageSizeBig , imageSizeBig ,Image.SCALE_SMOOTH);
            playPauseBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        playPauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playPauseCounter % 2 == 0){
                    try {
                        Image img = ImageIO.read(getClass().getResource("/play.png"));
                        Image image = img.getScaledInstance(imageSizeBig , imageSizeBig ,Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    playPauseCounter++;
                }
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/pause.png"));
                        Image image = img.getScaledInstance(imageSizeBig , imageSizeBig ,Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    playPauseCounter++;
                }
            }
        });
        centerButtons.add(playPauseBtn);
        nextBtn = new JButton();
        nextBtn.setOpaque(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/next-track.png"));
            Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
            nextBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        centerButtons.add(nextBtn);
        repeatBtn = new JButton();
        repeatBtn.setOpaque(false);
        repeatBtn.setContentAreaFilled(false);
        repeatBtn.setBorderPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/unrepeat.png"));
            Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
            repeatBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        repeatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (repeatCounter % 2 == 0){
                    try {
                        Image img = ImageIO.read(getClass().getResource("/repeat.png"));
                        Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
                        repeatBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    repeatCounter++;
                }
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unrepeat.png"));
                        Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
                        repeatBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    repeatCounter++;
                }
            }
        });
        centerButtons.add(repeatBtn);
        timeSong = new JLabel("0:00");
        timeSong.setEnabled(false);
        timeSong.setOpaque(false);
        centerButtons.add(timeSong);
        this.add(centerButtons , BorderLayout.CENTER);
        musicSlider = new JSlider();
        musicSlider.setOpaque(false);
        musicSlider.setBorder(new EmptyBorder(0,0,10,0));
        this.add(musicSlider , BorderLayout.SOUTH);
        soundBar = new JPanel();
        soundBar.setLayout(new BorderLayout());
        soundBar.setOpaque(false);
        soundIcon = new JButton();
        soundIcon.setOpaque(false);
        soundIcon.setBackground(new Color(0x636363));
        try {
            Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
            Image image = img.getScaledInstance(imageSizeSmall , imageSizeSmall ,Image.SCALE_SMOOTH);
            soundIcon.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        soundSlider = new JSlider();
        soundSlider.setOpaque(false);
        soundBar.add(soundIcon , BorderLayout.CENTER);
        soundBar.add(soundSlider , BorderLayout.EAST);
        this.add(soundBar , BorderLayout.EAST);
    }

    /**
     *
     * @param timePassed
     */
    public void setTimePassed(String timePassed){
        this.timePassed.setText(timePassed);
    }

    /**
     *
     * @param timeSong
     */
    public void setSongTime(String timeSong){
        this.timeSong.setText(timeSong);
    }
}
