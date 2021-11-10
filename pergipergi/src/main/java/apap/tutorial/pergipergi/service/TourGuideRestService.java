package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.rest.TourGuideDetail;

import reactor.core.publisher.Mono;

public interface TourGuideRestService {
    TourGuideModel getTourGuideByNoTourGuide(Long noTourGuide);
    String getTourGuideAge(Long tourGuideAge);
}
