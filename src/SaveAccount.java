import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveAccount implements SaveMusicLinker, Serializable, UsernameLinker2 , ProfileNameLinker ,RemoveMusicLinker
        ,ProfilePhotoSave , AddToFavoritesSave , RemoveFromFavoritesSave , AddNewPlaylistSave , AddNewTrackToPlaylistSave , DeletePlaylistSave , DeleteTrackFromPlaylistSave {


    private String username;
    private ArrayList<String> filesPath;
    private ArrayList<String> favoritesPath;
    private HashMap<String , ArrayList<String>> playLists;
    private String userImagePath;

    public HashMap<String, ArrayList<String>> getPlayLists() {
        return playLists;
    }

    private String saveMusicAddress = "C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\saves\\saveMusics";

    public ArrayList<String> getFavoritesPath() {
        return favoritesPath;
    }

    public void setFavoritesPath(ArrayList<String> favoritesPath) {
        this.favoritesPath = favoritesPath;
    }

    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }

    private ProfilePhotoLinker1 musicLinker;

    private ProfileLoadPicture1 profileLoadPicture1;

    private PlaylistLoadAddingPlaylist playlistLoadAddingPlaylist;
    private PlaylistLoadAddingTrackToPlaylist playlistLoadAddingTrackToPlaylist;

    public void setPlaylistLoadAddingTrackToPlaylist(PlaylistLoadAddingTrackToPlaylist playlistLoadAddingTrackToPlaylist) {
        this.playlistLoadAddingTrackToPlaylist = playlistLoadAddingTrackToPlaylist;
    }


    public void setPlaylistLoadAddingPlaylist(PlaylistLoadAddingPlaylist playlistLoadAddingPlaylist) {
        this.playlistLoadAddingPlaylist = playlistLoadAddingPlaylist;
    }

    public void setGetFavoritesFromFile(GetFavoritesFromFile getFavoritesFromFile) {
        this.getFavoritesFromFile = getFavoritesFromFile;
    }

    private GetFavoritesFromFile getFavoritesFromFile;

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
        filesPath = new ArrayList<>();
        favoritesPath = new ArrayList<>();
        playLists= new HashMap<>();
        userImagePath = "C:\\Users\\BPTEC-32338485\\Desktop\\Jpotify\\src\\user1.png";
    }

    public void loadAccount() throws IOException, ClassNotFoundException {
        String fileName = String.valueOf(new StringBuilder(saveMusicAddress).append(username));
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
            for (int i = 0; i < trash.getFavoritesPath().size(); i++) {
                File temp = new File(trash.getFavoritesPath().get(i));
                getFavoritesFromFile.getFavoritesFromFile(temp);
            }
            for (String name : trash.getPlayLists().keySet()){
                playlistLoadAddingPlaylist.playlistLoadAddingPlaylist(name);
                for (int i = 0; i < trash.getPlayLists().get(name).size(); i++) {
                    playlistLoadAddingTrackToPlaylist.playlistLoadAddingTrackToPlaylist(name , trash.getPlayLists().get(name).get(i));
                }
            }
            setByObject(trash);
            autoSave();
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
        favoritesPath.remove(file.getPath());
    }

    private void autoSave() throws IOException {
        if (username != null && !username.equals("")) {
            System.out.println("saving");
            String fileName = String.valueOf(new StringBuilder(saveMusicAddress).append(username));
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
        this.favoritesPath = saveAccount.getFavoritesPath();
        this.username = saveAccount.getUsername();
        this.userImagePath = saveAccount.getUserImagePath();
        this.playLists = saveAccount.getPlayLists();
    }
    /**
     * saving the adding file immediately save
     */
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

    /**
     * handle the user name in singing page
     */
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

    /**
     * handling the input user name and loading the last file
     */
    @Override
    public void nameLinker(String s) {
        String fileName = String.valueOf(new StringBuilder(saveMusicAddress).append(username));
        File f = new File(fileName);
        f.delete();
        username = s;
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * making single tracks removable
     */
    @Override
    public void remove(File f) {
        removeFile(f);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loading the last profileImage
     */
    @Override
    public void savePhoto(File f) {
        String fileName = String.valueOf(new StringBuilder(saveMusicAddress).append(username));
        File file = new File(fileName);
        file.delete();
        userImagePath = f.getPath();
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToFavoritesSave(File f) {
        favoritesPath.add(f.getPath());
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromFavoritesSave(File f) {
        favoritesPath.remove(f.getPath());
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewPlaylistSave(String name) {
        ArrayList<String> temp = new ArrayList<>();
        playLists.put(name , temp);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewTrackToPlaylistSave(String name, File f) {
        playLists.get(name).add(f.getPath());
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylistSave(String name) {
        playLists.remove(name);
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrackFromPlaylistSave(String name , File f) {
        playLists.get(name).remove(f.getPath());
        try {
            autoSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
