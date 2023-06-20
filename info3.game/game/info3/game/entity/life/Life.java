package info3.game.entity.life;

public class Life {

    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_MAX_HEALTH = 100;

    public int health;
    public int maxHealth;

    public Life(int health, int maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public Life() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
        ;
    }

    public int ratio()
    {
        return (int)((float)health*100/(float)maxHealth);
    }

    public boolean alive() {
        return health > 0;
    }

    public void removeHealth(int ammount) {
        if (health > 0)
            health -= ammount;
    }

    public void addHealth(int ammount) {
        if (health + ammount <= maxHealth)
            health += ammount;
        else
            health = maxHealth;
    }
}
