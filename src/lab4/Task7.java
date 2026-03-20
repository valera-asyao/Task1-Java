package lab4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task7 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к папке с логами: ");
            String input = scanner.nextLine();
            System.out.println("Анализирую...");

            try {
                Path root = Paths.get(input);
                if (!Files.exists(root)) throw new NoSuchFileException(input);

                List<Path> logFiles;
                try (var stream = Files.walk(root)) {
                    logFiles = stream.filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".log"))
                            .collect(Collectors.toList());
                }

                if (logFiles.isEmpty()) {
                    System.out.println("В указанной папке нет файлов с расширением .log.");
                    return;
                }

                List<String> validDates = new ArrayList<>();
                for (Path logFile : logFiles) {
                    try (var lines = Files.lines(logFile, StandardCharsets.UTF_8)) {
                        lines.filter(line -> line.length() >= 10 && line.substring(0, 10).matches("\\d{4}-\\d{2}-\\d{2}"))
                                .map(line -> line.substring(0, 10))
                                .forEach(validDates::add);
                    }
                }

                System.out.println("Анализ логов в папке: " + root.toAbsolutePath());
                System.out.println("Найдено .log файлов: " + logFiles.size());
                System.out.println("========================================");

                Map<String, Long> byDay = validDates.stream()
                        .collect(Collectors.groupingBy(d -> d, Collectors.counting()));

                System.out.println("Статистика по дням (все дни, сортировка по дате):");
                byDay.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " записей"));

                if (!byDay.isEmpty()) {
                    var max = Collections.max(byDay.entrySet(), Map.Entry.comparingByValue());
                    var min = Collections.min(byDay.entrySet(), Map.Entry.comparingByValue());
                    System.out.println("Максимальная активность: " + max.getKey() + " (" + max.getValue() + " записей)");
                    System.out.println("Минимальная активность: " + min.getKey() + " (" + min.getValue() + " записей)");
                }

                System.out.println("Статистика по месяцам:");
                validDates.stream()
                        .map(d -> d.substring(0, 7))
                        .collect(Collectors.groupingBy(m -> m, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " записей"));

                System.out.println("========================================");
                System.out.println("Всего записей обработано: " + validDates.size());

            } catch (Exception e) { Task1.handleError(e, input); }
        }
    }
}
