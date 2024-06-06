package model;
import java.util.ArrayList;
/*
 * Represents a Pokemon Card.
 */
public class Card {
    private int id;
    private String name;
    private String type;
    private String rarity;
    private int pack;
    private int hp;
    private double value;
    private int evoStage;
    
    private ArrayList<Integer> family;
    private ArrayList<String> attacks;
/*
 * Default constructor for the card class.
 */
    public Card() { 
        id = 0;
        name = "wow";
        type = "dang";
        rarity = "super";
        pack = 3;
        hp = 100;
        value = 10.4;
        evoStage = 1;
        family = new ArrayList<Integer>();
        attacks = new ArrayList<String>();
    }
    /**
     * Constructs a Card with specified attributes.
     * @param id The ID of the card pokeDex number.
     * @param name The name of the pokemon card.
     * @param type The pokemon type.
     * @param rarity The rarity of the card.
     * @param pack The pack number associated with the card.
     * @param hp The health points of the card.
     * @param value The value of the card.
     * @param evoStage The evo stage of the card.
     * @param family The family of the card.
     * @param attacks The attacks of the card.
     * 
     */
    public Card(int id, String name, String type, String rarity, int pack, int hp, double value, int evoStage, ArrayList<Integer> family, ArrayList<String> attacks) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.pack = pack;
        this.hp = hp;
        this.value = value;
        this.evoStage = evoStage;
        this.family = family;
        this.attacks = attacks;
    }
    /**
     * Gets the ID of the card.
     * 
     * @return The ID of the card.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the card.
     * 
     * @return The name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the card.
     * 
     * @return The type of the card.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the rarity of the card.
     * 
     * @return The rarity of the card.
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Gets the pack number of the card.
     * 
     * @return The pack number of the card.
     */
    public int getPack() {
        return pack;
    }


    /**
     * Gets the health points of the card.
     * 
     * @return The health points of the card.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the value of the card.
     * 
     * @return The value of the card.
     */
    public double getValue() {
        return value;
    }


    /**
     * Gets the evolution stage of the card.
     * 
     * @return The evolution stage of the card.
     */
    public int getEvoStage() {
        return evoStage;
    }

    /**
     * Gets the family of the card.
     * 
     * @return The family of the card.
     */
    public ArrayList<Integer> getFamily() {
        return family;
    }

    /**
     * Gets the attacks of the card.
     * 
     * @return The attacks of the card.
     */
    public ArrayList<String> getAttacks() {
        return attacks;
    }
}
