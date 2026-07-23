package Main.Cards.AllCards;

import java.util.InputMismatchException;

import Main.Cards.Card;
import Main.Cards.CardBuilder;
import Main.Entities.Dyad.Player;
import Main.System.Input;

public class Cd_Mimic extends Card {
    private Card guise = null; // Выбранный Мимиком облик

    public Cd_Mimic() {
        super("Mimic", 9);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        System.out.println("Карта \u001B[38;2;100;100;100mMimic\u001B[m:");

        while (guise == null) {
            System.out.print("Укажите карту для копирования поведения: ");

            // TODO: Проверить после того как разберусь со всеми del()
            try {
                int iSelectedCard = Input.nextInt() - 1;
                Card cardForCopyed = caster.gotoHand().getCard(iSelectedCard);
                guise = CardBuilder.buildCard(cardForCopyed.getName());
            }
            catch (InputMismatchException e) {
                System.out.println("\n ~Я тебя не понимаю~");
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("\n ~Я не могу это скопировать~");
            }
            catch (NullPointerException e) {
                System.out.println("\n ~Здесь пусто~");
            }

        }

        guise.opening(caster, enemy);
        caster.gotoHand().toList().set(iCard, guise);

        setMassage("\u001B[38;2;100;100;100mMimic\u001B[m из руки %s трансформировался в карту %s"
                .formatted(caster.getName(), guise.getName()));
    }

// Функции Класса

}
