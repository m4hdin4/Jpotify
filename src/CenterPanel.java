import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel implements ChangeCenterPanel1,ChangeCenterPanel2 ,ChangeCenterPanel3 , ChangeCenterPanel4 , ChangeCenterPanel5 {
    private HomePage homePage;
    private Songs allSongs;
    private Albums albums;
    private SetCurrentSongsAllSongs currentSongsAllSongs;
    private Favorites favorites;
    private SharedPlayListS sharedPlayListS;

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
        allSongs.setMatchSongsAndAlbums(albums);
        homePage.setCenterPanel3(this);
        allSongs.setAlbumRemoving(albums);
        this.add(albums);
        this.add(allSongs);
        this.add(homePage);
        this.add(favorites);
        this.add(sharedPlayListS);
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
        currentSongsAllSongs.setCurrentAllSongs(allSongs);
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

    @Override
    public void change4() {
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        favorites.setVisible(true);
    }

    @Override
    public void change5() {
        int last = this.getComponents().length;
        for (int i = last - 1; i >= 0; i--) {
            this.getComponent(i).setVisible(false);
        }
        sharedPlayListS.setVisible(true);
    }
}