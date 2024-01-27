package org.cofezuwo.nsuluofuo.story;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.worlds.World;

public class Condition {

    @Getter
    @Setter
    private String variableType;
    @Getter
    @Setter
    private String variable;
    @Getter
    @Setter
    private String operator;
    @Getter
    @Setter
    private String value;

    public Condition(String variableType, String variable, String operator, String value) {
        this.variableType = variableType;
        this.variable = variable;
        this.operator = operator;
        this.value = value;
    }

    public boolean resolve() {
        switch(this.variableType) {
            case "int": return resolveInt();
        }

        return true;
    }

    private boolean resolveInt() {
        switch(this.variable) {
            case "playerPosX": return resolvePlayerPosX();
            case "playerPosY": return resolvePlayerPosY();
        }

        return true;
    }

    private boolean resolvePlayerPosX() {
        switch(this.operator) {
            case "=": return eq(World.getInstance().getEntityManager().getPlayer().getX(), Integer.parseInt(this.value));
        }

        return true;
    }

    private boolean resolvePlayerPosY() {
        switch(this.operator) {
            case "=": return eq(World.getInstance().getEntityManager().getPlayer().getY(), Integer.parseInt(this.value));
        }

        return true;
    }

    private boolean eq(int var, int val) {
        if(var != val) return false;
        return true;
    }

    private boolean lt(int var, int val) {
        if(var >= val) return false;
        return true;
    }

    private boolean gt(int var, int val) {
        if(var <= val) return false;
        return true;
    }


}
