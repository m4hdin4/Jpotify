import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class SaveAccount implements SaveMusicLinker, Serializable, UsernameLinker2 , ProfileNameLinker {


    private String username;
    private ArrayList<String> filesPath;
    private String userImagePath;


    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }

    private ProfilePhotoLinker1 musicLinker;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public ArrayList<String> getFilesPath() {
        return filesPath;
    }

    public SaveAccount() {
        //username = "tuem";
        filesPath = new ArrayList<>();
    }

    public void loadAccount() throws IOException, ClassNotFoundException {
        String fileName = String.valueOf(new StringBuilder("C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\saves\\").append(username));
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("loading");
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SaveAccount trash = (SaveAccount) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            for (int i = 0; i < trash.getFilesPath().size(); i++) {
                try {
                    File fi = new File(trash.getFilesPath().get(i));
                    musicLinker.linker(fi);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
            }
            setByObject(trash);

        }
    }


    public void addFile(File file) throws IOException {
        filesPath.add(file.getPath());
    }

    public void removeFile(File file) {
        filesPath.remove(file.getPath());
    }

    private void autoSave() throws IOException {
        System.out.println("saving");
        String fileName = String.valueOf(new StringBuilder("C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\saves\\").append(username));
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private void setByObject(SaveAccount saveAccount) {
        this.filesPath = saveAccount.getFilesPath();
        this.username = saveAccount.getUsername();
        this.userImagePath = saveAccount.getUserImagePath();
    }

    @Override
    public void linker(File f) throws IOException {
        addFile(f);
        autoSave();
    }

    @Override
    public void linker(String s) {
        if (s != null && !s.equals("")) {
            username = s;
            try {
                loadAccount();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            username = "tuem";
            try {
                loadAccount();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void nameLinker(String s) {
        String fileName = String.valueOf(new StringBuilder("C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\saves\\").append(username));
        File f = new File(fileName);
        f.delete();
        username = s;
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
