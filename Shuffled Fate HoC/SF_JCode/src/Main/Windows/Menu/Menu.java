package Main.Windows.Menu;

import Main.Engine.GameRound;
import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;
import Main.Windows.Settings.Settings;
import Main.Windows.Window;
import Main.UI.UI;

public class Menu extends Window {
    final GameRound gameRound;
    final Settings settings;

    public Menu(UI ui) {
        super(ui.gotoAssets());

        gameRound = new GameRound(ui);
        settings = new Settings(gameRound.getUi().gotoAssets(), gameRound.getDyad());

        setInteractionableElements(
                new Btn_START(this),
                new Btn_SETTINGS(this),
                new Btn_EXIT(this)
        );
    }

    @Override
    protected void printInterface() {
        render.overlay(7, 22, assets.getTexture("[TS_Terrace]"), false);
        render.printInputZone("Приветствую в Shuffled Fate: Hand of Chaos !!!");
    }


    Renderer gotoRender() { return render; }
    AssetsManager gotoAssets() { return  assets; }

}