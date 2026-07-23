package Main.Windows.HandView;

import Main.Engine.core.Config;
import Main.Entities.Dyad.Player;
import Main.System.Input;
import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;

/// Переводит игру в ружим просмотра Карт в руке Игрока
public class Hand_View {
    private final Renderer render = new Renderer();
    private final AssetsManager assets;

    private String[] handNames;    // Массив строк названий кард Руки
    private int iCurrentCard = 0; // Индекс текущей просматриваемой Карты
    private int column;          // Столбец для отображения карт по центру экрана

    public Hand_View(AssetsManager assets) {
        this.assets = assets;
    }

    public void viewing(Player player, int iCard) throws IndexOutOfBoundsException{
        handNames = player.gotoHand().showHand();
        if (!isCorrektIndexOfCard(iCard)) throw new IndexOutOfBoundsException();

        iCurrentCard = iCard;

        render.clearFrame(); render.clearColorMap();
        render.overlay(46, 0, assets.getTexture("[Hand_View-Navigation]"), false);
        render.overlay(52, 0, new String[]{"-".repeat(179)}, false);
        render.messegeLine("Вы перешли в режим просмотра Руки.");

        column = ((Config.FRAME_COLUMNS - (11*handNames.length - 1)) / 2) - 2;

        exeComandHV();
    }

// Функции класса
    private boolean isCorrektIndexOfCard(int iCard) {
        return (0 <= iCard && iCard < handNames.length);
    }

    private void exeComandHV() {
        render.messegeLine("Выбрана карта номер " + (iCurrentCard + 1));
        showCardDescription();

        System.out.print("Команда: ");
        String input = Input.nextLine();

        String[] tokens = input.split("\\s+");
        switch (tokens[0]) {
            case "next": case "n":
                if (tokens.length == 1) {
                    iCurrentCard++;
                    if (iCurrentCard > handNames.length - 1) iCurrentCard = 0;
                } else {
                    iCurrentCard = Integer.parseInt(tokens[1]) - 1; }

                exeComandHV(); break;
            case "back": case "b":
                iCurrentCard--;
                if (iCurrentCard < 0) iCurrentCard = handNames.length-1;

                exeComandHV(); break;
            case "quit": case "q":
                break;
            default:
                render.messegeLine("Команда \"" + input + "\" не существует. Повторите ввод =>");
                exeComandHV();
        }
    }

    private void showCardDescription() {
        for (int i = 0; i < 44; i++)
            render.overlay(i, 0, " ".repeat(179), false);

        render.clearColorMap();
        render.overlay(45, 0, assets.getTexture("[Hand_View-Navigation]"), false);

        int nCard = 0;
        for (int i = 0; i < handNames.length; i++, nCard++) {
            String[] txrCard = assets.getTexture( "[Cd_%s]".formatted( handNames[i] ) );
            render.overlay(44, column + i*12, txrCard, false);
        }

        String[] cardDescription = assets.getTexture("[Cd_%s-Description]".formatted(handNames[iCurrentCard]));
        render.overlay(0, 30, cardDescription, false);

        // Выдвигание Выбранной Карты в нижнем перечне Карт
        int iColumnCurCd = column + 12*iCurrentCard;
        render.clearColorMap(44, iColumnCurCd, 50, iColumnCurCd+10, false); // Очистка цветов на месте выдвигаемой Карты
        String[] txrCurCd = assets.getTexture("[Cd_%s]".formatted(handNames[iCurrentCard]));
        render.overlay(43, iColumnCurCd, txrCurCd, false);
        render.overlay(50, iColumnCurCd, "          ", false);

        System.out.println(render.getScreen());
    }
}