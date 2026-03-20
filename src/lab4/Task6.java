package lab4;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к первой папке: ");
            String inputA = scanner.nextLine();
            System.out.print("Введите путь ко второй папке: ");
            String inputB = scanner.nextLine();
            System.out.println("Сравниваю...");

            try {
                Path dirA = Paths.get(inputA);
                Path dirB = Paths.get(inputB);

                if (!Files.exists(dirA)) throw new NoSuchFileException(inputA);
                if (!Files.exists(dirB)) throw new NoSuchFileException(inputB);

                Map<Path, Path> filesA = getRelativeFiles(dirA);
                Map<Path, Path> filesB = getRelativeFiles(dirB);

                List<Path> onlyInA = new ArrayList<>();
                List<Path> onlyInB = new ArrayList<>();
                List<String> differ = new ArrayList<>();
                List<Path> identical = new ArrayList<>();

                for (Path rel : filesA.keySet()) {
                    if (!filesB.containsKey(rel)) {
                        onlyInA.add(rel);
                    } else {
                        Path absA = filesA.get(rel);
                        Path absB = filesB.get(rel);
                        long sizeA = Files.size(absA);
                        long sizeB = Files.size(absB);

                        if (sizeA != sizeB) {
                            differ.add(rel + " (A: " + sizeA + " байт, B: " + sizeB + " байт)");
                        } else {
                            // Сравниваем хеши, если размер совпал. Переиспользуем метод из Task4 если классы вместе.
                            // Здесь для независимости реализован упрощенный вызов (в идеале скопировать calculateHash)
                            if (Arrays.equals(Files.readAllBytes(absA), Files.readAllBytes(absB))) {
                                identical.add(rel);
                            } else {
                                differ.add(rel + " (A: " + sizeA + " байт, B: " + sizeB + " байт)");
                            }
                        }
                    }
                }

                for (Path rel : filesB.keySet()) {
                    if (!filesA.containsKey(rel)) {
                        onlyInB.add(rel);
                    }
                }

                System.out.println("Сравнение папок:");
                System.out.println("A: " + dirA.toAbsolutePath());
                System.out.println("B: " + dirB.toAbsolutePath());
                System.out.println("----------------------------------------");

                printSection("Только в A", onlyInA);
                printSection("Только в B", onlyInB);

                System.out.println("Отличаются по содержимому (" + differ.size() + " файл(ов)):");
                differ.forEach(System.out::println);

                printSection("Идентичны", identical);

            } catch (Exception e) { Task1.handleError(e, "Один из путей"); }
        }
    }

    private static Map<Path, Path> getRelativeFiles(Path root) throws IOException {
        Map<Path, Path> map = new HashMap<>();
        try (var stream = Files.walk(root)) {
            stream.filter(Files::isRegularFile).forEach(p -> map.put(root.relativize(p), p));
        }
        return map;
    }

    private static void printSection(String title, List<Path> list) {
        System.out.println(title + " (" + list.size() + " файл(ов)):");
        list.forEach(System.out::println);
    }
}
