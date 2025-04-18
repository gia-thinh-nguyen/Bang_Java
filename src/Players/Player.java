package Players;

import Cards.Card;
import Cards.Equipments.EquipmentType;
import Game.GameBoard;
import Game.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public abstract class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int gunRange;
    private int attackHorse;
    private int defenseHorse;
    private boolean hasBarrel;
    private boolean isDynamited;
    private boolean isJailed;
    private Role role;
    private Character character;
    private boolean isSheriff;
    private boolean bangUnlimited;
    private boolean alreadyBanged;
    private boolean isAlive;
    private ArrayList<Card> hand;
    private HashMap<EquipmentType,Card> equipmentTypeToCardMap = new HashMap<>();
    private final GameBoard gameBoard;
    private final Game game;
    public Player(String name,Game game, GameBoard gameBoard, Role role, Character character){
        boolean isSheriff = role== Role.SHERIFF;
        this.isSheriff = isSheriff;
        this.name = name;
        this.health = isSheriff ? 5 : 4;
        this.maxHealth = isSheriff ? 5 : 4;
        this.gunRange = 1;
        this.attackHorse = 0;
        this.defenseHorse = 0;
        this.hasBarrel = false;
        this.isDynamited = false;
        this.isJailed = false;
        this.role = role;
        this.character = character;
        this.hand = new ArrayList<>();
        this.game = game;
        this.gameBoard = gameBoard;
        this.isAlive = true;
        this.bangUnlimited = false;

        // Initialize the equipmentTypeToCardMap with all keys from EquipmentType
        for (EquipmentType equipmentType : EquipmentType.values()) {
            equipmentTypeToCardMap.put(equipmentType, null);
        }
    }
    @Override
    public String toString(){
        String handString = "";
        String equipmentString = "";
        for(EquipmentType equipmentType : equipmentTypeToCardMap.keySet()){
            Card card = equipmentTypeToCardMap.get(equipmentType);
            if(card==null){
                continue;
            }
            equipmentString += equipmentType + ": " + equipmentTypeToCardMap.get(equipmentType).toString() + ", ";
        }
        for(Card card : hand){
            handString += card.toString() + ", ";
        }
        String horseString = "";
        if(attackHorse > 0){
            horseString += "Attack Horse: " + attackHorse + ", ";
        }
        if(defenseHorse > 0){
            horseString += "Defense Horse: " + defenseHorse + ", ";
        }
        String sb = "Player:" + getName() + " " +
                "Health:" + health + "/" + maxHealth + " " +
                "Gun Range:" + gunRange + " " +
                horseString+ " " +
                "Barrel:" + hasBarrel + " " +
                "Dynamited:" + isDynamited + " " +
                "Jailed:" + isJailed + " " +
                "Role:" + role.toString() + " " +
                "Character:" + character.toString() + "\n" +
                "     Hand: " + handString+ "\n" +
                "     Equipment: " + equipmentString;


        return sb;
    }
    public void draw(int numCards){
        for(int i=0; i<numCards; i++){
            Card card = gameBoard.DrawFromPile();
            card.setOwner(this);
            addToHand(card);
        }
    }
    public void heal(){
        if(health < maxHealth){
            health++;
        }
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
    public void setGunRange(int gunRange){
        this.gunRange = gunRange;
    }
    public void setAttackHorse(int horse){
        this.attackHorse = horse;
    }
    public void setDefenseHorse(int horse){
        this.defenseHorse = horse;
    }
    public void setBarrel(boolean hasBarrel){
        this.hasBarrel = hasBarrel;
    }
    public void setDynamited(boolean isDynamited){
        this.isDynamited = isDynamited;
    }
    public void setJailed(boolean isJailed){
        this.isJailed = isJailed;
    }
    public void setBangUnlimited(boolean bangUnlimited){
        this.bangUnlimited = bangUnlimited;
    }
    public void setAlreadyBanged(boolean alreadyBanged){
        this.alreadyBanged = alreadyBanged;
    }

    public Queue<Player> getPlayers(){
        return game.getPlayers();
    }
    public String getName(){
        return name;
    }
    public Character getCharacter(){
        return character;
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public int getGunRange(){
        return gunRange;
    }
    public int getAttackHorse(){
        return attackHorse;
    }
    public int getDefenseHorse(){
        return defenseHorse;
    }
    public boolean BangUnlimited(){
        return bangUnlimited;
    }
    public boolean AlreadyBanged(){
        return alreadyBanged;
    }
    public boolean isSheriff(){
        return isSheriff;
    }
    public boolean hasBarrel(){
        return hasBarrel;
    }
    public boolean hasCard(String cardName){
        for(Card card : hand){
            if(card.getName().equals(cardName)){
                return true;
            }
        }
        return false;
    }
    public void addToHand(Card card){
        hand.add(card);
    }
    public void removeFromHand(Card card){
        for (Card c : hand){
            if(c.equals(card)){
                hand.remove(c);
                break;
            }
        }
    }
    public void addToEquipmentMap(EquipmentType equipmentType, Card card){
        equipmentTypeToCardMap.put(equipmentType,card);
    }
    public void removeEquipment(EquipmentType equipmentType){
        Card card = equipmentTypeToCardMap.get(equipmentType);
        equipmentTypeToCardMap.put(equipmentType,null);
        //set to default values
        switch (equipmentType){
            case GUN -> {
                gunRange = 1;
                setBangUnlimited(false);
            }
            case HORSE -> {
                attackHorse = 0;
                defenseHorse = 0;
            }
            case BARREL -> hasBarrel = false;
            case DYNAMITE -> isDynamited = false;
            case JAIL -> isJailed = false;
        }
        gameBoard.Discard(card);
    }
    public HashMap<EquipmentType,Card> getEquipmentMap(){
        return equipmentTypeToCardMap;
    }
    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public void takeDamage(Player damageSource,int damage){
        //damageSource is null for dynamite
        health -= damage;
        if(health <= 0){
            isAlive = false;
        }
    }
    public void playFistCardFromHand(String cardName){
        for(Card card : hand){
            if(card.getName().equals(cardName)){
                card.played();
                break;
            }
        }
    }
    public void discardFirstCardFromHand(String cardName){
        for(Card card : hand){
            if(card.getName().equals(cardName)){
                gameBoard.Discard(card);
                hand.remove(card);
                break;
            }
        }
    }
    public Card removeRandomCardFromHand(){
        if(hand.isEmpty()){
            return null;
        }
        int randomIndex = (int) (Math.random() * hand.size());
        Card card = hand.get(randomIndex);
        hand.remove(randomIndex);
        return card;
    }

    public void firstPhase(){
        alreadyBanged = false;
        //draw 2 cards
        draw(2);
    }
}
