package Main.System;

import Main.Engine.core.Config;
import Main.UI.Render.Renderer;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class Data {
    long gameStartTime;
    long lastInputTime;
    int cntFrames = 0;
}

public class Profiler {
    private static boolean enabled = false;
    private static final Renderer tablet = new Renderer(8, Config.FRAME_COLUMNS);
    private static final Data data = new Data();

    private Profiler() {}

    public static boolean isEnabled() { return enabled; } // [ВОЗМОЖНОН НЕ НУЖЕН]
    public static void on()  {
        enabled = true;
        printInitialInterface();
    }
    public static void off() { enabled = false; }

    public static void recordGameStartTime() { data.gameStartTime = System.currentTimeMillis(); }
    public static void recordInputTime() { data.lastInputTime = System.nanoTime(); }
    public static void plusCntFrameS() { data.cntFrames++; }

    public static void display() {
        if (enabled) {
            printUpBorder();
            System.out.println(getActualTablet());
            printDownBorder();
        }
        else
            System.out.println("\n\u001B[1;3mProfiler: off\u001B[0m\n");
    }

    public static String getActualTablet() {
        if (enabled) {
            updateData();
            return "\n%s\u001B[0m\n".formatted(tablet.getScreen());
        } else {
            return "\n\u001B[1;3mProfiler: off\u001B[0m\n";
        }
    }


// Функции класса
    private static void printInitialInterface() {
        printUpBorder(); printDownBorder();
        printFieldNames();

        for (int i = 1; i <= 5; i++) addNote(i, 0, "— ");
    }

    private static void printUpBorder() {
        tablet.overlay(0, 0, new String[]{
                "\u001B[1;3mProfiler:\u001B[0m",
                "\u001B[1;53m",
                "\u001B[0m"},
                false);
    }
    private static void printDownBorder() {
        tablet.overlay(7, 0, "\u001B[1;4m", false);
    }
    private static void printFieldNames() {
        tablet.overlay(2, 3, "In game: ", false);
        tablet.overlay(3, 3, "Frames: ", false);
        tablet.overlay(4, 3, "Memory: ", false);
        tablet.overlay(6, 159, "frametime:       ms", false);
    }

    private static void addNote(int stroke, int column, String note) {
        tablet.overlay(stroke + 1, column + 1, note, false);
    }

    private static void updateData() {
        updateCurrentTime();
        updateTimeInGame();
        updateCntFrames();
        updateMemoryData();

        updateFrameTime();
    }

    private static void updateCurrentTime() {
        String time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        addNote(0, 0, time);
    }

    private static void updateTimeInGame() {
        String time = calculateTimeInGame();
        tablet.overlay(2, 12, time, false);
    }
    private static String calculateTimeInGame() {
        long difference = System.currentTimeMillis() - data.gameStartTime;
        Duration d = Duration.ofMillis(difference);
        return String.format("%02d:%02d:%02d", d.toHours()%24, d.toMinutes()%60, d.toSeconds()%60);
    }

    private static void updateCntFrames() {
        addNote(2, 10, String.valueOf(data.cntFrames));
    }

    private static void updateMemoryData() {
        addNote(3, 10, "               ");
        addNote(3, 10, calculateMemoryData());
    }
    private static String calculateMemoryData() {
        long total = Runtime.getRuntime().totalMemory();
        long used = total - Runtime.getRuntime().freeMemory();
        return "%.1f/%.1f MB".formatted(used/1_000_000f, total/1_000_000f);
    }

    private static void updateFrameTime() {
        String millis = "%6.2f".formatted(calculateFrameTime());
        addNote(5, 168, millis);
    }
    private static float calculateFrameTime() {
        return (System.nanoTime() - data.lastInputTime) / 1_000_000f;
    }

}