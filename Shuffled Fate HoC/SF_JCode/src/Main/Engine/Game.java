package Main.Engine;

import Main.System.Profiler;
import Main.UI.UI;
import Main.Windows.Menu.Menu;

public class Game {
    private final UI ui = new UI();

    public Game() {
        Profiler.on();
    }

    public void start() {
        Profiler.recordGameStartTime();

        new Menu(ui).display();

    }

// Функции класса

}
