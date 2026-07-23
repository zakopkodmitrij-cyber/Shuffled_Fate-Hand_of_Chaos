package Main.Windows.Settings;

import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Dyad;
import Main.Entities.Dyad.Player;
import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;
import Main.Windows.Window;

enum GameMode { PvP, PvE, EvE }
enum Language { Castom, Eng, Rus }

public class Settings extends Window {
    GameMode gameMode = GameMode.PvP;
    Language language = Language.Castom;

    final Dyad dyad;

    public Settings(AssetsManager assets, Dyad dyad) {
        super(assets);
        render.fillFrame('0');
        this.dyad = dyad;

        setInteractionableElements(
                new Btn_SwapMODE(this),
                new Btn_SwapLANG(this),
                new Btn_APPLY(this),
                new Btn_QUIT(this)
        );
    }

    Renderer gotoRender() { return render; }
    AssetsManager gotoAssets() { return  assets; }

    @Override
    protected void printInterface() {
        render.overlay(2, 2, assets.getTexture("[Int_titleSettings-Slant]"), false);
        render.printInputZone("Вы вошли в окно настроек.");
    }

    void swapGameMode() {
        gameMode = switch (gameMode) {
            case PvP -> GameMode.PvE;
            case PvE -> GameMode.EvE;
            case EvE -> GameMode.PvP;
        };
        render.messegeLine("Режим игры изменён на " + gameMode);
    }

    void swapLanguage() {
        language = switch (language) {
            case Castom -> Language.Eng;
            case Eng -> Language.Rus;
            case Rus -> Language.Castom;
        };
        render.messegeLine("Язык изменён на " + language);
    }


    void applyChanges() {
        configureGameMode();
        configureLanguage();
        render.messegeLine("Изменения примемены");
    }

// Функции класса
    private void configureGameMode() {
        switch (gameMode) {
            case PvP -> {
                dyad.Evil = new Player(dyad.Evil);
            }
            case PvE -> {
                dyad.Evil = new Soul(dyad.Evil, dyad.Hero);
            }
            case EvE -> {
                dyad.Hero = new Soul(dyad.Hero, dyad.Evil);
                dyad.Evil = new Soul(dyad.Evil, dyad.Hero);
            }
        }
    }

    private void configureLanguage() {
        switch (language) {
            case Castom -> assets.loadTexturesFrom(AssetsManager.pathAssetsDir + "Castom_lang");
            case Eng ->    assets.loadTexturesFrom(AssetsManager.pathAssetsDir + "Eng_lang");
            case Rus ->    assets.loadTexturesFrom(AssetsManager.pathAssetsDir + "Rus_lang");
        }
    }


    private void swapName(Player player, String[] tokens) {
        String newName;
        if (tokens.length < 2) {
            render.messegeLine("Смена имени для " + player.getName());
            render.printScreen();
            newName = inputNotEmptyName();
        } else
            newName = tokens[1];

        player.setName(newName);
        render.messegeLine("Имя сменено на " + player.getName());
    }

    private String inputNotEmptyName() {
        System.out.print("Новое имя: ");
        String name = ""; //Input.nextLine();

        while (name.isEmpty()) {
            render.messegeLine("Дайте своему персонажу имя");
            render.printScreen();
            System.out.print("Новое имя: ");

            name = ""; //Input.nextLine();
        }

        return name;
    }

}
