package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Effects.Ef_Parasitism;
import Main.Entities.Dyad.Player;

public class Cd_Parasite extends Card {
    private Ef_Parasitism efParasitism; // Запоминает ссылку на созданный собой эффект Паразита
    private final int extractionDamage = 6;

    public Cd_Parasite() {
        super("Parasite", 15);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        caster.changeHP(-extractionDamage);
        caster.gotoStatuses().delEffect(efParasitism);

        enemy.gotoHand().addCard(this, enemy, caster);
    }

    @Override
    public void drop(int iCard, Player caster, Player enemy) {
        caster.changeHP(-extractionDamage);
        caster.gotoStatuses().delEffect(efParasitism);
    }

    @Override
    public void opening(Player owner, Player enemy) {
        efParasitism = new Ef_Parasitism(owner, 1);
        owner.gotoStatuses().addEffect(efParasitism);
    }

}
