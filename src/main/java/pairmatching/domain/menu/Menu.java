package pairmatching.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {

    PAIR_MATCHING("1", ". 페어 매칭"),
    PRINT_PAIR("2", ". 페어 조회"),
    RESET_PAIR("3", ". 페어 초기화"),
    QUIT("Q", ". 종료");

    private final String prefix;
    private final String content;

    Menu(String prefix, String content) {
        this.prefix = prefix;
        this.content = content;
    }

    public boolean isSamePrefix(String prefix) {
        return this.prefix.equals(prefix);
    }

    public static List<String> getAll() {
        return Arrays.stream(Menu.values())
                .map(Menu::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return prefix + content;
    }
}
