import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel implements ChangeCenterPanel1,ChangeCenterPanel2 {
    private HomePage homePage;
    private Songs allSongs;

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



    public CenterPanel (){
        this.setLayout(new BorderLayout());
        homePage = new HomePage();
        homePage.setCenterPanel1(this);
        allSongs = new Songs();
        allSongs.setVisible(false);
        this.add(allSongs , BorderLayout.PAGE_START);
        this.add(homePage , BorderLayout.CENTER);
        homePage.setVisible(true);
//        allSongs.setVisible(false);
    }

   @Override
    public void change1() {
//        int last = this.getComponents().length;
//        for (int i = last - 1; i >=0 ; i--) {
//            this.remove(this.getComponent(i));
//        }
//        allSongs = new Songs();
        allSongs.setVisible(true);
        homePage.setVisible(false);
//        this.setLayout(new BorderLayout());
//        this.add(allSongs , BorderLayout.CENTER);
//        int last = this.getComponents().length;
//        for (int i = last - 1; i >= 0; i--) {
//           this.getComponent(i).setVisible(false);
//        }
////        homePage.setVisible(false);
//        allSongs.setVisible(true);

    }

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
        allSongs.setVisible(false);
        homePage.setVisible(true);
    }
}
