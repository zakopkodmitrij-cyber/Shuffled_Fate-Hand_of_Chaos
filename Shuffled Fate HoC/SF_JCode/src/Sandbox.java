import TOOLS.Timer;

import Main.Decks.AllDecks.DeckType;
import Main.Decks.Deck;
import Main.System.Input;
import Main.UI.UI;

import java.util.List;
import java.util.ArrayList;

public class Sandbox {
    static Timer t = new Timer();

    public static void main(String[] args) {

    }

    public static void main3(String[] args) {
        UI ui = new UI();

        ui.gotoRenderer().fillFrame('▯');

        ui.gotoRenderer().overlay(0, 0, ".", false);
        ui.gotoRenderer().overlay(54, 178, ".", false);
        ui.gotoRenderer().overlay(3, 20, ui.gotoAssets().getTexture("[ico_Alien]"), false);

        ui.gotoRenderer().printScreen();

        System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        System.out.print("\033[H");
//        System.out.print("\033[H\u001B[2J");
        ui.gotoRenderer().printScreen();
        System.out.print("\u001B[3J");

        Input.waitConfirm();
    }

    public static void main2(String[] args) {
        Deck deck = new Deck(DeckType.BOT);

        for (int i = 0; i < 100; i++) {
            System.out.println(deck.draw().getName());
        }
    }

    public static void main1(String[] args) {
        t.start();

        for (int i = 0; i < 4_000_000; i++) {
            t.detect("i = " + i);
        }
//        t.printLogs();
        System.out.println(t.allTimeMillis());
    }

    public static void main0(String[] args) {
        List<String> map = new ArrayList<>();

        t.detect("stFill");
        fillMap(map);
        System.out.println("enf Fillinf");
        t.detect("endFill");

        t.detect("stIterate");
        iterateAll(map);
        t.detect("endIterate");


        t.printLogs();
    }

    static void fillMap(List<String> map) {
        for (int i = 0; i < 12_000_000; i++) {
            map.add("String" + i);
        }
    }

    static void iterateAll(List<String> map) {
        for (String txtr : map) {
            int a = 0;
        }

    }

}