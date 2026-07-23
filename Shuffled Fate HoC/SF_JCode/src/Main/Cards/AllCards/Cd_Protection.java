package Main.Cards.AllCards;

import Main.Cards.Card;
import Main.Entities.Dyad.Player;

public class Cd_Protection extends Card {
    private final double buffDef = 0.3;

    public Cd_Protection() {
        super("Protection", 0);
    }

    @Override
    public void play(Player caster, Player enemy, int iCard) {}

    @Override
    public void opening(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleProtection(owner.gotoStatuses().getScaleProtection() + buffDef); }

    @Override
    public void ending(Player owner, Player enemy) {
        owner.gotoStatuses().setScaleProtection(owner.gotoStatuses().getScaleProtection() - buffDef); }
}
