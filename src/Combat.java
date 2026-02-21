import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Combat
{
    private static final Random die = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    // Will use a die simulator for random values
    private static int rollDie(int sides)
    {
        return die.nextInt(sides) + 1;
    }

    // Check if target dodges
    // Return false if not
    private static boolean dodge(Character target)
    {
        if (rollDie(12) + target.getDexterity() >= 20)
        {
            IO.println(target.getName() + " dodged the attack");
            return true;
        }
        return false;
    }

    // Get the value for the attack, checks for negative damage
    private static int attack(Character character, Character target)
    {
        if (!dodge(target))
        {
            int damage = character.getDamage() + rollDie(6) - (target.getShield()*2);
            // Don't accept negative
            return Math.max(damage, 0);
        }
        else return 0;
    }

    // Simulates a single attack, uses the attack function on a target
    private static void attackResult(Character hero, Character target)
    {
        int damage = attack(hero, target);
        if (damage == 0)
        {
            IO.println(target.getName() + " takes no damage\n");
        }
        else
        {
            IO.println(hero.getName() + " deals " + damage + " damage\n");
            target.changeHealth(-damage);
        }
    }

    // Simulates entire battle, uses the attackResult function to deal damage
    // Loops after each member of each party has attacked
    // Ends when all members are killed of the opposing party or the main character dies.
    public void combatSequence(Party heroes, Party villains) {

        while (!heroes.getCharacters().isEmpty() &&
                !villains.getCharacters().isEmpty())
        {
            List<Character> heroList = heroes.getCharacters();
            List<Character> villainList = villains.getCharacters();

            // Heroes' turn
            for (Character hero : heroList)
            {
                Character target;

                IO.println("Which enemy will " + hero.getName() + " attack? (0-" + (villainList.size() - 1) + ")");

                int choice = Helper.intTest(scanner.nextLine());
                while (true)
                {
                    if (choice < 0||choice > villainList.size()-1)
                    {
                        IO.println("Invalid choice\nPlease choose 0 - " + (villainList.size() - 1));
                        choice = Helper.intTest(scanner.nextLine());
                    }
                    else break;
                }
                target = villainList.get(choice);

                attackResult(hero, target);

                // Check for enemy casualties
                if (target != null && target.getHealth() <= 0) {
                    IO.println(target.getName() + " has been defeated");
                    villains.removeMember(target);
                }

                if (villains.getCharacters().isEmpty()) {
                    IO.println("All villains are defeated. You win!");
                    return;
                }
            }

            // Villains' turn
            for (Character villain : villainList)
            {
                // Select a random hero and attack
                int targetIndex = rollDie(heroList.size()) - 1;
                Character target = heroList.get(targetIndex);
                IO.println(villain.getName() + " attacks " + target.getName());
                attackResult(villain, target);
                Helper.pause();

                //Check for Hero casualties
                if (target.getHealth() <= 0)
                {
                    IO.println(target.getName() + " has been defeated");
                    // Bring Moroni back as Angel Moroni
                    if (target.getName().equals("Moroni") && target.getCharacterClass().equals("Nephite"))
                    {
                        IO.println("Refusing to abandon the mission, he returns as... Angel Moroni!");
                        target.changeHealth(30);
                        target.changeName("Angel Moroni");
                        target.changeIntelligence(10);
                        target.changeDamage(-4);
                        break;
                    }
                    // Lose if player 1 is defeated.
                    else if (target == heroList.getFirst())
                    {
                        IO.println("Nooooo, I gave it my all!\nGame Over :(");
                        System.exit(0);
                    }
                    heroes.removeMember(target);
                }
            }
            //Display health status
            for (Character hero : heroList)
            {
                IO.println(hero.getName() + " has " + hero.getHealth() + " health");
            }
            IO.println();
            for (Character v : villainList)
            {
                IO.println(v.getName() + " has " + v.getHealth() + " health");
            }
            Helper.pause();
        }
    }
}