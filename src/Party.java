import java.util.ArrayList;
import java.util.List;
// Class to handle a list of characters arranged as a party.
public class Party {

    private final List<Character> characters = new ArrayList<>();

    public List<Character> getCharacters()
    {
        return characters;
    }

    // Add member
    public void addMember(Character character)
    {
        characters.add(character);
    }

    // Remove member
    public void removeMember(Character character)
    {
        characters.remove(character);
    }
    public void displayMembers()
    {
        int index = 0;
        for (Character character : characters)
        {
            IO.println(index + ": "
                    + character.getName()
                    + " the "
                    + character.getCharacterClass());
            index++;
        }
    }
}