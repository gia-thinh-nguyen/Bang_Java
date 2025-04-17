package Players;

import Cards.Card;
import Game.GameBoard;
import Game.Game;

import java.util.ArrayList;

public class Player {
    private int health;
    private int maxHealth;
    private int gunRange;
    private int horse;
    private boolean hasBarrel;
    private boolean isDynamited;
    private boolean isJailed;
    private Role role;
    private Character character;
    private boolean isSheriff;
    private boolean bangUnlimited;
    private boolean isAlive;
    private ArrayList<Card> hand;
    private final GameBoard gameBoard;
    private final Game game;
    public Player(boolean isSheriff,Game game, GameBoard gameBoard, Role role, Character character){
        this.isSheriff = isSheriff;
        this.health = isSheriff ? 5 : 4;
        this.maxHealth = isSheriff ? 5 : 4;
        this.gunRange = 1;
        this.horse = 0;
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
    }
    public void draw(int numCards){
        for(int i=0; i<numCards; i++){
            Card card = gameBoard.DrawFromPile();
            hand.add(card);
        }
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void heal(){
        if(health < maxHealth){
            health++;
        }
    }
    public void setGunRange(int gunRange){
        this.gunRange = gunRange;
    }
    public void setHorse(int horse){
        this.horse = horse;
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

    public ArrayList<Player> getPlayers(){
        return game.getPlayers();
    }
}
