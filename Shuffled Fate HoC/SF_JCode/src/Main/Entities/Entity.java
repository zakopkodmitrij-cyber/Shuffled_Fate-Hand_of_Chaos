package Main.Entities;

public abstract class Entity {
    protected String name;
    protected String icoKey;
    protected int maxHP, maxMN;
    protected int HP, MN;

    protected Entity(String name, String icoKey, int maxHP, int maxMN) {
        this.name = name;
        this.icoKey = icoKey;

        this.maxHP = maxHP; this.maxMN = maxMN;
        this.HP    = maxHP; this.MN    = maxMN;
    }

    protected Entity(Entity sample) {
        this.name   = sample.name;
        this.icoKey = sample.icoKey;

        this.maxHP  = sample.maxHP; this.maxMN  = sample.maxMN;
        this.HP     = sample.HP;    this.MN     = sample.MN;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcoKey() { return icoKey; }
    public void setIcoKey(String icoKey) { this.icoKey = icoKey; }

    public int getMaxHP() { return maxHP; }
    public void setMaxHP(int maxHP) { this.maxHP = Math.max(1, maxHP); }

    public int getMaxMN() { return maxMN; }
    public void setMaxMN(int maxMN) { this.maxMN = Math.max(0, maxMN); }

    public int getHP() { return HP; }                                             // Получение HP
    public void setHP(int HP) { this.HP = Math.max(0, Math.min(HP, maxHP)); }    // Установление HP (ограничение по maxHP)
    public void changeHP(int change) { setHP(getHP() + change); }               // Изменение HP (учитывает ограничение maxHP)

    public int getMN() { return MN; }                                             // Получение MN
    public void setMN(int MN) { this.MN = Math.max(0, Math.min(MN, maxMN)); }    // Установление MN (ограничение по maxMN)
    public void changeMN(int change) { setMN(getMN() + change); }               // Изменение MN (учитывает ограничение maxMN)

    public void rebuild(Entity sample) {
        this.name   = sample.name;
        this.icoKey = sample.icoKey;

        this.maxHP  = sample.maxHP; this.maxMN  = sample.maxMN;
        this.HP     = sample.HP;    this.MN     = sample.MN;
    }
}
