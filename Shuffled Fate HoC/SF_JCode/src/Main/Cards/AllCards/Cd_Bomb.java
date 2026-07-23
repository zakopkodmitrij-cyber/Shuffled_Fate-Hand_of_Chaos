package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;
import Main.Effects.Ef_Mined;

public class Cd_Bomb extends Card {
    private final int power = 15;

    public Cd_Bomb() {
        super("Bomb", 6);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        int effectPower = (int)Math.floor(this.power * caster.gotoStatuses().getScaleDamage());
        enemy.gotoStatuses().addEffect(new Ef_Mined(enemy, 3, effectPower));
    }

}
