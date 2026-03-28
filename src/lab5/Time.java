package lab5;

import java.time.LocalTime;

public class Time {
    // Поля
    private int hours;
    private int minutes;
    private int seconds;

    // Конструкторы
    public Time(int hours, int minutes, int seconds) throws Exception {
        if (hours < 0 || hours > 23)
            throw new Exception("Некорректный час");
        if (minutes < 0 || minutes > 59)
            throw new Exception("Некорректные минуты");
        if (seconds < 0 || seconds > 59)
            throw new Exception("Некорректные секунды");
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    public Time(){
        hours = LocalTime.now().getHour();
        minutes = LocalTime.now().getMinute();
        seconds = LocalTime.now().getSecond();
    }

    // Геттеры
    public int getHours() { return hours; }
    public int getMinutes() { return minutes; }
    public int getSeconds() { return seconds; }

    // Добавление секунд
    public void addSeconds(int seconds) throws Exception {
        if (seconds < 0)
            throw new Exception("Время не может быть отрицательным");
        var total = this.seconds + seconds;
        this.seconds = total % 60;
        var extra = total / 60;
        addMinutes(extra);
    }
    // Добавление минут
    public void addMinutes(int minutes) throws Exception {
        if (minutes < 0)
            throw new Exception("Время не может быть отрицательным");
        var total = this.minutes + minutes;
        this.minutes = total % 60;
        var extra = total / 60;
        addHours(extra);
    }
    // Добавление часов
    public void addHours(int hours) throws Exception {
        if (hours < 0)
            throw new Exception("Время не может быть отрицательным");
        this.hours = (this.hours + hours) % 24;
    }
    // Разница во времени в секундах
    public int differenceInSeconds(Time other) {
        int fst = getHours() * 3600 + getMinutes() * 60 + getSeconds();
        int snd = other.getHours() * 3600 + other.getMinutes() * 60 + other.getSeconds();
        return  Math.abs(snd - fst);
    }
    // Вывод в 24-часовом формате
    public void print24h() {
        System.out.printf("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }
    // Вывод в 12-часовом формате
    public void print12h() {
        String AmPm = getHours() < 12 ? "AM" : "PM";
        int hours_12 = getHours() % 12;
        if (hours_12 == 0) hours_12 = 12;
        System.out.printf("%02d:%02d:%02d %s", hours_12, getMinutes(), getSeconds(), AmPm);
    }

    public static void main(String[] args){
        try{
        Time time1 = new Time(10, 29, 45);
        Time time2 = new Time(9, 18, 20);
        Time time3 = new Time(7, 59, 50);
        Time currentTime = new Time();

        System.out.println("Время 1: ");
        time1.print24h();
        System.out.print(" | ");
        time1.print12h();
        System.out.println();

        System.out.println("Время 2: ");
        time2.print24h();
        System.out.print(" | ");
        time2.print12h();
        System.out.println();

        System.out.println("Время 3: ");
        time3.print24h();
        System.out.print(" | ");
        time3.print12h();
        System.out.println();

        System.out.println("Текущее время: ");
        currentTime.print24h();
        System.out.print(" | ");
        currentTime.print12h();
        System.out.println();

        System.out.println("addSeconds()");

        System.out.print("Время 1 до добавления секунд: ");
        time1.print24h();
        System.out.println();

        time1.addSeconds(30);
        System.out.print("Время 1 после добавления 30 секунд: ");
        time1.print24h();
        System.out.println();

        time1.addSeconds(45);
        System.out.print("Время 1 после добавления 45 секунд: ");
        time1.print24h();
        System.out.println();
        System.out.println();

        System.out.println("addMinutes()");

        System.out.print("Время 2 до добавления минут: ");
        time2.print24h();
        System.out.println();

        time2.addMinutes(30);
        System.out.print("Время 2 после добавления 30 минут: ");
        time2.print24h();
        System.out.println();

        time2.addMinutes(45);
        System.out.print("Время 2 после добавления 45 минут: ");
        time2.print24h();
        System.out.println();
        System.out.println();

        System.out.println("addHours()");

        System.out.print("Время 3 до добавления часов: ");
        time3.print24h();
        System.out.println();

        time3.addHours(1);
        System.out.print("Время 3 после добавления 1 часа: ");
        time3.print24h();
        System.out.println();

        time3.addHours(25);
        System.out.print("Время 3 после добавления 25 часов: ");
        time3.print24h();
        System.out.println();
        System.out.println();

        System.out.println("differenceInSeconds()");

        Time time4 = new Time(10, 0, 0);
        Time time5 = new Time(16, 20, 45);

        System.out.print("Время 4: ");
        time4.print24h();
        System.out.println();

        System.out.print("Время 5: ");
        time5.print24h();
        System.out.println();

        int diff = time4.differenceInSeconds(time5);
        System.out.println("Разница между временем 4 и 5: " + diff + " секунд");

        Time midnight = new Time(0, 0, 0);
        Time noon = new Time(12, 0, 0);
        Time evening = new Time(18, 30, 15);

        System.out.println("AM и PM");
        System.out.print("Полночь: ");
        midnight.print12h();
        System.out.println();

        System.out.print("Полдень: ");
        noon.print12h();
        System.out.println();

        System.out.print("Вечер: ");
        evening.print12h();
        System.out.println();
        System.out.println();
    }
    catch (Exception e){
        System.err.println(e.getMessage());
    }
    }
}