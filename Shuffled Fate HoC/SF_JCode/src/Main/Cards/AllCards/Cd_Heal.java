package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Heal extends Card {
    private final int heal = 10;

    public Cd_Heal() { super("Heal", 6); } // Конструктор

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        caster.changeHP(+this.heal);
    } // Карта играется как исцеление

}
