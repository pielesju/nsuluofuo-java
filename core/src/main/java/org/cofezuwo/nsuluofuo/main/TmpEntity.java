package org.cofezuwo.nsuluofuo.main;

import lombok.Getter;
import lombok.Setter;

public class TmpEntity {

    enum EntityType {
        GANJA,
        NPC,
        MALENICA
    }

    @Getter
    @Setter
    private EntityType type;

    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String[] text;


}