package org.example.DTO.models.ddragon;

import java.util.Map;

public class ChampionData {

    public String type;
    public String format;
    public String version;
    public Map<String, Champion> data;

    public ChampionData(String type, String format, String version, Map<String, Champion> data) {
        this.type = type;
        this.format = format;
        this.version = version;
        this.data = data;
    }
}
