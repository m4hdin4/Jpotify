import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

public interface SetPlayingSongProfile {
    void setPlayingSongProfile (File f) throws InvalidDataException, IOException, UnsupportedTagException;
}
