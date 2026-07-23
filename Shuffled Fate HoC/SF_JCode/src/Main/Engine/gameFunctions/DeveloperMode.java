package Main.Engine.gameFunctions;

import Main.Cards.Card;
import Main.Cards.CardBuilder;
import Main.Entities.Dyad.Player;
import Main.System.Profiler;

// Класс DeveloperMode содержи специальные функции для управления игрой
public class DeveloperMode {
    private Player caller;
    private Player enother;

    public DeveloperMode(Player caller, Player enother) {
        this.caller = caller;
        this.enother = enother;
    }

    public String exeDevCommand(String[] tokens) {
        if (tokens.length < 3) return "-dev: Параметры не переданы.";

        switch (tokens[1]) {
            case "add": {
                try {
                    Card card = CardBuilder.buildCard(tokens[2]);
                    caller.gotoHand().addCard(card, caller, enother);
                    return "-dev: Добавлена карта " + card.getName();
                } catch (IllegalArgumentException e) {
                    return "-dev: Неверное имя карты \"" + tokens[2] + "\"";
                }
            }
            case "add0": {
                try {
                    Card card = CardBuilder.buildCard(tokens[2]);
                    enother.gotoHand().addCard(card, enother, caller);
                    return "-dev: Добавлена карта " + card.getName();
                } catch (IllegalArgumentException e) {
                    return "-dev: Неверное имя карты \"" + tokens[2] + "\"";
                }
            }
            case "del": {
                int iCard = Integer.parseInt(tokens[2]) - 1;
                if (0 <= iCard && iCard < caller.gotoHand().toList().size()) {
                    String cardName = caller.gotoHand().getCard(iCard).getName();
                    caller.gotoHand().delCard(iCard, caller, enother);
                    return "-dev: Удалена карта номер %s (%s)".formatted(tokens[2], cardName);
                }
                return "-dev: Неверный номер карты для удаления.";
            }
            case "del0": {
                int iCard = Integer.parseInt(tokens[2]) - 1;
                if (0 <= iCard && iCard < caller.gotoHand().toList().size()) {
                    String cardName = caller.gotoHand().getCard(iCard).getName();
                    enother.gotoHand().delCard(iCard, caller, enother);
                    return "-dev: Удалена карта номер %s (%s)".formatted(tokens[2], cardName);
                }
                return "-dev: Неверный номер карты для удаления.";
            }
            case "setHP": {
                caller.setHP(Integer.parseInt(tokens[2]));
                break; }
            case "setHP0": {
                enother.setHP(Integer.parseInt(tokens[2]));
                break; }
            case "setMN": {
                caller.setMN(Integer.parseInt(tokens[2]));
                break; }
            case "setMN0": {
                enother.setMN(Integer.parseInt(tokens[2]));
                break; }
            case "setIco": {
                caller.setIcoKey(tokens[2]);
                break; }
            case "setIco0": {
                enother.setIcoKey(tokens[2]);
                break; }

            case "new": {
                caller.rebuild(new Player(tokens[2]));
                break; }
            case "new0": {
                enother.rebuild(new Player(tokens[2]));
                break; }

//            case "capacity", "cap": {
//                Config.HAND_CAPACITY = Integer.parseInt(tokens[2]);
//                break; }
            case "Profiler", "prof": {
                return setProfilerMode(tokens[2]); }
            case "gc": {
                System.gc();
                break; }

            default:
                return "-dev: Команда \"" + tokens[1] + "\" не действительна.";
        }
        return "-dev: Команда выполнена.";
    }

// Функции класса
    private String setProfilerMode(String mode) {
        return switch (mode) {
            case "on" -> {
                Profiler.on();
                yield "-dev: Profiler ON"; }
            case "off" -> {
                Profiler.off();
                yield "-dev: Profiler OFF"; }
            default -> "-dev: Неверный режим для Profiler";
        };
    }
}
