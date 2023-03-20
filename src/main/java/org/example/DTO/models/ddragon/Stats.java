package org.example.DTO.models.ddragon;

public class Stats {

    private int hp;
    private double hpperlevel;
    private int mp;
    private double mpperlevel;
    private int movespeed;
    private double armor;
    private double armorperlevel;
    private double spellblock;
    private double spellblockperlevel;
    private int attackrange;
    private double hpregen;
    private double hpregenperlevel;
    private double mpregen;
    private double mpregenperlevel;
    private int crit;
    private int critperlevel;
    private int attackdamage;
    private double attackdamageperlevel;
    private double attackspeedperlevel;
    private double attackspeed;

    public Stats(int hp, double hpperlevel, int mp, double mpperlevel, int movespeed, double armor, double armorperlevel, double spellblock, double spellblockperlevel, int attackrange, double hpregen, double hpregenperlevel, double mpregen, double mpregenperlevel, int crit, int critperlevel, int attackdamage, double attackdamageperlevel, double attackspeedperlevel, double attackspeed) {
        this.hp = hp;
        this.hpperlevel = hpperlevel;
        this.mp = mp;
        this.mpperlevel = mpperlevel;
        this.movespeed = movespeed;
        this.armor = armor;
        this.armorperlevel = armorperlevel;
        this.spellblock = spellblock;
        this.spellblockperlevel = spellblockperlevel;
        this.attackrange = attackrange;
        this.hpregen = hpregen;
        this.hpregenperlevel = hpregenperlevel;
        this.mpregen = mpregen;
        this.mpregenperlevel = mpregenperlevel;
        this.crit = crit;
        this.critperlevel = critperlevel;
        this.attackdamage = attackdamage;
        this.attackdamageperlevel = attackdamageperlevel;
        this.attackspeedperlevel = attackspeedperlevel;
        this.attackspeed = attackspeed;
    }
}
