package mariel.testsexamples.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {

    HORROR("horror"),
    COMEDY("comedy"),
    ACTION("action");

    private String value;

    Genre(String genre){
        this.value = genre;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
