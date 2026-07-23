package Main.Windows.Settings;

import Main.Windows.Interface.Button;

class Btn_SwapLANG extends Button<Settings> {
    public Btn_SwapLANG(Settings RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(46, 1);
        setFokusKeys("Lang", "L", "l");
        generateTextures(RESPONSIBLE.gotoAssets(), "settings-SwapLANG");

        printCurrentLang();
    }

    @Override
    public void onSelectionActive() { printMicroPointer(); }
    @Override
    public void onSelectionPassive() {
        clearMicroPointerZone();
        printCurrentLang();
    }

    @Override
    public void click() {
        RESPONSIBLE.swapLanguage();
        printMicroPointer();
    }

// Функции класса
    private void printMicroPointer() {
        clearMicroPointerZone();
        RESPONSIBLE.gotoRender().overlay(coord_stroke - 1, getColmnAtLang(),
                RESPONSIBLE.gotoAssets().getTexture("[Int_microPointer]"), false);
    }

    private void clearMicroPointerZone() {
        RESPONSIBLE.gotoRender().clearAll(coord_stroke - 1, coord_column,
                coord_stroke, coord_column + 60, false);
    }

    private int getColmnAtLang() {
        return switch (RESPONSIBLE.language) {
            case Castom -> 28;
            case Eng -> 43;
            case Rus -> 54;
        };
    }

    private void printCurrentLang() {
        RESPONSIBLE.gotoRender().overlay(coord_stroke, coord_column + 22, getTxtrCurLang(), false); }

    private String[] getTxtrCurLang() {
        String key = switch (RESPONSIBLE.language) {
            case Castom -> "[Itn_Lang.Castom-miniwi]";
            case Eng -> "[Itn_Lang.Eng-miniwi]";
            case Rus -> "[Itn_Lang.Rus-miniwi]";
        };

        return RESPONSIBLE.gotoAssets().getTexture(key);
    }

}
