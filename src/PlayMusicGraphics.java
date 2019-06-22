import javafx.scene.control.Slider;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javazoom.jl.player.*;
import javazoom.jl.decoder.*;
import javazoom.jl.converter.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class PlayMusicGraphics extends JPanel {
    public static final int imageSizeSmall = 30;
    public static final int imageSizeBig = 60;

    public void setInput(FileInputStream inputStreamStream) {
        this.inputStreamStream = inputStreamStream;
    }

    private FileInputStream inputStreamStream;

    private JButton playPauseBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton repeatBtn;
    private JButton shuffleBtn;
    private JLabel timePassed;
    private JLabel timeSong;

    /*public SongProfile getSongData() {
        return songData;
    }*/


    //private SongProfile songData;

    private JPanel centerButtons;
    private JSlider musicSlider;
    private JPanel soundBar;
    private JButton soundIcon;
    private JSlider soundSlider;
    private int soundSliderValue;


    private int shuffleCounter;
    private int playPauseCounter ;
    private int repeatCounter;
    private int soundCounter;
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;

    // the player actually doing all the work
    private Player player;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;

    public void setInputStream(final InputStream inputStreamStreamStream) throws JavaLayerException {
        this.player = new Player(inputStreamStreamStream);
    }

    public PlayMusicGraphics() {
        //this.player = new Player(inputStreamStreamStream);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x636363));
        centerButtons = new JPanel();
        centerButtons.setOpaque(false);
        centerButtons.setLayout(new FlowLayout());
        //songData = new SongProfile("", "");
        //this.add(songData, BorderLayout.WEST);
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
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            shuffleBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        shuffleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shuffleCounter % 2 == 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/shuffled.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        shuffleBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    shuffleCounter++;
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unshuffled.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
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
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
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
            Image img = ImageIO.read(getClass().getResource("/play.png"));
            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            playPauseBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            inputStreamStream = new FileInputStream("C:\\Users\\mm\\Desktop\\Quera\\Jslider\\src\\dd.mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playPauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(playPauseCounter);
                if (playPauseCounter % 2 != 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/play.png"));
                        Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/pause.png"));
                        Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
                try {
                    setInputStream(inputStreamStream);

                    // start playing


                    if (playPauseCounter % 2 != 0 ) {
                        System.out.println("pa");
                        pause();
                        playPauseCounter++;
                    }
                    else  {

                        play();
                        System.out.println("res");
                        playPauseCounter++;
                    }


                    //resume();

                } catch (final Exception r) {
                    throw new RuntimeException(r);
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
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
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
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            repeatBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        repeatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (repeatCounter % 2 == 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/repeat.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        repeatBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    repeatCounter++;
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unrepeat.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
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
        this.add(centerButtons, BorderLayout.CENTER);
        musicSlider = new JSlider();
        musicSlider.setOpaque(false);
        musicSlider.setMinimum(0);
        musicSlider.setValue(0);
        musicSlider.setBorder(new EmptyBorder(0, 0, 10, 0));
        this.add(musicSlider, BorderLayout.SOUTH);
        soundBar = new JPanel();
        soundBar.setLayout(new BorderLayout());
        soundBar.setOpaque(false);
        soundIcon = new JButton();
        soundIcon.setOpaque(false);
        soundIcon.setBackground(new Color(0x636363));
        try {
            Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            soundIcon.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        soundSlider = new JSlider();
        soundSlider.setOpaque(false);

        soundIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (soundCounter % 2 == 0 && soundSliderValue != 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    soundSlider.setValue(0);
                    soundCounter++;
                } else if (soundSliderValue != 0) {
                    if (soundSliderValue <= 50) {
                        try {
                            Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        soundSlider.setValue(soundSliderValue);
                    } else {
                        try {
                            Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        soundSlider.setValue(soundSliderValue);
                    }
                    soundCounter++;
                }
            }
        });
        soundSlider = new JSlider();
        soundSlider.setOpaque(false);
        soundSlider.setMaximum(100);
        soundSlider.setMinimum(0);
        soundSlider.setValue(50);
        soundSliderValue = 50;
        soundSlider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soundSliderValue = soundSlider.getValue();
                if (soundSliderValue > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else if (soundSliderValue > 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                soundSliderValue = soundSlider.getValue();
                if (soundSliderValue > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else if (soundSliderValue > 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                soundSliderValue = soundSlider.getValue();
                if (soundSliderValue > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else if (soundSliderValue > 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                soundSliderValue = soundSlider.getValue();
                if (soundSliderValue > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else if (soundSliderValue > 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        soundBar.add(soundIcon, BorderLayout.CENTER);
        soundBar.add(soundSlider, BorderLayout.EAST);
        this.add(soundBar, BorderLayout.EAST);
    }

    /**
     * @param timePassed
     */
    public void setTimePassed(String timePassed) {
        this.timePassed.setText(timePassed);
    }

    /**
     * @param timeSong
     */
    public void setSongTime(String timeSong) {
        this.timeSong.setText(timeSong);
    }

    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }


    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }


    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }


    /**
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    public void play() throws JavaLayerException {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }


}
