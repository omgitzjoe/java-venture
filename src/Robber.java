// Robber character inheriting from Character class
public class Robber extends Character {

    public Robber(String name) {
        super(name);

        changeStory("A former Gadianton Robber, you have renounced those old ways "
                + "in favor of a simple pleasant life.\nFinding a lack "
                + "of clarity, you set out to find purpose and adventure.");

        changeDexterity(2);
        changeDamage(2);
        changeIntelligence(1);
        changeStrength(-1);

        changeCharacterClass("Robber");
    }
}