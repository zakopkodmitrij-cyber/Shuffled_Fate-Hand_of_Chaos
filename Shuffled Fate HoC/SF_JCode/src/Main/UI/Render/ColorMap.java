package Main.UI.Render;

import Main.Engine.core.Config;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;

// [В ПРОЦЕССЕ ВЫДЕЛЕНИЯ]
class ColorMap {
    private final Map<Integer, String> MAP = new TreeMap<>(); // Хранит индексы Фрейма куда были 'вставлены' ESC-последовательности для цветного вывода
    private int cntStrokes, cntColumns;

    ColorMap(Frame frame) {
        this.cntStrokes = frame.getCntStrokes();
        this.cntColumns = frame.getCntColumns();
    }

    public int getCntColumns() { return cntColumns; }
    public int getCntStrokes() { return cntStrokes; }

    public void setResolution(int strokes, int columns) {
        this.cntStrokes = strokes;
        this.cntColumns = columns;
        MAP.clear();
    }

    public Set<Integer> keySet() { return MAP.keySet(); }
    public String get(int key) { return MAP.get(key); }

    public void clear() { MAP.clear(); }

    public void clear(int uStroke, int lColumn, int dStroke, int rColumn, boolean stopingFill) {
        chekLegalIndexes(uStroke, lColumn, dStroke, rColumn);

        int ind; // Ключ-индекс под удаление в colorMap
        for (int iStroke = uStroke; iStroke < dStroke; iStroke++) {
            for (int iColumn = lColumn; iColumn < rColumn; iColumn++) {
                ind = iStroke* cntColumns + iColumn;
                MAP.remove(ind);
            }

            if (stopingFill)
                MAP.put(iStroke*cntColumns + lColumn, "\u001B[m"); // Ставит [0m в начале очищаемых строк
        }
    }

    /// Извлекает из Текстуры Цветове коды и добавляет их в буфер Цветовых кодов, возвращает чистую текстуру без кодов
    public String[] extractColorCodes(int stroke, int column, String[] texture) {
        String[] textureClone = texture.clone();

        for (int i = 0; i < textureClone.length; i++)
            textureClone[i] = extractColorCodesFromString(stroke + i, column, textureClone[i]);

        return textureClone;
    } // Извлекает из Текстуры Цветове коды и добавляет их в буфер Цветовых кодов, возвращает чистую текстуру без кодов

    // Функции класса
    private String extractColorCodesFromString(int stroke, int column, String str) {
        Matcher matcher; // Матчер для переданной Текстурной строки

        while (true) {
            matcher = Config.ANSI_ESCAPE_PATTERN.matcher(str);
            if (!matcher.find()) break; // Если нет совпадений - выходим

            int iStart = matcher.start();
            int iEnd = matcher.end();

            MAP.put((stroke * cntColumns + column) + iStart, str.substring(iStart, iEnd)); // Добавление найденного Кода в Цветовую карту

            str = matcher.replaceFirst(""); // [ПОЗЖЕ ОПТИМИЗИРОВАТЬ ЧЕРЕЗ StringBuilder]
        }

        return str;
    } // Извлекает из Текстурной строки Цветове коды и добавляет их в буфер Цветовых кодов, возвращает чистую строку без кодов
    
    private void chekLegalIndexes(int uStroke, int lColumn, int dStroke, int rColumn) {
        if (
                (0 <= uStroke && uStroke <= cntStrokes) &&
                        (0 <= lColumn && lColumn <= cntColumns) &&
                        (0 <= dStroke && dStroke <= cntStrokes) &&
                        (0 <= rColumn && rColumn <= cntColumns) &&
                        (uStroke <= dStroke && lColumn <= rColumn))
            return;
        else
            throw new IllegalArgumentException();
    }
    
}
