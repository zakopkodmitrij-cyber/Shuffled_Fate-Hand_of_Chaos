package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

// Карта Жестокость - при получении увеличивает scaleDamage обладателя на 30% и уменьшает на столькоже при удалении из руки
public class Cd_Cruelty extends Card {
    private final double buffAtk = 0.2;

    public Cd_Cruelty() {
        super("Cruelty", 0);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {}

    @Override
    public void opening(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleDamage(owner.gotoStatuses().getScaleDamage() + buffAtk); }

    @Override
    public void ending(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleDamage(owner.gotoStatuses().getScaleDamage() - buffAtk); }

}
