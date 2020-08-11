

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.MemoryImageSource;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Rain extends JDialog implements ActionListener {

    private Random random = new Random();
    private Dimension screenSize;
    private JPanel graphicsPanel;
    private final static int gap = 20;
    private int[] posArr;
    private int lines;
    private int columns;
    private boolean isMultiColor = false;
    private JDialog that = this;

    public Rain(boolean isMultiColor) {
        this.isMultiColor = isMultiColor;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        graphicsPanel = new GraphicsPanel();
        add(graphicsPanel, BorderLayout.CENTER);
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Image image = defaultToolkit.createImage(new MemoryImageSource(0, 0, null, 0, 0));
        Cursor invisibleCursor = defaultToolkit.createCustomCursor(image, new Point(0, 0), "cursor");
        setCursor(invisibleCursor);
        KeyPressListener keyPressListener = new KeyPressListener();
        this.addKeyListener(keyPressListener);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        lines = screenSize.height / gap;
        columns = screenSize.width / gap;
        posArr = new int[columns + 1];
        for (int i = 0; i < posArr.length; i++) {
            posArr[i] = random.nextInt(lines);
        }
        new Timer(100, this).start();
    }

    private char getChr() {
        int nextInt = random.nextInt(3);
        if (nextInt == 1) {
            if (random.nextBoolean()) {
                return '0';
            } else {
                return '1';
            }
        } else if (nextInt == 2) {
            return (char) ('A' + random.nextInt(26));
        } else {
            return (char) (0x3041 + random.nextInt(86));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graphicsPanel.repaint();
    }

    private class GraphicsPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(getFont().deriveFont(Font.BOLD));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, screenSize.width, screenSize.height);
            int currentColumn = 0;
            for (int x = 0; x < screenSize.width; x += gap) {
                int endPos = posArr[currentColumn];
                int cg = 0;
                for (int j = endPos - 15; j < endPos; j++) {
                    cg += 20;
                    if (cg > 255) {
                        cg = 255;
                    }
                    Color color = new Color(0, cg, 0);
                    if (isMultiColor) {
                        int red = random.nextInt(256);
                        int blue = random.nextInt(256);
                        int green = random.nextInt(256);
                        color = new Color(red, green, blue);
                    }
                    g2d.setColor(color);
                    g2d.drawString(String.valueOf(getChr()), x, j * gap);
                }
                posArr[currentColumn] += random.nextInt(5);
                if (posArr[currentColumn] * gap > getHeight()) {
                    posArr[currentColumn] = random.nextInt(lines);
                }
                currentColumn++;
            }
        }
    }

    private class KeyPressListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            that.dispose();
        }

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("代码流屏保");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) screen.getHeight();
        int width = (int) screen.getWidth();
        int ww = 300;
        int wh = 160;
        jf.setLayout(null);
        Font font = new Font("隶书", Font.BOLD, 13);
        JPanel jp = new JPanel(null);
        JButton gen = new JButton("开始屏保");
        gen.setFont(font);
        gen.setBounds(40, 40, 100, 40);
        gen.setBorderPainted(false);
        jp.add(gen);
        final JRadioButton rb = new JRadioButton("是否多彩");
        rb.setFont(font);
        rb.setBounds(180, 40, 100, 40);
        rb.setBackground(Color.LIGHT_GRAY);
        rb.setBorderPainted(false);
        jp.add(rb);
        jp.setBackground(Color.LIGHT_GRAY);
        jf.setBounds(width / 2 - ww / 2, height / 2 - wh / 2, ww, wh);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setContentPane(jp);
        jf.setResizable(false);
        jf.setVisible(true);
        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = rb.isSelected();
                new Rain(selected);
            }
        });
    }

}