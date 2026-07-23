package Main.Engine.gameFunctions;

import Main.Cards.Card;
import Main.Cards.NotEnoughManaException;
import Main.Entities.Dyad.Player;

import Main.Windows.HandView.Hand_View;
import Main.UI.UI;

/// Ожидает ввода Игроком команды и исполняет её
public class Commands {
    private DeveloperMode devMode;

    private String input;
    private Player caller, another;
    private final UI ui;
    Hand_View hand_view;

    public Commands(UI ui) {
        this.ui = ui;
        this.hand_view = new Hand_View(ui.gotoAssets());
    }

    public void callCommand(Player caller, Player another) {
        this.caller = caller;
        this.another = another;

        exeCommand();
    }

    private void exeCommand() {
        ui.updateScreen();

        System.out.print("Команда: ");
        input = caller.inputCommand();

        String[] tokens = input.split("\\s+");
        switch (tokens[0]) {
            case "":
                ui.gotoRenderer().messegeLine("Введите команду ⇙"); exeCommand(); break;
            case "play": case "p":
                for (int i = 1; i < tokens.length; i++)
                    play(Integer.parseInt(tokens[i]) - 1);
                exeCommand(); break;
            case "drop": case "d":
                for (int iToken = 1; iToken < tokens.length; iToken++)
                    dropCard(Integer.parseInt(tokens[iToken]) - 1);
                exeCommand(); break;
            case "swap": case "sw":
                for (int i = 1; i < tokens.length - 1; i += 2)
                    swapCards(Integer.parseInt(tokens[i]) - 1, Integer.parseInt(tokens[i+1]) - 1);
                exeCommand(); break;
            case "show": case "sh":
                if (tokens.length > 1)
                    show(Integer.parseInt(tokens[1]) - 1);
                else
                    show(0);
                exeCommand(); break;
            case "gup":
                giveUp();
                break;
/*
            case "":
                break;
            case "":
                break;
*/
            case "help":
                help(); exeCommand();
                break;
            case "end": case "e":
                end(); break;

            case "EXIT": case "EX":
                System.exit(0);
            case "-dev": case "-d":
                developCommands(tokens);
                exeCommand(); break;
            default:
                invalidCommand();
                exeCommand();
        }
    }

    // Команды
    private void play(int iCard) {
        Card currentCard = null;

        try {
            currentCard = caller.gotoHand().getCard(iCard);
            caller.gotoHand().playCard(iCard, caller, another);
        }
        catch (NotEnoughManaException e) {
            ui.gotoRenderer().messegeLine("Недостаточно \u001B[38;2;100;100;255mМАНЫ\u001B[m для использования карты " + currentCard.getName());
            return;
        }
        catch (IndexOutOfBoundsException e) {
            ui.gotoRenderer().messegeLine("Указан неверный номер карты для использования (всего " + caller.gotoHand().toList().size() + " карт)");
            return;
        }

        String defaultMassage = caller.getName() + " использовал карту " + currentCard.getName();
        displayCardMessage(caller.gotoHand().getLastUsed(), defaultMassage);
    }

    private void dropCard(int iCard) {
        Card currentCard = caller.gotoHand().getCard(iCard);

        try {
            caller.gotoHand().dropCard(iCard, caller, another);
        }
        catch (IndexOutOfBoundsException e) {
            ui.gotoRenderer().messegeLine("Указан неверный номер карты для сброса");
            return;
        }

        String defaultMassage = caller.getName() + " сбросил карту " + caller.gotoHand().getLastUsed().getName();
        displayCardMessage(currentCard, defaultMassage);
    }

    private void swapCards(int icard_1, int icard_2) {
        try {
            caller.gotoHand().swapCards(icard_1, icard_2);
        }
        catch (IndexOutOfBoundsException e) {
            invalidCommand();
            return;
        }

        ui.gotoRenderer().messegeLine("Карты переложены");
    }

    private void show(int iCurrentCd) {
        try {
            hand_view.viewing(caller, iCurrentCd);
        } catch (IndexOutOfBoundsException e) {
            invalidCommand();
            return;
        }

        ui.gotoRenderer().messegeLine("Вы закончили просмотр Руки");
    }

    private void giveUp() { // Хотелось бы моментального завершения без обнуления HP
        caller.setHP(0);
        ui.gotoRenderer().messegeLine(caller.getName() + " сдаётся.");
    }

    private void help() {
        ui.gotoRenderer().messegeLine(
            "'play \u001B[1;4mN\u001B[m' " +
                "'drop \u001B[1;4mN\u001B[m' " +
                "'swap \u001B[1;4mA\u001B[m \u001B[1;4mB\u001B[m' " +
                "'show' " +
                "'end' " +
                "'gup' " +
                "'EXIT'"); }

    private void end() {
        ui.gotoRenderer().messegeLine("Ход переходит к " + another.getName());
    }

    private void invalidCommand() {
        ui.gotoRenderer().messegeLine("Команда \"" + input + "\" не существует. Повторите ввод =>");
        ui.gotoRenderer().overlay(53, 160, "( 'help' — помощь )", false);
    }

    private void developCommands(String[] tokens) {
        devMode = new DeveloperMode(caller, another);
        ui.gotoRenderer().messegeLine(devMode.exeDevCommand(tokens));
    }

// Функции класса
    private void displayCardMessage(Card card, String defaultMassage) {
        if (card.getMassage() != null)
            ui.gotoRenderer().messegeLine(card.getMassage());
        else
            ui.gotoRenderer().messegeLine(defaultMassage);
    }

}
