import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public interface CounterHandler {
    void handle(SingleTrack st) throws InvalidDataException, IOException, UnsupportedTagException;
}
