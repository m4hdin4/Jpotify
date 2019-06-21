import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

public interface ProfilePhotoLinker1 {
    void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException;
}
