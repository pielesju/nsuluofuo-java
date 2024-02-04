package org.cofezuwo.nsuluofuo.environment;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Animation;
import org.cofezuwo.nsuluofuo.graphics.Assets;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {

    private Animation rain;

    private Color daycolor;
    private Date currentDate = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH");
    private String formattedDate = sdf.format(currentDate);
    private int time = Integer.parseInt(formattedDate);



    public Weather() {
        this.rain = new Animation(100, Assets.raina);
        setDayColor();
    }

    public void update() {
        rain.update();
    }

    public void render(ATG g) {
        renderDayTimeColor(g);

        g.drawImage(rain.getCurrentFrame(), 0, 0, 640, 480);
    }

    private void setDayColor() {
        if((time >= 17  &&  time <= 20) || (time >= 6 && time  <= 8)) {
            daycolor = new Color(255,0,0,75);
        }else if(time>= 20 || time<=6) {
            daycolor = new Color(0,0,255,100);
        }else if(time>= 6 && time<=8) {
            daycolor = new Color(255,0,140,100);
        }else{
            daycolor = new Color(0,0,0,0);
        }
    }

    private void renderDayTimeColor(ATG g) {
        g.fillRect(0, 0, 640, 480, daycolor);
    }


}
