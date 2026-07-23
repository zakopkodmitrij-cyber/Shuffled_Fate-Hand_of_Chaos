package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Rift extends Card {
    public Cd_Rift() {
        super("Rift", 7);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        for (int i = 0; i < caster.gotoHand().toList().size(); i++) {
            if (caster.gotoHand().getCard(i) != null) {
                Card newCard = caster.getDeck().draw();
                caster.gotoHand().toList().set(i, newCard);
            }

        }

    }

// Функции класса

}
