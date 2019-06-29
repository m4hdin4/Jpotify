import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Favorites extends JPanel implements AddTrackToFavorites , DeleteFromFavorites {
    private ArrayList<Favorite> favorites;

    public int getFavoriteCounter() {
        return favoriteCounter;
    }

    private int favoriteCounter;

    public ArrayList<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Favorites(){
        favorites = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            favorites.add(new Favorite());
            this.add(favorites.get(i));
            favorites.get(i).setVisible(false);
            favorites.get(i).setDeleteFromFavorites(this);
        }
        this.setLayout(new GridLayout(30 , 1));
    }

    @Override
    public void addTrackToFavorites(SingleTrack singleTrack) {
        favorites.get(favoriteCounter).setSingleTrack(singleTrack);
        favorites.get(favoriteCounter).setText(singleTrack.getTrackName());
        favorites.get(favoriteCounter).setVisible(true);
        favoriteCounter++;
    }

    @Override
    public void DeleteFromFavorites(Favorite favorite) {
        favorite.setVisible(false);
        favorites.remove(favorite);
        remove(favorite);
        favoriteCounter--;
        revalidate();
    }
}
