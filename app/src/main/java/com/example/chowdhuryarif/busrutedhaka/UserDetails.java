package com.example.chowdhuryarif.busrutedhaka;

import android.widget.AutoCompleteTextView;

public class UserDetails {
    String busName, placeName;
    int fkBusId, fkPlaceId;


    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getFkBusId() {
        return fkBusId;
    }

    public void setFkBusId(int fkBusId) {
        this.fkBusId = fkBusId;
    }

    public int getFkPlaceId() {
        return fkPlaceId;
    }

    public void setFkPlaceId(int fkPlaceId) {
        this.fkPlaceId = fkPlaceId;
    }
}
