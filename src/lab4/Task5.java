package lab4;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к папке: ");
            String input = scanner.nextLine();

            try {
                Path root = Paths.get(input);
                if (!Files.exists(root)) throw new NoSuchFileException(input);

                System.out.println("Организация папки: " + root.toAbsolutePath());
                int movedCount = 0;

                try (var stream = Files.list(root)) {
                    for (Path file : stream.toList()) {
                        if (Files.isRegularFile(file)) {
                            String name = file.getFileName().toString();
                            int dotIndex = name.lastIndexOf('.');
                            String ext = "other";

                            if (dotIndex > 0 && dotIndex < name.length() - 1) {
                                ext = name.substring(dotIndex + 1).toLowerCase();
                            }

                            Path targetDir = root.resolve(ext);
                            if (!Files.exists(targetDir)) {
                                Files.createDirectories(targetDir);
                            }

                            Path targetFile = targetDir.resolve(file.getFileName());
                            Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Перемещён: " + name + " -> " + ext + "/");
                            movedCount++;
                        }
                    }
                }

                if (movedCount == 0) {
                    System.out.println("В папке нет файлов для организации.");
                } else {
                    System.out.println("Готово. Перемещено файлов: " + movedCount);
                }

            } catch (Exception e) { Task1.handleError(e, input); }
        }
    }
}