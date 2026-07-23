package Main.Windows.Menu;

import Main.Windows.Interface.Button;

class Btn_START extends Button<Menu> {
    public Btn_START(Menu RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(32, 70);
        setFokusKeys("Start", "s", "1");
        generateTextures(RESPONSIBLE.gotoAssets(), "menu-START");
    }

    @Override
    public void click() { RESPONSIBLE.gameRound.GameCycle(); }

}
