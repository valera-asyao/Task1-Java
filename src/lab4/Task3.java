package lab4;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    static class FileInfo {
        Path path; long size; FileTime time; String ext;
        FileInfo(Path p, long s, FileTime t, String e) { path = p; size = s; time = t; ext = e; }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к папке для анализа: ");
            String input = scanner.nextLine();
            System.out.println("Анализирую...");

            try {
                Path root = Paths.get(input);
                List<FileInfo> allFiles = new ArrayList<>();
                long[] dirCount = {0};
                long[] totalSize = {0};

                Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        dirCount[0]++;
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        String name = file.getFileName().toString();
                        int dot = name.lastIndexOf('.');
                        String ext = (dot > 0 && dot < name.length() - 1) ? name.substring(dot).toLowerCase() : "без расширения";

                        allFiles.add(new FileInfo(file.toAbsolutePath(), attrs.size(), attrs.lastModifiedTime(), ext));
                        totalSize[0] += attrs.size();
                        return FileVisitResult.CONTINUE;
                    }
                });

                System.out.println("========================================");
                System.out.println("ОТЧЁТ ПО ПАПКЕ: " + root.toAbsolutePath());
                System.out.println("========================================");
                System.out.println("Всего файлов: " + allFiles.size());
                System.out.println("Всего папок: " + dirCount[0] + " (включая корневую)");
                System.out.println("Общий размер: " + formatSize(totalSize[0]));

                System.out.println("\nТОП-5 САМЫХ БОЛЬШИХ ФАЙЛОВ:");
                allFiles.stream()
                        .sorted(Comparator.comparingLong((FileInfo f) -> f.size).reversed())
                        .limit(5)
                        .forEach(f -> System.out.println("- " + f.path + " (" + formatSize(f.size) + ")"));

                System.out.println("\nТОП-5 САМЫХ СТАРЫХ ФАЙЛОВ (по дате изменения):");
                allFiles.stream()
                        .sorted(Comparator.comparingLong((FileInfo f) -> f.time.toMillis()))
                        .limit(5)
                        .forEach(f -> System.out.println("- " + f.path + " (" + DATE_FORMATTER.format(f.time.toInstant()) + ")"));

                System.out.println("\nСТАТИСТИКА ПО РАСШИРЕНИЯМ (по убыванию количества):");
                allFiles.stream()
                        .collect(Collectors.groupingBy(f -> f.ext, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " файл(ов)"));

                System.out.println("========================================");

            } catch (Exception e) { Task1.handleError(e, input); }
        }
    }

    private static String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " байт";
        if (bytes < 1048576) return String.format(Locale.US, "%.2f КБ", bytes / 1024.0);
        if (bytes < 1073741824) return String.format(Locale.US, "%.2f МБ", bytes / 1048576.0);
        return String.format(Locale.US, "%.2f ГБ", bytes / 1073741824.0);
    }
}