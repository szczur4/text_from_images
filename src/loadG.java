import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("StringConcatenationInLoop")
public class loadG extends AbstractAction implements Runnable{
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
        Main.progress[1].setMaximum(height*width);
        Main.progress[1].setVisible(true);
        Main.labels[4].setVisible(true);
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                switch((char)new Color(Main.image.getRGB(x,y)).getGreen()){
                    case'\\'->{
                        if(x+1==width&&new Color(Main.image.getRGB(0,y+1)).getGreen()=='<'){
                            y++;
                            x=0;
                            text+=(char)new Color(Main.image.getRGB(x,y)).getGreen();
                        }
                        else if(x+1<width&&new Color(Main.image.getRGB(x+1,y)).getGreen()=='<'){
                            x++;
                            text+=(char)new Color(Main.image.getRGB(x,y)).getGreen();
                        }
                        else text+=new Color(Main.image.getRGB(x,y)).getGreen();
                    }
                    case'<'->{
                        if(x+1==width){
                            y++;
                            x=0;
                        }
                        else{
                            x++;
                        }
                        c+=new Color(Main.image.getRGB(x,y)).getGreen()*255;
                        if(x+1==width){
                            y++;
                            x=0;
                        }
                        else{
                            x++;
                        }
                        c+=new Color(Main.image.getRGB(x,y)).getGreen();
                        text+=(char)c;
                        c=0;
                    }
                    case(0)->{}
                    default->text+=(char)new Color(Main.image.getRGB(x,y)).getGreen();
                }
                Main.progress[1].setValue(y*width+x);
                Main.labels[4].setText("loading "+(y*width+x)+"/"+height*width);
            }
        }
        Main.textAreas[1].setText(text);
        Main.progress[1].setVisible(false);
        Main.labels[4].setVisible(false);
        text="";
        thread=null;
    }
}