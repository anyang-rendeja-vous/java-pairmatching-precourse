package pairmatching.controller;

import pairmatching.service.PairMatchingService;

public class ControllerMapper {
    public static Controller executeByUserChoice(String choice, PairMatchingService pairMatchingService){
        return MainControls
                .getMatchingControls(choice)
                .generatedController(pairMatchingService);
    }
}
