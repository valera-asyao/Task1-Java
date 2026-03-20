package lab4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Task4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите путь к папке: ");
            String input = scanner.nextLine();
            System.out.println("Ищу дубликаты...");

            try {
                Path root = Paths.get(input);
                if (!Files.exists(root)) throw new NoSuchFileException(input);

                // Этап 1: Группировка по размеру
                Map<Long, List<Path>> bySize;
                try (var stream = Files.walk(root)) {
                    bySize = stream
                            .filter(Files::isRegularFile)
                            .collect(Collectors.groupingBy(p -> {
                                try { return Files.size(p); } catch (IOException e) { return -1L; }
                            }));
                }

                // Этап 2: Группировка по хешу (только если размер совпал)
                List<List<Path>> duplicateGroups = new ArrayList<>();
                Map<List<Path>, String> groupHashes = new HashMap<>();

                for (Map.Entry<Long, List<Path>> entry : bySize.entrySet()) {
                    if (entry.getValue().size() > 1 && entry.getKey() != -1L) {
                        Map<String, List<Path>> byHash = entry.getValue().stream()
                                .collect(Collectors.groupingBy(Task4::calculateHash));

                        for (Map.Entry<String, List<Path>> hashEntry : byHash.entrySet()) {
                            if (hashEntry.getValue().size() > 1) {
                                duplicateGroups.add(hashEntry.getValue());
                                groupHashes.put(hashEntry.getValue(), hashEntry.getKey());
                            }
                        }
                    }
                }

                if (duplicateGroups.isEmpty()) {
                    System.out.println("Дубликатов не найдено.");
                } else {
                    System.out.println("Анализ завершён. Найдено групп дубликатов: " + duplicateGroups.size());
                    int groupNum = 1;
                    for (List<Path> group : duplicateGroups) {
                        System.out.println("--- Группа " + groupNum++ + " ---");
                        long size = Files.size(group.get(0));
                        String hash = groupHashes.get(group);
                        System.out.println("Размер: " + size + " байт Хеш: " + hash.substring(0, Math.min(16, hash.length())) + "...");
                        for (Path p : group) {
                            System.out.println(p.toAbsolutePath());
                        }
                    }
                }

            } catch (Exception e) { Task1.handleError(e, input); }
        }
    }

    private static String calculateHash(Path path) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream is = Files.newInputStream(path)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : digest.digest()) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            return UUID.randomUUID().toString(); // Уникальный fallback
        }
    }
}
