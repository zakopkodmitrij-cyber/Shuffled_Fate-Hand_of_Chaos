package Main.Windows.Settings;

import Main.Windows.Interface.Button;

class Btn_QUIT extends Button<Settings> {
    public Btn_QUIT(Settings RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(0, 0);
        setFokusKeys("Quit", "quit", "Q", "q");
        generateTextures(RESPONSIBLE.gotoAssets(), "settings-QUIT");
    }

    @Override
    public void onSelectionActive() {
        RESPONSIBLE.gotoRender().messegeLine(RESPONSIBLE.gotoAssets().getTexture("[Mes_settings-QUIT]")[0]);
    }

    @Override
    public void click() {
//        RESPONSIBLE.applyChanges();
        RESPONSIBLE.prepareToQuite();
    }

// Функции класса

}
