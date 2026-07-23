package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Conduction extends Card {
    private final double buffCon = 0.5;

    public Cd_Conduction() {
        super("Conduction", 0);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {
        caster.gotoStatuses().setMaxCon(true);
    }

    @Override
    public void opening(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleConduction(owner.gotoStatuses().getScaleConduction() + buffCon); }

    @Override
    public void ending(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleConduction(owner.gotoStatuses().getScaleConduction() - buffCon); }

}
