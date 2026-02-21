private static final Scanner scanner = new Scanner(System.in);

void main() {

    // Introduction
    IO.println("""
            \nYou struggle to gain consciousness, like waking from a dream.
            Surrounded by darkness, memories from another life begin to settle in...)
            You notice you are inside a large cave""");
    Helper.pause();

    IO.println("You ask yourself, what is my name again?\n(Enter name)");
    String name = Helper.getName(scanner.nextLine());

    IO.println("\nThat's right, it's " + name);

    // Create main character
    IO.println("A presence asks you to choose a tribe that speaks to you");
    Character hero = Helper.createCharacter(name);

    // Initialize a party
    Party party = new Party();
    party.addMember(hero);
    Helper.pause();

    IO.println(hero.getStory());

    IO.println("\nInitial stats are as follows:");
    hero.displayStats();
    Helper.pause();

    IO.println("You soon notice you are not alone");

    // Second member
    IO.println("Your best friend, their name was?:");
    String friend = Helper.getName(scanner.nextLine());
    Character hero2 = Helper.createCharacter(friend);
    party.addMember(hero2);
    hero2.displayStats();
    Helper.pause();

    // Third member
    IO.println("Another friend emerges, name being...");
    String friend2 = Helper.getName(scanner.nextLine());
    Character hero3 = Helper.createCharacter(friend2);
    party.addMember(hero3);
    hero3.displayStats();
    Helper.pause();

    // Display party
    IO.println("Your party consists of:");
    party.displayMembers();
    Helper.pause();

    List<Character> members = party.getCharacters();

    // Treasure chest event
    IO.println("""
            You search the cave and notice a treasure chest!
            However it is locked.
            There is an inscription, a riddle by the looks of it");
            You can smash the lock (1), pick the lock (2), pray (3), or read the riddle (4)""");

    boolean valid = false;

    while (!valid) {
        // Validate choice and member values
        IO.println("Please choose (1-4)");
        int chest = Helper.intTest(scanner.nextLine());
        if (chest > 5 || chest < 1)
        {
            IO.println("Please enter a valid choice!");
            continue;
        }
        int choice;
        IO.println("Which member of the party should try this? (0,1,2)");
        while (true)
        {
            choice = Helper.intTest(scanner.nextLine());
            if (choice < 0 || choice > 2)
            {
                IO.println("Please enter a valid choice!");
            }
            else break;
        }
        hero = members.get(choice);

        // Situational choice
        switch (chest)
        {
            case 1:
                IO.println(hero.getName() + " attempts to break the chest.");
                Helper.pause();
                if (Checks.strengthCheck(hero))
                {
                    IO.println(hero.getName() +
                            " has found a crossbow!\nDamage increased by 5");
                    hero.changeDamage(5);
                }
                else
                {
                    IO.println(hero.getName() +
                            " acquires a bruised hand.Strength reduced by 1");
                    hero.changeStrength(-1);
                }
                valid = true;
                break;

            case 2:
                if (hero.getCharacterClass().equals("Robber"))
                {
                    IO.println(hero.getName() + " boldly declares, 'looks like a job for a robber', and picks the lock!");
                    IO.println("Damage increased by 5");
                    hero.changeDamage(5);
                }
                else
                {
                    IO.println(hero.getName() + " attempts to pick the lock but fails.");
                }
                valid = true;
                break;

            case 3:
                IO.println(hero.getName() + " prays. The party feels less vulnerable");
                for (Character member : members)
                {
                    member.changeShield(1);
                }
                valid = true;
                break;

            case 4:
                IO.println("Which letter of the alphabet has the most water?");
                // Skip riddle if character has good intelligence
                if (Checks.intelligenceCheck(hero))
                {
                    IO.println(hero.getName() +
                            " answers 'c', the chest opens!");
                    hero.changeDamage(5);
                }
                else
                {
                    String answer = scanner.nextLine();
                    if (answer != null && answer.equalsIgnoreCase("c"))
                    {
                        IO.println(hero.getName() + " answers 'c', the chest opens!");
                        hero.changeDamage(5);
                    }
                    else
                    {
                        IO.println("The chest remains locked.");
                    }
                }
                valid = true;
                break;
            default:
                IO.println("Invalid choice");
        }
    }

    // Transition to combat
    IO.println("The party exits the cave...");
    Helper.pause();
    IO.println("Just where do you think you're going!");
    Helper.pause();

    IO.println("""
            Approaching the party are 4 Gadianton Robbers
            and they are not friendly,
            """);

    // Create a party of villains
    Party robbers = new Party();

    robbers.addMember(new Robber("Scary Man"));
    robbers.addMember(new Robber("Sketchy Stranger"));
    robbers.addMember(new Robber("Deceitful Sister"));
    robbers.addMember(new Robber("Robert the Robber"));
    robbers.displayMembers();

    Combat combat = new Combat();
    combat.combatSequence(party, robbers);

    IO.println("\nEnd of Program");
}