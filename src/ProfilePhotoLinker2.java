
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

/**
 * handling changing the profile image
 */
public interface ProfilePhotoLinker2 {
    void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException;
}
