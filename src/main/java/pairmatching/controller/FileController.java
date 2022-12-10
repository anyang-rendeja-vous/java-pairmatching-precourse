package pairmatching.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.view.OutputView;

public class FileController {

    OutputView outputView = new OutputView();

    private final String BACKEND_ADDRESS = "src/main/resources/backend-crew.md";
    private final String FRONTEND_ADDRESS = "src/main/resources/frontend-crew.md";

    //파일 읽기
    public List<Crew> readFile(Course course, String fileAddress) throws IOException {
        File file = new File(fileAddress);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Crew> crewNames = new ArrayList<>();
        String element;
        while ((element = bufferedReader.readLine()) != null) {
            crewNames.add(new Crew(course, element));
        }
        return crewNames;
    }

    //백엔드 크루 파일 읽기
    public List<Crew> readBackendCrews() {
        List<Crew> backendCrews = new ArrayList<>();
        try {
            backendCrews = readFile(Course.BACKEND, BACKEND_ADDRESS);
        } catch (IOException e) {
            outputView.printError(e.getMessage());
        }
        return backendCrews;
    }

    //프론트엔드 크루 파일 읽기
    public List<Crew> readFrontendCrews() {
        List<Crew> frontendCrews = new ArrayList<>();
        try {
            frontendCrews = readFile(Course.FRONTEND, FRONTEND_ADDRESS);
        } catch (IOException e) {
            outputView.printError(e.getMessage());
        }
        return frontendCrews;
    }
}
