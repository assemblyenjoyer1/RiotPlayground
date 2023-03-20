package org.example.DTO.models.v4;

import java.util.List;

public class Perks {

    List<Long> perkIds;
    long perkStyle;
    long perkSubStyle;

    public Perks(List<Long> perkIds, long perkStyle, long perkSubStyle) {
        this.perkIds = perkIds;
        this.perkStyle = perkStyle;
        this.perkSubStyle = perkSubStyle;
    }

    public List<Long> getPerkIds() {
        return perkIds;
    }

    public void setPerkIds(List<Long> perkIds) {
        this.perkIds = perkIds;
    }

    public long getPerkStyle() {
        return perkStyle;
    }

    public void setPerkStyle(long perkStyle) {
        this.perkStyle = perkStyle;
    }

    public long getPerkSubStyle() {
        return perkSubStyle;
    }

    public void setPerkSubStyle(long perkSubStyle) {
        this.perkSubStyle = perkSubStyle;
    }
}
