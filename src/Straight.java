import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Straight {
    public static void main(String[] args) throws IOException {
        new MainWindow();
    }
}

class MainWindow extends JFrame {

    MainPanel panel;

    public MainWindow () throws IOException {
        super("straight");

        panel = new MainPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(800, 600));

        this.setLocation(100, 60);

        this.pack();

        this.setVisible(true);

        this.add(panel);

        Timer timer = new Timer(100, new PaintPanelListener(panel));
        timer.start();
    }
}

class PaintPanelListener implements ActionListener {

    JPanel panel;
    int x = 0;
    int y = 0;

    public PaintPanelListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println((new SimpleDateFormat("HH:mm:ss")).format(new Date()));
        x += 10; y += 10;
        ((MainPanel) panel).repaintImage(x, y);
    }
}

class MainPanel extends JPanel {
    BufferedImage image;

    public MainPanel() {
        super();

        try {
            image = ImageIO.read(new File("duck.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(image, 0, 0, this);
    }

    public void repaintImage(int x, int y) {
        this.getGraphics().setColor(Color.GREEN);
        this.getGraphics().fillRect(0, 0, 800, 600);
        this.getGraphics().drawImage(image, x, y, this);
    }
}