package Players;

import Cards.Card;
import Cards.Equipments.EquipmentType;
import Cards.SUIT;
import Game.GameBoard;
import Game.Game;
import Game.ActionMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public abstract class Player {
    private final String name;
    private int health;
    private int maxHealth;
    private int gunRange;
    private int attackHorse;
    private int defenseHorse;
    private final Role role;
    private final Character character;
    private boolean bangUnlimited;
    private boolean alreadyBanged;
    private boolean isAlive;
    private final ArrayList<Card> hand;
    private final HashMap<EquipmentType,Card> equipmentTypeToCardMap = new HashMap<>();
    private final GameBoard gameBoard;
    private final Game game;
    public Player(String name,Game game, GameBoard gameBoard, Role role, Character character){
        this.name = name;
        this.health = role== Role.SHERIFF ? 5 : 4;
        this.maxHealth = role== Role.SHERIFF ? 5 : 4;
        this.gunRange = 1;
        this.attackHorse = 0;
        this.defenseHorse = 0;
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
        String sb = "Player:" + getName() + " " +
                "Health:" + health + "/" + maxHealth + " " +
                "Gun Range:" + gunRange + " " +
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
    public boolean getIsAlive(){
        return isAlive;
    }
    public boolean BangUnlimited(){
        return bangUnlimited;
    }
    public boolean AlreadyBanged(){
        return alreadyBanged;
    }
    public boolean isSheriff(){
        return role== Role.SHERIFF;
    }
    public boolean hasBarrel(){
        return equipmentTypeToCardMap.get(EquipmentType.BARREL) != null;
    }
    public boolean isDynamited(){
        return equipmentTypeToCardMap.get(EquipmentType.DYNAMITE) != null;
    }
    public boolean isJailed(){
        return equipmentTypeToCardMap.get(EquipmentType.JAIL) != null;
    }
    public boolean hasCard(String cardName){
        for(Card card : hand){
            if(card.getName().equals(cardName)){
                return true;
            }
        }
        return false;
    }
    public boolean almostDead(){
        return health <= 1;
    }
    public boolean hasHealCards(){
        for(Card card : hand){
            if(card.getName().equals("Beer") || card.getName().equals("Saloon")){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Card> getHealCards(){
        ArrayList<Card> healCards = new ArrayList<>();
        for(Card card : hand){
            if(card.getName().equals("Beer") || card.getName().equals("Saloon")){
                healCards.add(card);
            }
        }
        return healCards;
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
        //remove from equipment map and discard
        Card card = equipmentTypeToCardMap.get(equipmentType);
        if(card==null){
            return;
        }
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
        }
        gameBoard.Discard(card);
    }
    public HashMap<EquipmentType,Card> getEquipmentMap(){
        return equipmentTypeToCardMap;
    }
    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public void takeDamage(Player damageSource){
        String damageSourceName = damageSource == null ? "dynamite" : damageSource.getName();
        System.out.println("Player "+name +" lose 1 health from " + damageSourceName + ".");
        if(almostDead()){
            if(hasHealCards()){
                System.out.println("Almost dead but you have heals.");
                Card card = ActionMenu.showMenu(getHealCards(),false);
                card.played();
            }
            else{
                System.out.println("You are dead.");
                reward(damageSource,role);
                onElimination();
            }
        }
        health -= 1;

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

    public void reward(Player killer,Role role){
        //reward based on role
        //if Outlaw, killer draw 3 cards
        //if Vice and killer is Sheriff, lose all cards and equipment
        if(killer == null) return;
        if(role == Role.OUTLAW){
            killer.draw(3);
        }
        else if(role == Role.VICE && killer.isSheriff()){
            //lose all cards and equipment
            for(Card card : hand){
                gameBoard.Discard(card);
            }
            hand.clear();
            for(EquipmentType equipmentType : equipmentTypeToCardMap.keySet()){
                removeEquipment(equipmentType);
            }
        }
    }
    public void onElimination(){
        isAlive = false;
        if(game.hasVultureSam()){
            Player vultureSam = game.getVultureSam();
            for(Card card: hand){
                vultureSam.addToHand(card);
            }
            hand.clear();
            for(Card card: equipmentTypeToCardMap.values()){
                if(card != null){
                    vultureSam.addToHand(card);
                }
            }
        }
        else{
            //discard all cards
            for(Card card : hand){
                gameBoard.Discard(card);
            }
            hand.clear();
            for(EquipmentType equipmentType : equipmentTypeToCardMap.keySet()){
                removeEquipment(equipmentType);
            }
        }

    }

    //return false if player is failed to escape from jail
    public boolean firstPhase(){
        //reset variables
        alreadyBanged = false;
        //check for dynamite and jail
        if(isDynamited()){
            //check for dynamite
            //if top card is 2 to 9 Spades, lose 3 health. Else pass the dynamite to the next player
            boolean dynamiteExploded = gameBoard.checkTopCard(null,true);
            if(dynamiteExploded && character==Character.LUCKY_DUKE){
                //Lucky Duke gets a second chance
                System.out.println("Lucky Duke gets a second chance.");
                dynamiteExploded = gameBoard.checkTopCard(null,true);
            }
            if(dynamiteExploded){
                for(int i=0; i<3; i++){
                    takeDamage(null);
                }
                removeEquipment(EquipmentType.DYNAMITE);
                System.out.println("Dynamite exploded.You lose 3 health.");
            }
            else{
                System.out.println("Pass the dynamite to the next player.");
                //pass the dynamite to the next player
                Card dynamite = equipmentTypeToCardMap.get(EquipmentType.DYNAMITE);
                equipmentTypeToCardMap.put(EquipmentType.DYNAMITE,null);
                game.getNextPlayer().addToEquipmentMap(EquipmentType.DYNAMITE,dynamite);
            }
        }
        if(isJailed()){
            //if jailed, check top card for a Heart. If failed, lose a turn and discard the jail
            removeEquipment(EquipmentType.JAIL);
            boolean success = gameBoard.checkTopCard(SUIT.HEART,false);
            if (!success && character == Character.LUCKY_DUKE) {
                // Lucky Duke gets a second chance
                System.out.println("Lucky Duke gets a second chance!");
                success = gameBoard.checkTopCard(SUIT.HEART, false);
            }

            if(!success){
                System.out.println("You are jailed. You lose a turn.");
                return false;
            }
            else{
                System.out.println("You are free from jail.");
                removeEquipment(EquipmentType.JAIL);
                return true;
            }
        }

        //draw in the first phase
        firstPhaseDraw();
        return true;
    }
    protected void firstPhaseDraw(){
        //normal characters draw 2 cards
        draw(2);
    }
}
