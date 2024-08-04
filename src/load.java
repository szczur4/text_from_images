import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class load extends AbstractAction{
    final FileDialog opener=new FileDialog((Frame)null,"select file to load",FileDialog.LOAD);
    String filename;
    final loadR loadR=new loadR();
    final loadG loadG=new loadG();
    final loadB loadB=new loadB();
    @Override
    public void actionPerformed(ActionEvent e){
        opener.setDirectory("c:\\");
        opener.setVisible(true);
        filename=opener.getDirectory()+opener.getFile();
        if(filename.equals("")){
            System.out.println("No file selected");
            return;
        }
        Main.file=new File(filename);
        try{Main.image=ImageIO.read(Main.file);
        }catch(Exception ex){throw new RuntimeException(ex);}
        loadR.actionPerformed(e);
        loadG.actionPerformed(e);
        loadB.actionPerformed(e);
    }
}