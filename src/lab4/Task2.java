package lab4;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к папке: ");
            String pathInput = scanner.nextLine();
            System.out.print("Введите маску (например, *.txt, image*.png): ");
            String mask = scanner.nextLine();

            try {
                Path startPath = Paths.get(pathInput);
                if (!Files.exists(startPath)) throw new NoSuchFileException(pathInput);

                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);

                System.out.println("Поиск по маске \"" + mask + "\" в папке " + startPath.toAbsolutePath() + "...");

                try (var stream = Files.walk(startPath)) {
                    List<Path> found = stream
                            .filter(Files::isRegularFile)
                            .filter(p -> matcher.matches(p.getFileName()))
                            .collect(Collectors.toList());

                    if (found.isEmpty()) {
                        System.out.println("По маске \"" + mask + "\" файлов не найдено.");
                    } else {
                        System.out.println("Найдено файлов: " + found.size());
                        for (Path p : found) {
                            long size = Files.size(p);
                            FileTime time = Files.getLastModifiedTime(p);
                            System.out.println(p.toAbsolutePath());
                            System.out.println("Размер: " + size + " байт");
                            System.out.println("Изменён: " + DATE_FORMATTER.format(time.toInstant()));
                        }
                    }
                }
            } catch (Exception e) { Task1.handleError(e, pathInput); } // Можно переиспользовать метод или скопировать его сюда
        }
    }
}