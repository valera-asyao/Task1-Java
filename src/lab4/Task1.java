package lab4;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Файловый менеджер ===");
            System.out.println("Текущая папка: " + Paths.get("").toAbsolutePath());
            System.out.println("1 Показать содержимое папки");
            System.out.println("2 Создать папку");
            System.out.println("3 Удалить файл или папку");
            System.out.println("4 Копировать файл");
            System.out.println("5 Переместить / переименовать");
            System.out.println("6 Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": showDirectory(); break;
                case "2": createDirectory(); break;
                case "3": deletePath(); break;
                case "4": copyPath(); break;
                case "5": movePath(); break;
                case "6": return;
                default: System.out.println("Неверный выбор. Повторите.");
            }
        }
    }

    private static void showDirectory() {
        System.out.print("Введите путь к папке: ");
        String input = scanner.nextLine();
        try {
            Path path = Paths.get(input);
            try (var stream = Files.list(path)) {
                long count = 0;
                for (Path p : stream.toList()) {
                    count++;
                    FileTime time = Files.getLastModifiedTime(p);
                    String dateStr = DATE_FORMATTER.format(time.toInstant());
                    if (Files.isDirectory(p)) {
                        System.out.printf("папка: [П] %s <%s>%n", p.getFileName(), dateStr);
                    } else {
                        long size = Files.size(p);
                        System.out.printf("файл: [Ф] %s <%d> байт <%s>%n", p.getFileName(), size, dateStr);
                    }
                }
                if (count == 0) System.out.println("Папка пуста.");
            }
        } catch (Exception e) { handleError(e, input); }
    }

    private static void createDirectory() {
        System.out.print("Введите путь для новой папки: ");
        String input = scanner.nextLine();
        try {
            Path path = Paths.get(input);
            if (Files.exists(path)) {
                System.out.println("Папка уже существует: " + path);
            } else {
                Files.createDirectories(path);
                System.out.println("Папка создана: " + path.toAbsolutePath());
            }
        } catch (Exception e) { handleError(e, input); }
    }

    private static void deletePath() {
        System.out.print("Введите путь для удаления: ");
        String input = scanner.nextLine();
        try {
            Path path = Paths.get(input);
            try {
                Files.delete(path); // Пытаемся удалить файл или пустую папку
                System.out.println("Удалено: " + path);
            } catch (DirectoryNotEmptyException e) {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println("Удалено: " + path);
            }
        } catch (Exception e) { handleError(e, input); }
    }

    private static void copyPath() {
        System.out.print("Введите путь к источнику: ");
        String srcInput = scanner.nextLine();
        System.out.print("Введите путь к цели: ");
        String dstInput = scanner.nextLine();
        try {
            Path src = Paths.get(srcInput);
            Path dst = Paths.get(dstInput);
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Скопировано: " + src + " -> " + dst);
        } catch (Exception e) { handleError(e, srcInput); }
    }

    private static void movePath() {
        System.out.print("Введите путь к источнику: ");
        String srcInput = scanner.nextLine();
        System.out.print("Введите путь к цели: ");
        String dstInput = scanner.nextLine();
        try {
            Path src = Paths.get(srcInput);
            Path dst = Paths.get(dstInput);
            Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Перемещено: " + src + " -> " + dst);
        } catch (Exception e) { handleError(e, srcInput); }
    }

    public static void handleError(Exception e, String input) {
        if (e instanceof InvalidPathException) {
            System.out.println("Ошибка: некорректный путь: " + input);
        } else if (e instanceof NoSuchFileException) {
            String file = ((NoSuchFileException) e).getFile();
            System.out.println("Ошибка: файл или папка не найдены: " + (file != null ? file : input));
        } else if (e instanceof AccessDeniedException) {
            String file = ((AccessDeniedException) e).getFile();
            System.out.println("Ошибка: нет прав доступа: " + (file != null ? file : input));
        } else if (e instanceof IOException) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        } else {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}