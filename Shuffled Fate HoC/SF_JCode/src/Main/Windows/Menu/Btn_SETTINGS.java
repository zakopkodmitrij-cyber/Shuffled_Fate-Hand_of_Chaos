package Main.Windows.Menu;

import Main.Windows.Interface.Button;

class Btn_SETTINGS extends Button<Menu> {
    public Btn_SETTINGS(Menu RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(36, 70);
        setFokusKeys("Settings", "set", "2");
        generateTextures(RESPONSIBLE.gotoAssets(), "menu-SETTINGS");
    }

    @Override
    public void click() { RESPONSIBLE.settings.display(); }

}
