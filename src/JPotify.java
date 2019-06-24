



public class JPotify implements SignNewUser{
    FirstFrame firstFrame ;
    JPotifyUser jPotifyUser ;
    public JPotify() {
        try {
            firstFrame = new FirstFrame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jPotifyUser = new JPotifyUser();
        jPotifyUser.getJpotifyFrame().getSearch().getProfileSettings().setUser(this);
    }

    @Override
    public void newUser() {
        jPotifyUser.getJpotifyFrame().dispose();
        jPotifyUser.getSignPage().dispose();
        jPotifyUser = new JPotifyUser();
        jPotifyUser.getJpotifyFrame().getSearch().getProfileSettings().setUser(this);
    }
}
