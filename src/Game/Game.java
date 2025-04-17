package Game;

import Cards.Card;
import Players.Character;
import Players.Player;
import Players.Role;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private Queue<Player> players= new LinkedList<>();
    private GameBoard gameBoard;
    public Game() {
        gameBoard = new GameBoard();
        CreateAndAddPlayers(gameBoard, this);
    }
    public void PlayGame(){
        for(Card card : gameBoard.getDrawPile()){
            System.out.println(card);
        }
    }
    public void CreateAndAddPlayers(GameBoard gameBoard, Game game){
        players.add(new Player("1",game,gameBoard, Role.OUTLAW, Character.PAUL_REGRET));
        players.add(new Player("2",game,gameBoard, Role.SHERIFF, Character.WILLY_THE_KID));
        players.add(new Player("3",game,gameBoard, Role.OUTLAW, Character.JOURDONNAIS));
        players.add(new Player("4",game,gameBoard, Role.OUTLAW, Character.ROSE_DOOLAN));
        //sheriff first to queue then add the rest of the players
        this.players.addAll(players);
        while(!this.players.peek().isSheriff()) {
            nextTurn();
        }
    }

    public void nextTurn(){
        Player player = this.players.remove();
        this.players.add(player);
    }
    public Queue<Player> getPlayers() {
        return players;
    }

}
