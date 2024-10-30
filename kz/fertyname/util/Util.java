package kz.fertyname.util;

import java.time.LocalTime;

public class Util {
    private static void currentTime() {
        LocalTime now = LocalTime.now();
        System.out.printf("\n[%02d:%02d:%02d] ", now.getHour(), now.getMinute(), now.getSecond());
    }
    public static void m_Print(String message, String buffer, int sleep) {
        currentTime();

        switch (message) {
            case "error":
                System.out.print("\u001B[31m[>] \u001B[0m" + buffer); // Color(4) - red
                break;
            case "default":
                System.out.print("\u001B[35m[>] \u001B[0m" + buffer); // Color(5) - magenta
                break;
            case "warning":
                System.out.print("\u001B[33m[>] \u001B[0m" + buffer); // Color(6) - yellow
                break;
            default:
                System.out.print("\u001B[36m[>] \u001B[0m" + buffer); // Color(9) - cyan
                break;
        }

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted during sleep.");
        }
    }
}
