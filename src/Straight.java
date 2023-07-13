import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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

        panel.addComponentListener(new MainPanelListener());

        Timer timer = new Timer(100, new PaintPanelListener(panel));
        timer.start();
    }
}

class PaintPanelListener implements ActionListener {

    JPanel panel;

    public PaintPanelListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((MainPanel) panel).repaintImage();
    }
}

class MainPanelListener extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        ((MainPanel) e.getComponent()).init = false;
    }
}

class MainPanel extends JPanel {
    BufferedImage image;
    int x, y, dx, dy;
    boolean init;

    public MainPanel() {
        super();

        init = false;

        try {
            image = ImageIO.read(new File("duck.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDimensions() {
        x = 0; y = 0;
        dx = getWidth() / 40;
        dy = getHeight() / 40;
        init = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!init) {
            initDimensions();
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, x, y, this);
        x += dx; y += dy;
        if (x > getWidth() || y > getHeight()) {
            initDimensions();
        }
    }

    public void repaintImage() {
        repaint();
    }
}