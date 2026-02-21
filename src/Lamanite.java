// Lamanite character inheriting from Character class
public class Lamanite extends Character {

    public Lamanite(String name) {
        super(name);

        changeStory("""
                A strong member of your tribe, you sought to do the right thing,\s
                though it wasn't always easy given your family's traditions.
                One day you set out and never looked back""");

        changeStrength(6);
        changeDamage(4);
        changeIntelligence(-1);

        changeCharacterClass("Lamanite");
    }
}