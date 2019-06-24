import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class JPotifyUser implements Serializable {
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
        try {
            jpotifyFrame = new JpotifyFrame();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        signPage = new SignPage();
        saveAccount = new SaveAccount();
        //firstFrame = new FirstFrame();
        jpotifyFrame.getControlPanel().setCenterPanel1(jpotifyFrame.getCenterPanel());
        jpotifyFrame.getControlPanel().setCenterPanel2(jpotifyFrame.getCenterPanel());
        jpotifyFrame.getControlPanel().setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
        jpotifyFrame.getCenterPanel().getHomePage().setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
        jpotifyFrame.getCenterPanel().getHomePage().setSaveMusic(saveAccount);
//        for (int i = 0; i < jpotifyFrame.getCenterPanel().getAllSongs().getTracks().length; i++) {
//            jpotifyFrame.getCenterPanel().getAllSongs().getTracks()[i].setPlaySingleTrack(jpotifyFrame.getPlayMusic());
//        }

        jpotifyFrame.getSearch().getProfileSettings().setNameLinker(saveAccount);
        try {
            saveAccount.loadAccount();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        signPage.setShow(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setChangePhoto2(signPage);
        signPage.setChangeName(jpotifyFrame.getSearch().getProfileSettings());
        signPage.setChangeName2(saveAccount);
        jpotifyFrame.getSearch().getProfileSettings().setJpotifyVisibility(jpotifyFrame);
        jpotifyFrame.getSearch().getProfileSettings().setSignpageVisibility(signPage);
        jpotifyFrame.getControlPanel().setSaveMusic(saveAccount);
        saveAccount.setMusicLinker(jpotifyFrame.getCenterPanel().getAllSongs());
        for (int i = 0; i < jpotifyFrame.getCenterPanel().getAllSongs().getTracks().size(); i++) {
            jpotifyFrame.getCenterPanel().getAllSongs().getTracks().get(i).setRemoveMusicLinker(saveAccount);
        }
        jpotifyFrame.getSearch().getProfileSettings().setProfilePhotoSave(saveAccount);
        saveAccount.setProfileLoadPicture1(jpotifyFrame.getSearch());
        saveAccount.setProfileLoadPicture2(jpotifyFrame.getSearch().getProfileSettings());
    }


}
