package org.cofezuwo.nsuluofuo.story;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Quest {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private QuestStatus status;
    @Getter
    @Setter
    private List<String> conditions;
    @Getter
    @Setter
    private List<String> actions;
    @Getter
    @Setter
    private List<Quest> children;

    public String toString() {
        StringBuilder sb = new StringBuilder(this.name);

        sb.append(":\n");
        sb.append("STATUS: ").append(this.status).append("\n");

        if(null != conditions) {
            sb.append("CONDITIONS:\n");
            for(String condition : conditions) {
                sb.append(condition).append("\n");
            }
        }

        if(null != actions) {
            sb.append("ACTIONS:\n");
            for(String action : actions) {
                sb.append(action).append("\n");
            }
        }

        if(null != children) {
            for(Quest quest : children) {
                sb.append(quest.toString());
            }
        }

        return sb.toString();
    }

    public boolean checkConditions() {
        for(String condition : this.conditions) {
            String[] t = condition.split(" ");
            Condition con = new Condition(t[0], t[1], t[2], t[3]);

            if(con.resolve() == false) return false;
        }

        return true;
    }

    public void doActions() {
        for(String action : this.actions) {

        }
    }
}
