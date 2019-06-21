import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface PhotoAndMusicLinker {
    void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException;
}
