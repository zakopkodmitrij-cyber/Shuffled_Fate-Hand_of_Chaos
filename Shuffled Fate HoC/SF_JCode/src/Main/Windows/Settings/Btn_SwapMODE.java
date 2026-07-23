package Main.Windows.Settings;

import Main.Windows.Interface.Button;

class Btn_SwapMODE extends Button<Settings> {
    public Btn_SwapMODE(Settings RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(24, 1);
        setFokusKeys("Mode", "m");
        generateTextures(RESPONSIBLE.gotoAssets(), "settings-SwapMODE");

        printCurrentMode();
    }

    @Override
    public void onSelectionActive() { printMicroPointer(); }
    @Override
    public void onSelectionPassive() {
        clearMicroPointerZone();
        printCurrentMode();
    }

    @Override
    public void click() {
        RESPONSIBLE.swapGameMode();
        printMicroPointer();
    }

// Функции класса
    private void printMicroPointer() {
        clearMicroPointerZone();
        RESPONSIBLE.gotoRender().overlay(coord_stroke - 1, getColmnAtMode(),
                RESPONSIBLE.gotoAssets().getTexture("[Int_microPointer]"), false);
    }

    private void clearMicroPointerZone() {
        RESPONSIBLE.gotoRender().clearAll(coord_stroke - 1, coord_column + 14,
                coord_stroke, coord_column + 46, false);
    }

    private int getColmnAtMode() {
        return switch (RESPONSIBLE.gameMode) {
            case PvP -> 19;
            case PvE -> 30;
            case EvE -> 41;
        };
    }

    private void printCurrentMode() {
        RESPONSIBLE.gotoRender().overlay(coord_stroke, coord_column + 16, getTxtrCurMode(), false); }

    private String[] getTxtrCurMode() {
        String key = switch (RESPONSIBLE.gameMode) {
            case PvP -> "[Itn_Mode.PvP-miniwi]";
            case PvE -> "[Itn_Mode.PvE-miniwi]";
            case EvE -> "[Itn_Mode.EvE-miniwi]";
        };

        return RESPONSIBLE.gotoAssets().getTexture(key);
    }

}
