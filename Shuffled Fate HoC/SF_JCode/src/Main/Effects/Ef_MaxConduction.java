package Main.Effects;

import Main.Entities.Dyad.Player;

// [ПОД СНОС]
class Ef_MaxConduction extends Effect {
    private double oldConduction;

    public Ef_MaxConduction(Player player) {
        super(player, 1);
        oldConduction = player.gotoStatuses().getScaleConduction();
        start(Double.MAX_VALUE);
    }

    @Override
    public void start(double value) {
        player.gotoStatuses().setScaleConduction(value);
    }

    @Override
    public void tick() { duration--; }

    @Override
    public void end() {
        player.gotoStatuses().setScaleProtection(oldConduction);
    }

    @Override
    public String presentation() { return ""; }

}
