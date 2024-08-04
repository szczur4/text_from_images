import java.awt.*;

@SuppressWarnings("SpellCheckingInspection")
public class thred implements Runnable{
    final Thread thread=new Thread(this);
    final Robot robot=new Robot();
    thred()throws AWTException{
    }
    @Override
    public void run(){
        while(thread.isAlive()){
            Main.textAreas[0].setSize(Main.frame.getWidth()/3-10,Main.frame.getHeight()-90);
            Main.textAreas[1].setBounds(Main.frame.getWidth()/3,25,Main.frame.getWidth()/3-15,Main.frame.getHeight()-90);
            Main.textAreas[2].setBounds(Main.frame.getWidth()/3*2-10,25,Main.frame.getWidth()/3-10,Main.frame.getHeight()-90);
            Main.labels[1].setLocation(Main.frame.getWidth()/3,0);
            Main.labels[2].setLocation(Main.frame.getWidth()/3*2-10,0);
            Main.labels[4].setLocation(Main.frame.getWidth()/3+150,0);
            Main.labels[5].setLocation(Main.frame.getWidth()/3*2+140,0);
            Main.progress[1].setLocation(Main.frame.getWidth()/3+40,4);
            Main.progress[2].setLocation(Main.frame.getWidth()/3*2+30,4);
            robot.delay(50);
        }
    }
}