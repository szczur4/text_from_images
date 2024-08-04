import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("StringConcatenationInLoop")
public class loadB extends AbstractAction implements Runnable{
    int height,width,c;
    String text="";
    Thread thread;
    @Override
    public void actionPerformed(ActionEvent e){
        thread=new Thread(this);
        thread.start();
    }
    @Override
    public void run(){
        height=Main.image.getHeight();
        width=Main.image.getWidth();
        Main.progress[2].setMaximum(height*width);
        Main.progress[2].setVisible(true);
        Main.labels[5].setVisible(true);
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                switch((char)new Color(Main.image.getRGB(x,y)).getBlue()){
                    case'\\'->{
                        if(x+1==width&&new Color(Main.image.getRGB(0,y+1)).getBlue()=='<'){
                            y++;
                            x=0;
                            text+=(char)new Color(Main.image.getRGB(x,y)).getBlue();
                        }
                        else if(x+1<width&&new Color(Main.image.getRGB(x+1,y)).getBlue()=='<'){
                            x++;
                            text+=(char)new Color(Main.image.getRGB(x,y)).getBlue();
                        }
                        else text+=new Color(Main.image.getRGB(x,y)).getBlue();
                    }
                    case'<'->{
                        if(x+1==width){
                            y++;
                            x=0;
                        }
                        else{
                            x++;
                        }
                        c+=new Color(Main.image.getRGB(x,y)).getBlue()*255;
                        if(x+1==width){
                            y++;
                            x=0;
                        }
                        else{
                            x++;
                        }
                        c+=new Color(Main.image.getRGB(x,y)).getBlue();
                        text+=(char)c;
                        c=0;
                    }
                    case(0)->{}
                    default->text+=(char)new Color(Main.image.getRGB(x,y)).getBlue();
                }
                Main.progress[2].setValue(y*width+x);
                Main.labels[5].setText("loading "+(y*width+x)+"/"+height*width);
            }
        }
        Main.textAreas[2].setText(text);
        Main.progress[2].setVisible(false);
        Main.labels[5].setVisible(false);
        text="";
        thread=null;
    }
}