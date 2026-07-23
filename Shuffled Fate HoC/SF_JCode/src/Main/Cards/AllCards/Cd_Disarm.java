package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Disarm extends Card {

    public Cd_Disarm() {
        super("Disarm", 15);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        int lenHand = enemy.gotoHand().cntAllCards();

        for (int i = 0; i < lenHand; i++) {
            enemy.gotoHand().delCardAsUsed(i, enemy, caster);
        }

    }
}
