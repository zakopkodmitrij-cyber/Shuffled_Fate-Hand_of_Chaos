package Main.Engine.core;

import java.util.regex.Pattern;

/// Содержит константные значения кода
public class Config {
    private Config() {}

    public static final int FRAME_STROKES = 55;
    public static final int FRAME_COLUMNS = 179;

    /// Шаблон ANSI-кода
    public static final Pattern ANSI_ESCAPE_PATTERN = Pattern.compile("\\u001B\\[[0-9;?]*[a-zA-Z]");

    public static final int HAND_CAPACITY = 9; // < 15

    public static int BOT_WAITING_MILLIS = 0;
}
