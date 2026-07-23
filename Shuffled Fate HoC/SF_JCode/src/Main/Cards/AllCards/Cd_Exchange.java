package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;
import Main.System.Input;

/// Размен - позволяет конвертировать HP ⇆ MN в соотношении 2HP/3MN
public class Cd_Exchange extends Card {
    private boolean used = false;
    private int exchanges;

    public Cd_Exchange() { super("Exchange", 0); }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        printUse();

        while (true) {
            System.out.print("Конвертация \u001B[38;2;100;100;255mМАНЫ\u001B[m в \u001B[38;2;100;255;100mЗДОРОВЬЕ\u001B[m. " +
                    "Число конверсий: ");
            String input = caster.inputAddCommand();

            try {
                exchanges = Integer.parseInt(input);
            } catch (NumberFormatException e){
                continue;
            }

            if (exchanges <= -1) {
                drop(iCard, caster, enemy); return; }

            if (exchanges * 3 <= caster.getMN()) {
                caster.changeMN(-(exchanges * 3));
                caster.changeHP(+(exchanges * 2));

                setMassage(caster.getName() + " использовал карту \u001B[38;2;100;255;255mExchange\u001B[m " +
                        "(\u001B[38;2;100;100;255m%d MN\u001B[m → \u001B[38;2;100;255;100m%d HP\u001B[m)"
                                .formatted(exchanges*3, exchanges*2));
                break;
            } else {
                System.out.println("\nУ вас недостаточно \u001B[38;2;100;100;255mМАНЫ\u001B[m для проведения такого обмена."); }
        }

//        del(caster, enemy, iCard);
    }

    @Override
    public void drop(int iCard, Player caster, Player enemy) {
        printUse();

        while (true) {
            System.out.print("Конвертация \u001B[38;2;100;255;100mЗДОРОВЬЯ\u001B[m в \u001B[38;2;100;100;255mМАНУ\u001B[m. " +
                    "Число конверсий: ");
            exchanges = Input.nextInt();
            if (exchanges <= -1) {
                play(caster, enemy, iCard); return; }

            if (exchanges * 2 <= caster.getHP() - 1) {
                caster.changeHP(-(exchanges * 2));
                caster.changeMN(+(exchanges * 3));

                setMassage(caster.getName() + " использовал карту \u001B[38;2;100;255;255mExchange\u001B[m " +
                        "(\u001B[38;2;100;255;100m%d HP\u001B[m → \u001B[38;2;100;100;255m%d MN\u001B[m)"
                                .formatted(exchanges*2, exchanges*3));
                break;
            } else {
                System.out.println("\nУ вас недостаточно \u001B[38;2;100;255;100mЗДОРОВЬЯ\u001B[m для проведения такого обмена."); }
        }

//        del(caster, enemy, iCard);
    }

// Функции класса
    private void printUse() {
        if (!used)
            System.out.println("Карта \u001B[38;2;100;255;255mExchange\u001B[m:" + " ".repeat(141) + "( '-1' — смена режима )");
        used = true;
    }

}
