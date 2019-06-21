public class Main {

    public static void main(String[] args) throws InterruptedException {
        FirstFrame firstFrame = new FirstFrame();
        SignPage signPage = new SignPage();
        JpotifyFrame jpotifyFrame = new JpotifyFrame();
        signPage.setShow(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setChangePhoto2(signPage);
        signPage.setChangeName(jpotifyFrame.getSearch().getProfileSettings());
        jpotifyFrame.getSearch().getProfileSettings().setJpotifyVisibility(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setSignpageVisibility(signPage);
    }
}
