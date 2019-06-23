import java.io.IOException;

public class JPotifyUser {
    JpotifyFrame jpotifyFrame;
    //FirstFrame firstFrame ;

    SaveAccount saveAccount ;
    SignPage signPage;

    public JpotifyFrame getJpotifyFrame() {
        return jpotifyFrame;
    }

    public void setJpotifyFrame(JpotifyFrame jpotifyFrame) {
        this.jpotifyFrame = jpotifyFrame;
    }

    public SaveAccount getSaveAccount() {
        return saveAccount;
    }

    public void setSaveAccount(SaveAccount saveAccount) {
        this.saveAccount = saveAccount;
    }

    public SignPage getSignPage() {
        return signPage;
    }

    public void setSignPage(SignPage signPage) {
        this.signPage = signPage;
    }

    public JPotifyUser() {
        jpotifyFrame = new JpotifyFrame();
        //firstFrame = new FirstFrame();
        saveAccount = new SaveAccount();
        jpotifyFrame.getControlPanel().setCenterPanel1(jpotifyFrame.getCenterPanel());
        jpotifyFrame.getControlPanel().setCenterPanel2(jpotifyFrame.getCenterPanel());
        jpotifyFrame.getControlPanel().setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
        jpotifyFrame.getCenterPanel().getHomePage().setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
        jpotifyFrame.getCenterPanel().getHomePage().setSaveMusic(saveAccount);

        jpotifyFrame.getSearch().getProfileSettings().setNameLinker(saveAccount);
        try {
            saveAccount.loadAccount();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        signPage = new SignPage();
        signPage.setShow(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setChangePhoto2(signPage);
        signPage.setChangeName(jpotifyFrame.getSearch().getProfileSettings());
        signPage.setChangeName2(saveAccount);
        jpotifyFrame.getSearch().getProfileSettings().setJpotifyVisibility(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setSignpageVisibility(signPage);
        jpotifyFrame.getControlPanel().setSaveMusic(saveAccount);
        saveAccount.setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
    }


}
