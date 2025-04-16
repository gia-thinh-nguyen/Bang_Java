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
    private ArrayList<Card> hand;
    private final GameBoard gameBoard;
    public Player(boolean isSheriff, GameBoard gameBoard, Role role, Character character){
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
        this.gameBoard = gameBoard;
    }
    public void draw(int numCards){
        for(int i=0; i<numCards; i++){
            Card card = gameBoard.DrawFromPile();
            hand.add(card);
        }
    }
}
