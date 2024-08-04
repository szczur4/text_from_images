import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class save extends AbstractAction implements Runnable{
    final FileDialog saver=new FileDialog((Frame)null,"Save as",FileDialog.SAVE);
    BufferedImage image;
    int height=1,width=1,k,max;
    String filename;
    final String[]text=new String[3];
    char c;
    Thread thread;
    @Override
    public void actionPerformed(ActionEvent e){
        thread=new Thread(this);
        thread.start();
    }
    @Override
    public void run(){
        for(int j=0;j<3;j++){
            text[j]="";
            for(int i=0;i<Main.textAreas[j].getText().length();i++){
                c=Main.textAreas[j].getText().charAt(i);
                if(c=='<')text[j]+="\\<";
                else if(c>255){
                    text[j]+="<";
                    text[j]+=(char)(c/255);
                    text[j]+=(char)(c%255);
                }
                else text[j]+=c;
            }
        }
        max=Math.max(Math.max(text[0].length(),text[1].length()),text[2].length());
        for(int i=0;i<=max;i++){
            if(text[0].length()<=max)text[0]+=(char)0;
            if(text[1].length()<=max)text[1]+=(char)0;
            if(text[2].length()<=max)text[2]+=(char)0;
        }
        for(int i=1;i<=Math.sqrt(max);i++)if(max%i==0)width=max/i;
        if(max/width>0)height=max/width;
        image=new BufferedImage(width,height,1);
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                image.setRGB(x,y,new Color(text[0].charAt(k),text[1].charAt(k),text[2].charAt(k)).getRGB());
                k++;
            }
        }
        if(Main.file==null){
            saver.setDirectory("C:\\");
            saver.setFile("*.png");
            saver.setVisible(true);
            filename=saver.getDirectory()+saver.getFile();
            if(filename.equals("")){
                System.out.println("No file selected");
                return;
            }
            Main.file=new File(filename);
        }
        try{ImageIO.write(image,"png",Main.file);
        }catch(Exception ex){throw new RuntimeException(ex);}
        k=0;
    }
}