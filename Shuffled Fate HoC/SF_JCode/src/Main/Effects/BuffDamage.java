package Main.Effects;

import Main.Entities.Dyad.Player;

public class BuffDamage extends Effect {
    private final double buffValue;

//                  Целевогой игрок, длительность, множитель баффа урона
    public BuffDamage(Player player, int duration, double buffDamage) {
        super(player, duration);
        this.buffValue = buffDamage;
    }

    @Override
    public String presentation() { return ""; }

    @Override
    public void start(double value) {
        player.gotoStatuses().multScaleDamage(buffValue);
    }

    @Override
    public void tick() { duration--; }

    @Override
    public void end() {
        player.gotoStatuses().multScaleDamage(1/buffValue);
    }

}

