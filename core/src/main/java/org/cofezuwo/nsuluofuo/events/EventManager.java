package org.cofezuwo.nsuluofuo.events;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void update() {
        for(Event e: events) {
            e.update();
        }
    }

    public void render(ATG g) {
        for (Event e : events) {
            e.render(g);
        }
    }

    public void addEvent(Event e) {
        events.add(e);
    }
}
