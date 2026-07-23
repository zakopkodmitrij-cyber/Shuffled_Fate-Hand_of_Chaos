package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;


public class Cd_Attack extends Card {
    private final int damage = 10;

    public Cd_Attack() { super("Attack", 6); } // Конструктор

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        int changeHP = -(int)Math.floor(this.damage * caster.gotoStatuses().getScaleDamage());
        changeHP = (int)Math.round(changeHP / enemy.gotoStatuses().getScaleProtection());
        enemy.changeHP( changeHP );
    } // Карта играется как нанесение урона цели

}
