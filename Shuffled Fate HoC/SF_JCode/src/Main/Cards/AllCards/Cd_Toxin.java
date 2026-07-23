package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;
import Main.Effects.Ef_Intoxication;

public class Cd_Toxin extends Card {
    private final int toxicity = 2;

    public Cd_Toxin() { super("Toxin", 4); } // Конструктор

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        enemy.gotoStatuses().addEffect(new Ef_Intoxication(enemy, 5, toxicity));
    } // Карта играется как наложение эффекта Интоксикации на врага

}
