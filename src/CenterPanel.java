import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel implements ChangeCenterPanel1,ChangeCenterPanel2 ,ChangeCenterPanel3 , ChangeCenterPanel4 , ChangeCenterPanel5 , ChangeCenterPanel6 ,ChangeCenterPanel7 {
    private HomePage homePage;
    private Songs allSongs;
    private Albums albums;
    private SetCurrentSongsAllSongs currentSongsAllSongs;
    private Favorites favorites;
    private SharedPlayListS sharedPlayListS;
    private SharedPlaylistPanelOtherUsers sharedPlaylistPanelOtherUsers;
    private PlayListPanel playlistSongs;

    public void setSetSharedSongsPlay(SetSharedSongsPlay setSharedSongsPlay) {
        this.setSharedSongsPlay = setSharedSongsPlay;
    }

    private SetSharedSongsPlay setSharedSongsPlay;
    private SetCurrentSongsPlaylist setCurrentSongsPlaylist;
    private SetCurrentSongsFavorites setCurrentSongsFavorites;

    public void setSetCurrentSongsFavorites(SetCurrentSongsFavorites setCurrentSongsFavorites) {
        this.setCurrentSongsFavorites = setCurrentSongsFavorites;
    }


    public void setSetCurrentSongsPlaylist(SetCurrentSongsPlaylist setCurrentSongsPlaylist) {
        this.setCurrentSongsPlaylist = setCurrentSongsPlaylist;
    }


    public PlayListPanel getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(PlayListPanel playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public SharedPlayListS getSharedPlayListS() {
        return sharedPlayListS;
    }

    public void setSharedPlayListS(SharedPlayListS sharedPlayListS) {
        this.sharedPlayListS = sharedPlayListS;
    }

    public SetCurrentSongsAllSongs getCurrentSongsAllSongs() {
        return currentSongsAllSongs;
    }

    public Favorites getFavorites() {
        return favorites;
    }

    public void setFavorites(Favorites favorites) {
        this.favorites = favorites;
    }

    public void setCurrentSongsAllSongs(SetCurrentSongsAllSongs currentSongsAllSongs) {
        this.currentSongsAllSongs = currentSongsAllSongs;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public Songs getAllSongs() {
        return allSongs;
    }

    public void setAllSongs(Songs allSongs) {
        this.allSongs = allSongs;
    }


    public CenterPanel() {
        this.setLayout(new CardLayout());
        homePage = new HomePage();
        homePage.setCenterPanel1(this);
        allSongs = new Songs(1000);
        allSongs.setVisible(false);
        albums = new Albums();
        albums.setVisible(false);
        favorites = new Favorites();
        favorites.setVisible(false);
        sharedPlayListS = new SharedPlayListS();
        sharedPlayListS.setVisible(false);
        //playlistSongs = new PlayListPanel();
        //playlistSongs.setVisible(false);
        allSongs.setMatchSongsAndAlbums(albums);
        homePage.setCenterPanel3(this);
        allSongs.setAlbumRemoving(albums);
        this.add(albums);
        this.add(allSongs);
        this.add(homePage);
        this.add(favorites);
        this.add(sharedPlayListS);
        //this.add(playlistSongs);
        homePage.setVisible(true);
//        allSongs.setVisible(false);
    }

    /**
     * changing the centerPanel to allSongs
     */
    @Override
    public void change1() {
//        int last = this.getComponents().length;
//        for (int i = last - 1; i >=0 ; i--) {
//            this.remove(this.getComponent(i));
//        }
//        allSongs = new Songs();

        //////////////////////////
//        allSongs.setVisible(true);
//        homePage.setVisible(false);
        /////////////////////////


//        this.setLayout(new BorderLayout());
//        this.add(allSongs , BorderLayout.CENTER);
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
//        homePage.setVisible(false);
        allSongs.setVisible(true);
        ArrayList<SingleTrack> temp = new ArrayList<>();
        for (int i = 0; i < allSongs.getMusicCounter(); i++) {
            temp.add(allSongs.getTracks().get(i));
        }
        currentSongsAllSongs.setCurrentAllSongs(temp);
//        this.setVisible(true);
    }

    /**
     * changing centerPanel to homePage
     */
    @Override
    public void change2() {
//        int last = this.getComponents().length;
//        for (int i = last - 1; i >= 0; i--) {
//            this.getComponent(i).setVisible(false);
//        }
//        //homePage.setVisible(false);
//        homePage.setVisible(true);
//        int last = this.getComponents().length;
//        for (int i = last - 1; i >=0 ; i--) {
//            this.remove(this.getComponent(i));
//        }
//        homePage = new HomePage();
//        this.setLayout(new BorderLayout());
//        this.add(homePage , BorderLayout.CENTER);
//        allSongs.setVisible(false);
//        homePage.setVisible(true);
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
//        homePage.setVisible(false);
        homePage.setVisible(true);
//        this.setVisible(true);
    }

    /**
     * changing centerPanel to albums
     */
    @Override
    public void change3() {
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
//        homePage.setVisible(false);
        albums.setVisible(true);
    }

    /**
     * showing favoritesPanel
     */
    @Override
    public void change4() {
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        favorites.setVisible(true);
        ArrayList<SingleTrack> trash = new ArrayList<>();
        for (int i = 0; i < favorites.getFavoriteCounter(); i++) {
            trash.add(favorites.getFavorites().get(i).getSingleTrack());
        }
        setCurrentSongsFavorites.setCurrentSongsFavorites(trash);
    }

    /**
     * showing shared playlist
     */
    @Override
    public void change5() {
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        sharedPlayListS.setVisible(true);
    }

    @Override
    public void change6(PlayListPanel playListPanel , ArrayList<SingleTrack> singleTracks) {
        playlistSongs = playListPanel;
        this.add(playlistSongs);
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        playlistSongs.setVisible(true);
        setCurrentSongsPlaylist.setCurrentSongsPlaylist(singleTracks);
    }

    @Override
    public void change7(SharedPlaylistPanelOtherUsers sharedPlaylistPanelOtherUsers) {
        this.sharedPlaylistPanelOtherUsers = sharedPlaylistPanelOtherUsers;
        this.add(this.sharedPlaylistPanelOtherUsers);
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        sharedPlaylistPanelOtherUsers.setVisible(true);
        for (int i = 0; i < sharedPlaylistPanelOtherUsers.getOtherShared().size(); i++) {
            setSharedSongsPlay.setSharedSongsPlay(sharedPlaylistPanelOtherUsers.getOtherShared().get(i));
        }
    }
}