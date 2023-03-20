package org.example.DTO.models.ddragon;

import java.util.ArrayList;

public class Champion {

        public String version;
        public String id;
        public String key;
        public String name;
        public String title;
        public String blurb;
        public Info info;
        public Image image;
        public ArrayList<String> tags;
        public String partype;
        public Stats stats;

        public Champion(String version, String id, String key, String name, String title, String blurb, Info info, Image image, ArrayList<String> tags, String partype, Stats stats) {
                this.version = version;
                this.id = id;
                this.key = key;
                this.name = name;
                this.title = title;
                this.blurb = blurb;
                this.info = info;
                this.image = image;
                this.tags = tags;
                this.partype = partype;
                this.stats = stats;
        }
}
