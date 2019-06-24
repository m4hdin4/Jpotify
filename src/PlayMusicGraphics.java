
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.Slider;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javazoom.jl.player.*;
import javazoom.jl.decoder.*;
import javazoom.jl.converter.*;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;


public class PlayMusicGraphics extends JPanel  {
    public static final int imageSizeSmall = 30;
    public static final int imageSizeBig = 60;

    public void setInput(FileInputStream inputStreamStream) {
        this.inputStream = inputStreamStream;
    }

    private FileInputStream inputStream;

    private JButton playPauseBtn;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton repeatBtn;
    private JButton shuffleBtn;
    private JLabel timePassed;
    private JLabel timeSong;
    private Timer myTimer;
    private Mp3File mp3File;

    public SongProfile getSongData() {
        return songData;
    }
    private SongProfile songData;
    private JPanel centerButtons;
    private JProgressBar jProgressBar;
    private JPanel soundBar;
    private JButton soundIcon;
    private JSlider soundSlider;
    private int soundSliderValue;
    private Thread thread;

    private int shuffleCounter;
    private int playPauseCounter;
    private int repeatCounter;
    private int soundCounter;
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private long frameCount;
    private int frame;
    private MusicPlayer musicPlayer;

    public void setPlayNext(PlayNext playNext) {
        this.playNext = playNext;
    }

    private PlayNext playNext;
    private PlayLast playLast;

    public void setPlayLast(PlayLast playLast) {
        this.playLast = playLast;
    }

    File file = new File("C:\\Users\\BPTEC-32338485\\Desktop\\jpotifyMusics\\Sasy.mp3");
    //private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public PlayMusicGraphics() throws JavaLayerException, FileNotFoundException {


        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x636363));
        centerButtons = new JPanel();
        centerButtons.setOpaque(false);
        centerButtons.setLayout(new FlowLayout());

        songData = new SongProfile("", "");
        this.add(songData, BorderLayout.WEST);

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
            ex.printStackTrace();
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
                        ex.printStackTrace();
                    }
                    shuffleCounter++;
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unshuffled.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        shuffleBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
            ex.printStackTrace();
        }

        try {
            mp3File = new Mp3File(file);
            frame = (int)mp3File.getLengthInSeconds();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }



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






        if (repeatCounter % 2 != 0 ){
            if (jProgressBar.getValue()>=jProgressBar.getMaximum()-10){
                jProgressBar.setValue(0);
                System.out.println("fuck");
                try {
                    musicPlayer = new MusicPlayer(new FileInputStream(file));
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                musicPlayer.play();
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                thread.start();

            }
        }






        jProgressBar = new JProgressBar( 0 ,frame);
        jProgressBar.setSize(800 ,10);
        jProgressBar.setOpaque(false);
        jProgressBar.setMinimum(0);
        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        musicPlayer = new MusicPlayer(new FileInputStream(file));

        playPauseBtn = new JButton();
        playPauseBtn.setOpaque(false);
        playPauseBtn.setContentAreaFilled(false);
        playPauseBtn.setBorderPainted(false);
        try {

            Image img = ImageIO.read(getClass().getResource("/play.png"));

            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            playPauseBtn.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /**
         * changes the image of playPause Button by every click
         */
        playPauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (playPauseCounter % 2 != 0) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/play.png"));
                        Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/pause.png"));
                        Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
                        playPauseBtn.setIcon(new ImageIcon(image));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }



                if (playPauseCounter % 2 != 0) {

                    musicPlayer.pause();
                    myTimer.stop();
                    playPauseCounter++;
                } else {
                    try {
                        musicPlayer.play();
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                    myTimer.start();
                    playPauseCounter++;
                }

            }

        });


/**
 * timer used for jprogressbar and playing music
 */
        myTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jProgressBar.getValue()>=jProgressBar.getMaximum()){
                    myTimer.stop();
                    jProgressBar.setValue(0);
                    if(repeatCounter %2!=0) {

                            musicPlayer.close1();
                        try {
                            musicPlayer= new MusicPlayer(new FileInputStream(file));
                            musicPlayer.play();
                            myTimer.start();
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }

                    }
                }
                else
                    jProgressBar.setValue(jProgressBar.getValue() + 1);}

        });
        jProgressBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    musicPlayer.close1();
                      thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                synchronized (musicPlayer){
                                Seek seek = new Seek(file , (e.getX()*jProgressBar.getMaximum())/jProgressBar.getWidth());
                                musicPlayer = new MusicPlayer(new FileInputStream(seek.getFile()));
                                musicPlayer.play();
                                if(!myTimer.isRunning()){
                                    myTimer.start();
                                    Image img = ImageIO.read(getClass().getResource("/pause.png"));
                                    Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
                                    playPauseBtn.setIcon(new ImageIcon(image));
                                    playPauseCounter++;

                                }
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    thread.start();



                int mouseX = e.getX();
                int progressBarVal = (int)Math.round(((double)mouseX / (double)jProgressBar.getWidth()) * jProgressBar.getMaximum());
                jProgressBar.setValue(progressBarVal);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        jProgressBar.setBorder(new EmptyBorder(0, 0, 10, 0));
        this.add(jProgressBar, BorderLayout.SOUTH);


        centerButtons.add(lastBtn);
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
            ex.printStackTrace();
        }
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playNext.next(file);
            }
        });
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
            ex.printStackTrace();
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
                        ex.printStackTrace();
                    }
                    repeatCounter++;
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/unrepeat.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        repeatBtn.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
            ex.printStackTrace();
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
                        ex.printStackTrace();
                    }
                    soundSlider.setValue(0);
                    soundCounter++;

                } else if (soundSliderValue != 0) {
                    if (soundSliderValue <= 50) {


                        try {
                            Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        soundSlider.setValue(soundSliderValue);
                    } else {


                        try {
                            Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    soundSlider.setValue(soundSliderValue);
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
        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Audio.setMasterOutputVolume((float) soundSlider.getValue()/100);
                if (soundSlider.getValue() != 0)
                    soundSliderValue = soundSlider.getValue();
                if (soundSlider.getValue() > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (soundSlider.getValue() > 0) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        soundSlider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
                soundSliderValue = soundSlider.getValue();

                if (soundSliderValue > 50) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                } else if (soundSliderValue > 0) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
                soundSliderValue = soundSlider.getValue();

                if (soundSliderValue > 50) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (soundSliderValue > 0) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
                soundSliderValue = soundSlider.getValue();

                if (soundSliderValue > 50) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }





                } else if (soundSliderValue > 0) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
                soundSliderValue = soundSlider.getValue();
                if (soundSliderValue > 50) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (soundSliderValue > 0) {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {

                    try {
                        Image img = ImageIO.read(getClass().getResource("/volume-off.png"));
                        Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                        soundIcon.setIcon(new ImageIcon(image));
                    } catch (Exception ex) {
                        ex.printStackTrace();
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


}
