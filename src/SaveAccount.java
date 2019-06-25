import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class SaveAccount implements SaveMusicLinker, Serializable, UsernameLinker2 , ProfileNameLinker ,RemoveMusicLinker,ProfilePhotoSave {


    private String username;
    private ArrayList<String> filesPath;
    private String userImagePath;

    private String localAddress = "C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\saves\\";

    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }

    private ProfilePhotoLinker1 musicLinker;

    private ProfileLoadPicture1 profileLoadPicture1;

    public void setProfileLoadPicture1(ProfileLoadPicture1 profileLoadPicture1) {
        this.profileLoadPicture1 = profileLoadPicture1;
    }

    public void setProfileLoadPicture2(ProfileLoadPicture2 profileLoadPicture2) {
        this.profileLoadPicture2 = profileLoadPicture2;
    }

    private ProfileLoadPicture2 profileLoadPicture2;

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
        userImagePath = "C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\user1.png";
    }

    public void loadAccount() throws IOException, ClassNotFoundException {
        String fileName = String.valueOf(new StringBuilder(localAddress).append(username));
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("loading");
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SaveAccount trash = (SaveAccount) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            profileLoadPicture1.load1(new File(trash.getUserImagePath()));
            profileLoadPicture2.load2(new File(trash.getUserImagePath()));
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
        else {
            autoSave();
        }
    }


    public void addFile(File file) throws IOException {
        filesPath.add(file.getPath());
    }

    public void removeFile(File file) {
        System.out.println(file.getPath());
        filesPath.remove(file.getPath());
    }

    private void autoSave() throws IOException {
        if (username != null && !username.equals("")) {
            System.out.println("saving");
            String fileName = String.valueOf(new StringBuilder(localAddress).append(username));
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            SaveAccount temp = new SaveAccount();
            temp.setByObject(this);
            objectOutputStream.writeObject(temp);
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

    private void setByObject(SaveAccount saveAccount) {
        this.filesPath = saveAccount.getFilesPath();
        this.username = saveAccount.getUsername();
        this.userImagePath = saveAccount.getUserImagePath();
    }

    @Override
    public void linker(File f)  {
        try {
            addFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String fileName = String.valueOf(new StringBuilder(localAddress).append(username));
        File f = new File(fileName);
        f.delete();
        username = s;
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(File f) {
        removeFile(f);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePhoto(File f) {
        String fileName = String.valueOf(new StringBuilder(localAddress).append(username));
        File file = new File(fileName);
        file.delete();
        userImagePath = f.getPath();
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
