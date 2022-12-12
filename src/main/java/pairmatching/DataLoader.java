package pairmatching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;

public class DataLoader {
    private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";

    private final Crews crews;

    public DataLoader() {
        crews = new Crews();
    }

    public Crews load() throws FileNotFoundException {
        List<String> backendCrews = loadBackendCrews();
        List<String> frontendCrews = loadFrontendCrews();

        initBackend(backendCrews);
        initFrontend(frontendCrews);

        return crews;
    }

    private void initBackend(List<String> backendCrews) {
        for (String backendCrew : backendCrews) {
            Crew crew = new Crew(Course.BACKEND, backendCrew);
            crews.addCrew(crew);
        }
    }

    private void initFrontend(List<String> frontendCrews) {
        for (String frontendCrew : frontendCrews) {
            Crew crew = new Crew(Course.FRONTEND, frontendCrew);
            crews.addCrew(crew);
        }
    }

    private List<String> loadBackendCrews() throws FileNotFoundException {
        List<String> backendCrews = new ArrayList<>();
        Scanner scanner = new Scanner(new File(BACKEND_FILE_PATH));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            backendCrews.add(name);
        }
        scanner.close();
        return backendCrews;
    }

    private List<String> loadFrontendCrews() throws FileNotFoundException {
        List<String> frontendCrews = new ArrayList<>();
        Scanner scanner = new Scanner(new File(FRONTEND_FILE_PATH));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            frontendCrews.add(name);
        }
        scanner.close();
        return frontendCrews;
    }


}
