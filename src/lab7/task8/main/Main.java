package lab7.task8.main;
import lab7.task8.models.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.logInfo("Приложение запущено.");
        Logger.logWarning("Низкий уровень заряда батареи.");
        Logger.logError("Не удалось подключиться к базе данных.");
        Logger.logInfo("Повторная попытка...");
        Logger.logError("Критический сбой системы.");

        System.out.println("\n--- Статистика логгера ---");
        System.out.println(Logger.getStats());
    }
}
