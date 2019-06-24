
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
        Audio.setMasterOutputVolume(1f);
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
            System.out.println(ex);
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



        myTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jProgressBar.getValue()>=frame){
                    myTimer.stop();
                    jProgressBar.setValue(0);
                }
                else
                    jProgressBar.setValue(jProgressBar.getValue() + 1);}

        });
        jProgressBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    musicPlayer.close1();
                    Thread t = new Thread(new Runnable() {
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
                    t.start();
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
                            Image img = ImageIO.read(getClass().getResource("/volume-up.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        soundSlider.setValue(soundSliderValue);
                    } else {


                        try {
                            Image img = ImageIO.read(getClass().getResource("/volume-down.png"));
                            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
                            soundIcon.setIcon(new ImageIcon(image));
                        } catch (Exception ex) {
                            System.out.println(ex);
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
                        System.out.println(ex);
                    }

                } else if (soundSlider.getValue() > 0) {
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
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
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
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
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
                Audio.setMasterOutputVolume((float)soundSlider.getValue()/100);
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


}
