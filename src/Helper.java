import java.util.Scanner;
// Helper functions to clean up main a bit, will better organize
// when more functions are added;
public class Helper
{
    private static final Scanner scanner = new Scanner(System.in);
    //Function to prevent accidentally skipping naming
    public static String getName(String name)
    {
        while (name == null || name.isBlank())
        {
            System.out.println("Please re-enter a name");
            name = scanner.nextLine();
        }
        return name;
    }
    // Adds a pause to the game flow
    public static void pause()
    {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    // Test for integer input
    // Loops until a valid integer is entered
    public static int intTest(String input)
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input, please choose an integer.");
                input = scanner.nextLine();
            }
        }
    }
    // Create a hero, can easily handle additional classes if desired
    // Validates choice and creates the chosen hero
    public static Character createCharacter(String name)
    {
        System.out.println("Nephite (1), Lamanite (2), Robber (3)");

        while (true)
        {
            System.out.println("Please choose (1-3)");
            String input = scanner.nextLine();
            int type = intTest(input);
            // Create character based on chosen class
            switch (type)
            {

                case 1:
                    System.out.println("\nYou have chosen Nephite; well rounded and full of faith!");
                    Character heroN= new Nephite(name);
                    // Nephi gets better stats
                    if (heroN.getName().equals("Nephi"))
                    {
                        IO.println("""
                                    Legendary hero Nephi has entered the party,
                                    he brings increased attack damage and intelligence""");
                        heroN.changeDamage(4);
                        heroN.changeIntelligence(2);
                    }
                    return heroN;

                case 2:
                    System.out.println("\nYou have chosen Lamanite; a natural warrior!");
                    Character heroL= new Lamanite(name);
                    // Samuel gets better stats
                    if (heroL.getName().equals("Samuel"))
                    {
                        IO.println("""
                        Legendary hero Samuel the Lamanite enters the party,
                        His dodging ability makes him incredibly hard to hit""");
                        heroL.changeDexterity(8);
                    }
                    return heroL;

                case 3:
                    System.out.println("\nYou have chosen Robber; sneaky, crafty, but with a heart of gold!");
                    Character heroR=new Robber(name);
                    // Fatal mistake to pick Gadianton as a robber
                    if (heroR.getName().equals("Gadianton"))
                    {
                        IO.println("""
                                  Gadianton immediately turns on the party!
                                  Hidden figures emerge from the surrounding area,
                                  we never stood a chance. Assassinated by Gadianton.
                                  Careful the company you keep!""");
                        System.exit(0);
                    }
                    return heroR;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}