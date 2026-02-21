import java.util.Random;

public class Checks {

    private static final Random die = new Random();

    // Roll a die with the given number of sides
    private static int rollDie() {
        return die.nextInt(20) + 1; // nextInt(sides) gives 0..sides-1, so add 1
    }

    // Strength check
    public static boolean strengthCheck(Character character) {
        return character.getStrength() + rollDie() > 20;
    }

    // Intelligence check
    public static boolean intelligenceCheck(Character character) {
        return character.getIntelligence() + rollDie() > 20;
    }

    // Dexterity check
    public static boolean dexterityCheck(Character character) {
        return character.getDexterity() + rollDie() > 20;
    }
}