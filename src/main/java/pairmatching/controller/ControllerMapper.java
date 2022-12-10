package pairmatching.controller;

import pairmatching.domain.Crews;
import pairmatching.domain.PairMatchingRepository;

public class ControllerMapper {
    public static Controller executeByUserChoice(String choice, Crews crews, PairMatchingRepository pairMatchingRepository){
        return MainControls
                .getMatchingControls(choice)
                .generatedController(crews, pairMatchingRepository);
    }
}
