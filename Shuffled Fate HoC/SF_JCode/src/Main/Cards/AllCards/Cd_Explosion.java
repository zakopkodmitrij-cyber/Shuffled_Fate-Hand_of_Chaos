package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Explosion extends Card {
    private final int damage = 15;

    public Cd_Explosion() {
        super("Explosion", 8);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        int changeHP;

        changeHP = (int) Math.floor(this.damage * caster.gotoStatuses().getScaleDamage());
        changeHP = (int) Math.round(changeHP / caster.gotoStatuses().getScaleProtection());
        caster.changeHP(-changeHP);

        changeHP = (int) Math.floor(this.damage * caster.gotoStatuses().getScaleDamage());
        changeHP = (int) Math.round(changeHP / enemy.gotoStatuses().getScaleProtection());
        enemy.changeHP(-changeHP);
    }

}