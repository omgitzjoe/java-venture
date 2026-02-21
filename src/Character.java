// Parent class to each character.
public abstract class Character {

    // Identity strings
    private String characterClass = "";
    private String story = "";
    private  String name="";

    // Ability stats
    private int health = 30;
    private int intelligence = 6;
    private int strength = 6;
    private int dexterity = 6;

    // Combat stats
    private int damage=6;
    private int shield = 0;

    // Constructor
    public Character(String name)
    {
        this.name = name;
    }

    // Getters
    public int getShield() { return shield; }
    public String getName() { return name; }
    public int getDexterity() { return dexterity; }
    public int getIntelligence() { return intelligence; }
    public int getHealth() { return health; }
    public int getStrength() { return strength; }
    public String getStory() { return story; }
    public int getDamage()  { return damage; }
    public String getCharacterClass() { return characterClass; }


    // Modifiers for increasing or decreasing stats
    public void changeShield(int change) {
        shield += change;
    }
    protected void changeDamage(int change) {
        damage += change;
    }
    protected void changeIntelligence(int change) {
        intelligence += change;
    }
    public void changeName(String newName)
    {
        name= newName;
    }
    public void changeDexterity(int change) {
        dexterity += change;
    }

    public void changeStrength(int change) {
        strength += change;
    }

    public void changeHealth(int change) {
        health += change;
    }

    protected void changeCharacterClass(String change) {
        characterClass = change;
    }
    protected void changeStory(String change) {
        story = change;
    }

    public void displayStats()
    {
        IO.println(name + " has: " + health + " Health, "
                + strength + " Strength, "
                + intelligence + " Intelligence, and "
                + dexterity + " Dexterity");
    }
}