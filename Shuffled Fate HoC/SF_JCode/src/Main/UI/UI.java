package Main.UI;

import Main.System.Input;
import Main.UI.Texturing.*;
import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;


// Класс UI создан для формирования "Кадровой строки" и вывода её на экран
public class UI {
    private final Renderer render = new Renderer();                      // Рендерер
    private final AssetsManager assets = new AssetsManager();           // Ассеты
    private final Texturing texturing = new Texturing(render, assets); // Текстурные функции

    public UI() {
        System.out.println("Откройте приложение на весь экран (F11) и нажмите Enter ↲ ");
        Input.nextLine();
    }

    public Texturing gotoTexturing() { return texturing; } // Переход в класс Текстурирования
    public Renderer gotoRenderer() { return render; }     // Переход в класс Рендера
    public AssetsManager gotoAssets() { return assets; } // Переход в класс Ассетов

    public void updateScreen() {
        texturing.updateFrameData();
        render.printScreen();
    } // Вывод Фрейма с обновленными данными

    public void printScreen() {
        render.printScreen();
    } // Вывод фрейма на экран

// Функции Класса
    private void clearFrame() {
        System.out.println("\033[H\033[2J"); // Команда очистки экрана (работает немного не так)
    } // TODO: [Потом найду применение. Либо удалю. Мусор]
}
