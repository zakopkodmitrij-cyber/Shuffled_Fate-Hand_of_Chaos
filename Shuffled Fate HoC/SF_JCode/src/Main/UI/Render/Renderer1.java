package Main.UI.Render;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;

import Main.Engine.core.Config;
import Main.System.Profiler;

public class Renderer1 {
    private final Frame frame = new Frame(Config.FRAME_STROKES, Config.FRAME_COLUMNS);
    private final Map<Integer, String> colorMap = new TreeMap<>(); // Хранит индексы Фрейма куда были 'вставлены' ESC-последовательности для цветного вывода

    private final ColorMap _colorMap = new ColorMap(frame); //---

    public Renderer1() {}

    public Renderer1(int strokes, int columns) {
        frame.setResolution(strokes, columns);
//        _colorMap.setResolution(strokes, columns);
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
        String[] clearedTexture = extractColorCodes(stroke, column, texture);
        frame.overlay(stroke, column, clearedTexture, spot);
    }

    public void clearColorMap() { colorMap.clear(); }

    public void clearColorMap(int uStroke, int lColumn, int dStroke, int rColumn, boolean stopingFill) {
        chekLegalIndexes(uStroke, lColumn, dStroke, rColumn);

        int ind; // Ключ-индекс под удаление в colorMap
        for (int iStroke = uStroke; iStroke < dStroke; iStroke++) {
            for (int iColumn = lColumn; iColumn < rColumn; iColumn++) {
                ind = iStroke*Config.FRAME_COLUMNS + iColumn;
                colorMap.remove(ind);
            }

            if (stopingFill)
                colorMap.put(iStroke*Config.FRAME_COLUMNS + lColumn, "\u001B[m"); // Ставит [0m в начале очищаемых строк
        }
    }

    /// Возвращает "раскрашенный" фрейм (с вставленными цветовыми кодами) встраивая Карту цветов в базовыый Фрейм
    public StringBuilder colorizeFrame() {
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
    private void chekLegalIndexes(int uStroke, int lColumn, int dStroke, int rColumn) {
        if (
                (uStroke < 0 || Config.FRAME_STROKES < uStroke) ||
                (lColumn < 0 || Config.FRAME_COLUMNS < lColumn) ||
                (dStroke < 0 || Config.FRAME_STROKES < dStroke) ||
                (rColumn < 0 || Config.FRAME_COLUMNS < rColumn) ||
                (dStroke < uStroke || rColumn < lColumn)
        ) throw new IllegalArgumentException();
    }

    private String[] extractColorCodes(int stroke, int column, String[] texture) {
        String[] textureClone = texture.clone();

        for (int i = 0; i < textureClone.length; i++)
            textureClone[i] = extractColorCodesFromString(stroke + i, column, textureClone[i]);
        return textureClone;
    } // Извлекает из Текстуры Цветове коды и добавляет их в буфер Цветовых кодов, возвращает чистую текстуру без кодов

    private String extractColorCodesFromString(int stroke, int column, String str) {
        Matcher matcher; // Матчер для переданной Текстурной строки

        while (true) {
            matcher = Config.ANSI_ESCAPE_PATTERN.matcher(str);
            if (!matcher.find()) break; // Если нет совпадений - выходим

            int iStart = matcher.start();
            int iEnd = matcher.end();

            colorMap.put((stroke * Config.FRAME_COLUMNS + column) + iStart, str.substring(iStart, iEnd)); // Добавление найденного Кода в Цветовую карту

            str = matcher.replaceFirst(""); // [ПОЗЖЕ ОПТИМИЗИРОВАТЬ ЧЕРЕЗ StringBuilder]
        }

        return str;
    } // Извлекает из Текстурной строки Цветове коды и добавляет их в буфер Цветовых кодов, возвращает чистую строку без кодов
}
