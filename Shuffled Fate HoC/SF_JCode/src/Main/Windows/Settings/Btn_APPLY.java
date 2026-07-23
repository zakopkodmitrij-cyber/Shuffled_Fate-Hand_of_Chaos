package Main.Windows.Settings;

import Main.Windows.Interface.Button;

class Btn_APPLY extends Button<Settings> {
    public Btn_APPLY(Settings RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(47, 157);
        setFokusKeys("Apply", "App", "app", "a");
        generateTextures(RESPONSIBLE.gotoAssets(), "settings-APPLY");
    }

    @Override
    public void click() {
        RESPONSIBLE.applyChanges();
        printChekMark();
    }

// Функции класса
    private void printChekMark() {
        RESPONSIBLE.gotoRender().overlay(coord_stroke, coord_column + 14,
                RESPONSIBLE.gotoAssets().getTexture("[Itn_Apply.ChekMark-miniwi]"), false);
    }
}
