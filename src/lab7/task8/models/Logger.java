package lab7.task8.models;

public class Logger {
    private static int totalMessages = 0;
    private static int errorCount = 0;
    private static int warningCount = 0;

    public static void logInfo(String message) {
        System.out.println("[INFO] " + message);
        totalMessages++;
    }

    public static void logWarning(String message) {
        System.out.println("[WARNING] " + message);
        totalMessages++;
        warningCount++;
    }

    public static void logError(String message) {
        System.out.println("[ERROR] " + message);
        totalMessages++;
        errorCount++;
    }

    public static String getStats() {
        return String.format("Всего сообщений: %d, из них ошибок: %d, предупреждений: %d",
                totalMessages, errorCount, warningCount);
    }
}
