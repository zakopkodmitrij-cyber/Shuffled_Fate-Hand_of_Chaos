package Main.Effects;

import Main.Entities.Dyad.Player;

// Абстрактный клаас Effect для создания Эффектов
public abstract class Effect {
    protected final Player player; // Каждый эффект должен иметь доступ к игроку, на которого он действует
    protected int duration;

    public Effect(Player player, int duration) {
        this.player = player;
        this.duration = duration;
    } // Конструктор - установление ссылки на Статы Игрока и продолжительности Эффекта при обьявлении


    public int  getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    // Действия при:
    public abstract void start(double value);  // наложении Эффекта
    public abstract void tick();              // действие Эффекта за ход
    public abstract void end();              // окончании Эффекта

    public abstract String presentation();

}
