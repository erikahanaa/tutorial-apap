package apap.tutorial.pergipergi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TourGuideDetail {
    
    @JsonProperty("age")
    private Integer tourGuideAge;

    public Integer getTourGuideAge(){
        return tourGuideAge;
    }

    public void setTourGuideAge(Integer tourGuideAge){
        this.tourGuideAge = tourGuideAge;
    }
}