package Main.UI.Render;

import Main.Engine.core.Config;

import java.util.regex.Matcher;

// Идея создать обширную структуру данных для текстур, которая будет содержать некоторые данные,
// относящиеся конкретно к этой текстуре
// Пока просто не совсем понятно, насколько это всё будет полезно
public class Texture {
    public String[] data = new String[]{""};
    public int coord_stroke, coord_column;

    public Texture() {}

    public Texture(int coord_stroke, int coord_column, String[] data) {
        this.coord_stroke = coord_stroke;
        this.coord_column = coord_column;
        this.data = data;
    }

    public int getHeight() {
        int i = data.length - 1;
        while (i > -1) {
            if (!data[i].isEmpty())
                break;
            i--;
        }
        return i + 1;
    }

    public int getWidth() {
        Matcher matcher;
        int maxWidh = 0;

        for (String str : data) {
            matcher = Config.ANSI_ESCAPE_PATTERN.matcher(str);
            String clearStr = matcher.replaceAll("");


            if (clearStr.length() > maxWidh)
                maxWidh = clearStr.length();
        }

        return maxWidh;
    }

    public void clearSpace(Renderer render) {
        render.clearAll(coord_stroke, coord_column,
                coord_stroke + getHeight(), coord_column + getWidth(),
                false);
    }

}
