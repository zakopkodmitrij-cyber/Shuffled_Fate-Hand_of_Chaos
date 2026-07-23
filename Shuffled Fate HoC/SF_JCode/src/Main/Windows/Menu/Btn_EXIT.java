package Main.Windows.Menu;

import Main.Windows.Interface.Button;

class Btn_EXIT extends Button<Menu> {
    public Btn_EXIT(Menu RESPONSIBLE) {
        super(RESPONSIBLE);
        setCoord(40, 70);
        setFokusKeys("Exit", "E", "e", "3");
        generateTextures(RESPONSIBLE.gotoAssets(), "menu-EXIT");
    }

    @Override
    public void click() {
        RESPONSIBLE.gotoRender().clearAll();
        RESPONSIBLE.gotoRender().overlay(0, 0,
                RESPONSIBLE.gotoAssets().getTexture("[Int_Farewel]"), false);
        RESPONSIBLE.gotoRender().printScreen();

        System.exit(0);
    }

}
