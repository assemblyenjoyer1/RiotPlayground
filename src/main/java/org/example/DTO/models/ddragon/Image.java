package org.example.DTO.models.ddragon;

public class Image {

    public String full;
    public String sprite;
    public String group;
    public int x;
    public int y;
    public int w;
    public int h;

    public Image(String full, String sprite, String group, int x, int y, int w, int h) {
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
