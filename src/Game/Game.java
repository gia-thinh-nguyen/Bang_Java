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
    private Player currentPlayer= null;
    public Game() {
        gameBoard = new GameBoard();
        CreateAndAddPlayers(gameBoard, this);
    }
    public void PlayGame(){
        for(Player player: players){
            player.draw(4);
        }
        while(true){
            System.out.println("----------------------");

            for(Player player: players){
                System.out.println(player);
            }
            System.out.println("Draw pile: " + gameBoard.getDrawPile().size() + ","+
                    " Discard top: " + (gameBoard.getDiscardPile().isEmpty() ? "None" : gameBoard.getDiscardPile().peek()));
            System.out.println("----------------------");
            if(players.peek()!=currentPlayer){
                currentPlayer = players.peek();
                currentPlayer.draw(2);
            }
            System.out.println("Current Player: " + players.peek());
            Card chosenCard=ActionMenu.showMenu(players.peek().getHand());
            //if chosenCard is null, skip
            if(chosenCard!=null){
                chosenCard.played();
            }
            else{
                nextTurn();
            }
        }
    }
    public void CreateAndAddPlayers(GameBoard gameBoard, Game game){
        players.add(new Player("1",game,gameBoard, Role.OUTLAW, Character.PAUL_REGRET));
        players.add(new Player("2",game,gameBoard, Role.SHERIFF, Character.WILLY_THE_KID));
        players.add(new Player("3",game,gameBoard, Role.OUTLAW, Character.JOURDONNAIS));
        players.add(new Player("4",game,gameBoard, Role.VICE, Character.ROSE_DOOLAN));
        //sheriff plays first
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
