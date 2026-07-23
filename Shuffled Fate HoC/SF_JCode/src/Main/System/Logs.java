package Main.System;

import Main.UI.UI;

// [СЛОЖНО ВСТРОИТЬ, ВЕРНУТЬСЯ ПОСЛЕ ПОЛНОГО РЕФАКТОРИНГА]
final class Logs {
    private final int
            STROKE = 2, COLUMN = 1,
            HEIGH = 20, WIDTH = 27;

    private final UI ui;

    public Logs(UI ui) {
        this.ui = ui;
        printBackGround();
    }

    public void printBackGround() {
        String backLine = "\u001B[48;2;0;0;0m" + " ".repeat(WIDTH) + "\u001B[0m";
        for (int i = 0; i < HEIGH; i++) {
            ui.gotoRenderer().overlay(STROKE + i, COLUMN, backLine, false);
        }
        ui.gotoRenderer().overlay(STROKE, COLUMN + 1, "Logs:", false);

    }
}
