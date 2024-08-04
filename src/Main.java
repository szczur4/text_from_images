import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

@SuppressWarnings("SpellCheckingInspection")
public class Main implements ActionListener{
    static final JFrame frame=new JFrame("Text from images");
    static final JMenuBar menuBar=new JMenuBar();
    static final JMenu menu=new JMenu("File");
    static final JMenuItem openItem=new JMenuItem("Open");
    static final JMenuItem saveItem=new JMenuItem("Save");
    static final JTextArea[] textAreas=new JTextArea[3];
    static final JLabel[] labels=new JLabel[6];
    static File file;
    static BufferedImage image;
    static final Color back=new Color(0x171717);
    static final Color fore=new Color(0xb0b0b0);
    static final Border border=new LineBorder(fore,1);
    static final JProgressBar[] progress=new JProgressBar[3];
    static final thred thred;
    static{try{thred=new thred();}catch(AWTException e){throw new RuntimeException(e);}}
    private static void gui(){
        Main m=new Main();
        for(int i=0;i<3;i++){
            textAreas[i]=new JTextArea();
            textAreas[i].setBackground(back);
            textAreas[i].setForeground(fore);
            textAreas[i].setBorder(border);
            textAreas[i].setCaretColor(fore);
			textAreas[i].setFont(new Font("consolas",Font.PLAIN,14));
            textAreas[i].setLineWrap(true);
            progress[i]=new JProgressBar();
            progress[i].setVisible(false);
            progress[i].setStringPainted(true);
            progress[i].setBorderPainted(true);
            progress[i].setSize(100,18);
            progress[i].setBorder(border);
            progress[i].setForeground(new Color(0x1a9122));
            progress[i].setBackground(back);
            progress[i].setUI(new BasicProgressBarUI(){protected Color getSelectionBackground(){return fore;}protected Color getSelectionForeground(){return fore;}});
            labels[i]=new JLabel("Text "+(i+1));
            labels[i].setForeground(fore);
            labels[i].setSize(50,30);
            frame.add(textAreas[i]);
            frame.add(labels[i]);
            frame.add(progress[i]);
        }
        labels[3]=new JLabel();
        labels[4]=new JLabel();
        labels[5]=new JLabel();
        openItem.addActionListener(m);
        saveItem.addActionListener(m);
        menu.add(openItem);
        menu.add(saveItem);
        menuBar.add(menu);
        textAreas[0].setLocation(5,25);
        labels[0].setLocation(5,0);
        labels[3].setBounds(150,0,300,30);
        labels[3].setForeground(fore);
        labels[4].setSize(300,30);
        labels[4].setForeground(fore);
        labels[5].setSize(300,30);
        labels[5].setForeground(fore);
        progress[0].setLocation(45,4);
        frame.add(labels[3]);
        frame.add(labels[4]);
        frame.add(labels[5]);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(back);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setJMenuBar(menuBar);
        thred.thread.start();
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(Main.class.getResource("icon.png"))).getImage());
    }
    public static void main(String[] args){javax.swing.SwingUtilities.invokeLater(Main::gui);}
    final load load=new load();
    final save save=new save();
    @Override
    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
        switch(s){
            case"Open"->load.actionPerformed(e);
            case"Save"->save.actionPerformed(e);
        }
    }
}