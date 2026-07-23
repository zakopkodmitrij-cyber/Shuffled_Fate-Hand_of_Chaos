package Main.UI.Render;

class Frame {
    private final StringBuilder LINE;
    private int cntStrokes, cntColumns;

    Frame(int strokes, int columns) {
        this.cntStrokes = strokes;
        this.cntColumns = columns;
        LINE = new StringBuilder().append(" ".repeat(cntStrokes * cntColumns));
    }

    public StringBuilder getCopyOfLINE() { return new StringBuilder(LINE); }

    public int getCntColumns() { return cntColumns; }
    public int getCntStrokes() { return cntStrokes; }

    public void setResolution(int strokes, int columns) {
        this.cntStrokes = strokes;
        this.cntColumns = columns;
        LINE.setLength(strokes * columns); fill(' ');
    }

    public void clear() { fill(' '); }

    public void clear(int uStroke, int lColumn, int dStroke, int rColumn) {
        chekLegalIndexes(uStroke, lColumn, dStroke, rColumn);

        String voidStr = " ".repeat(rColumn - lColumn);

        for (int iStroke = uStroke; iStroke < dStroke; iStroke++) {
            int start = iStroke*cntColumns + lColumn;
            int end   = iStroke*cntColumns + rColumn;

            LINE.replace(start, end, voidStr);
        }

    }

    /// Заполняет весь врейм переданным символом
    public void fill(char c) {
        for (int i = 0; i < LINE.length(); i++)
            LINE.setCharAt(i, c);
    }
    public void fill0(char c) {
        LINE.setLength(0);
        LINE.append( Character.toString(c).repeat(cntStrokes * cntColumns));
    } // TODO: Провести замеры скорости


    /** Функцияя наложения текстуры на фрейм по координатам <br>
     * (Корректно работает с текстурами на границах фрейма [КРОМЕ ЛЕВОЙ]) <br>
     */
    public void overlay(int stroke, int column, String OneLineTxr, boolean spot) {
        overlay(stroke, column, new String[]{OneLineTxr}, spot);
    }
    public void overlay(int stroke, int column, String[] texture, boolean spot) {
        if (stroke >= cntStrokes || column >= cntColumns) throw new IllegalArgumentException();

        for (int iTxrLine = 0; iTxrLine < texture.length; iTxrLine++) {
            if ( (stroke + iTxrLine) >= cntStrokes ) return; // Выход, если ушли ниже видимости фрейма

            String txrLine = texture[iTxrLine]; // Текущая строка переданой Текстуры
            if (txrLine.isEmpty()) continue;   // Пропускаем .replace() если строка пустая

            int iSt = ((stroke + iTxrLine) * cntColumns) + column; // Индекс начала выводимой линии текстуры в Кадровой строке
            int iEnd = iSt + txrLine.length();                    // Индекс конца

            // Включает точечный режим (заменяет все пробелы на символы из имеющегося фрейма)
            if (spot) {
                char[] chars = txrLine.toCharArray();

                for (int i = 0; i < chars.length; i++)
                    if (chars[i] == ' ') chars[i] = LINE.charAt(iSt + i);

                txrLine = new String(chars);
            }

            if ( (iEnd / cntColumns) <= (stroke + iTxrLine) )
                LINE.replace(iSt, iEnd, txrLine);
            else
                LINE.replace(
                        iSt, (stroke + iTxrLine + 1) * cntColumns,
                        txrLine.substring(0, cntColumns - column)
                );

        }
    }


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
