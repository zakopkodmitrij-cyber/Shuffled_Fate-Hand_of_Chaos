package Main.UI.Render;

import Main.Engine.core.Config;
import Main.System.Profiler;

public class Renderer {
    private final Frame frame = new Frame(Config.FRAME_STROKES, Config.FRAME_COLUMNS);
    private final ColorMap colorMap = new ColorMap(frame);

    public Renderer() {}

    public Renderer(int strokes, int columns) {
        frame.setResolution(strokes, columns);
        colorMap.setResolution(strokes, columns);
    }

    public void printScreen() {
        Profiler.plusCntFrameS();

        String profilerTablet = Profiler.getActualTablet();
        String screen = getScreen();

        System.out.printf("%s\n%s\n", profilerTablet, screen);
    }

    public String getScreen() { return colorizeFrame().toString(); }

    public void clearAll() {
        clearFrame();
        clearColorMap();
    }
    public void clearAll(int uStroke, int lColumn, int dStroke, int rColumn, boolean stopingFill) {
        clearFrame(uStroke, lColumn, dStroke, rColumn);
        clearColorMap(uStroke, lColumn, dStroke, rColumn, stopingFill);
    }

    public void fillFrame(char c) { frame.fill(c); }

    public void clearFrame() { frame.fill(' '); }
    public void clearFrame(int uStroke, int lColumn, int dStroke, int rColumn) {
        frame.clear(uStroke, lColumn, dStroke, rColumn);
    }

    public void overlay(Texture texture, boolean spot) {
        overlay(texture.coord_stroke, texture.coord_column, texture.data, spot);
    }
    public void overlay(int stroke, int column, String OneLineTxr, boolean spot) {
        overlay(stroke, column, new String[]{OneLineTxr}, spot);
    }
    public void overlay(int stroke, int column, String[] texture, boolean spot) {
        String[] clearedTexture = colorMap.extractColorCodes(stroke, column, texture);
        frame.overlay(stroke, column, clearedTexture, spot);
    }

    public void clearColorMap() { colorMap.clear(); }
    public void clearColorMap(int uStroke, int lColumn, int dStroke, int rColumn, boolean stopingFill) {
        colorMap.clear(uStroke, lColumn, dStroke, rColumn, stopingFill);
    }

    /// Возвращает "раскрашенный" фрейм (с вставленными цветовыми кодами) встраивая Карту цветов в базовыый Фрейм
    private StringBuilder colorizeFrame() {
        StringBuilder colorizedFrame = frame.getCopyOfLINE();

        int shift = 0;
        for (int idCode : colorMap.keySet()) {
            String code = colorMap.get(idCode);
            colorizedFrame.insert(idCode + shift, code);
            shift += code.length();
        }

        return colorizedFrame;
    }

    public void printInputZone(String massage) {
        overlay(52, 0, new String[]{"-".repeat(Config.FRAME_COLUMNS)}, false); // ---------
        messegeLine(massage);
    }

    /// Строка для вывода сообщений в игре
    public void messegeLine(String str) {
        clearColorMap(Config.FRAME_STROKES -2, 4, Config.FRAME_STROKES -1, Config.FRAME_COLUMNS, false);
        overlay(Config.FRAME_STROKES -2, 0, "⪢⪢⪢" + " ".repeat(Config.FRAME_COLUMNS -3), false);
        overlay(Config.FRAME_STROKES -2, 4, str, false);
    }

// Функции класса

}