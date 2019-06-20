import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class FirstFrame extends JFrame {

    public FirstFrame() throws InterruptedException {
        this.setSize(400 , 400);
        JLabel jLabel = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("/logoo.jpg"));
            Image image = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            jLabel.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.setLayout(new BorderLayout());
        this.add(jLabel , BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        setVisible(true);
        TimeUnit.SECONDS.sleep(3);
        this.dispose();
        SignPage signPage = new SignPage();



    }
}
