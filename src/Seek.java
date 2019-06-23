import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * for creating a new file with the current time that user selected
 */
public class Seek {
    File file;
    public Seek(File file , int second) throws IOException, InvalidDataException, UnsupportedTagException {
        this.file = file;
        Mp3File mp3File = new Mp3File(this.file);
        byte[] bytes = (byte[]) Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        int byteNum = (int) (mp3File.getLength() * second / (mp3File.getLengthInMilliseconds() / 1000));
        this.file = new File(file.getParent() , "seperate.mp3");
        FileOutputStream fos = new FileOutputStream(this.file);
        fos.write(bytes , byteNum , bytes.length - byteNum -1);
    }

    /**
     *
     * @return return's the new File
     */
    public File getFile() {
        return file;
    }
}
