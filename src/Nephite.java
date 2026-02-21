// Nephite character inheriting from Character class
public class Nephite extends Character {

    public Nephite(String name) {
        super(name);

        changeStory("""
                Born of goodly parents, adventure has called to you from a young age.
                You believed you were at an age where the pursuit of adventure was most appropriate.
                You wished your family well and departed.""");

        changeIntelligence(2);
        changeDexterity(2);
        changeStrength(1);

        changeCharacterClass("Nephite");
    }
}