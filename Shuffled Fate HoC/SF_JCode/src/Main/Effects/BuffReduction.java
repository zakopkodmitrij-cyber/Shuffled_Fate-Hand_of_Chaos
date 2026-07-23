package Main.Effects;

import Main.Entities.Dyad.Player;

public class BuffReduction extends Effect {
    private final double buffValue;

    public BuffReduction(Player player, int duration, double buffHeal) {
        super(player, duration);
        this.buffValue = buffHeal;
    }

    @Override
    public String presentation() {
        return ""; }

    @Override
    public void start(double value) {
        player.gotoStatuses().multScaleProtection(buffValue); }

    @Override
    public void tick() { duration--; }


    @Override
    public void end() {
        player.gotoStatuses().multScaleProtection(1/buffValue);
    }
}
