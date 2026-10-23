package Exercise;

import java.util.Scanner;

public class WeekdayWeekend {

    private static String getDayType(String day) {
        return switch (day.trim().toLowerCase()) {
            case "monday", "tuesday", "wednesday", "thursday", "friday" -> "Weekday";
            case "saturday", "sunday" -> "Weekend";
            default -> "Invalid day";
        };
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a day of the week: ");
            String day = scanner.nextLine();

            System.out.println(day + " is a " + getDayType(day) + ".");
        }
    }
}
