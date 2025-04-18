package Game;

import Cards.Card;
import Players.Character;
import Players.Heros.*;
import Players.Player;
import Players.Role;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private final Queue<Player> players= new LinkedList<>();
    private final GameBoard gameBoard;
    private Player currentPlayer= null;
    public Game() {
        gameBoard = new GameBoard();
        CreateAndAddPlayers(gameBoard, this);
    }
    public void PlayGame(){
        for(Player player: players){
            player.draw(player.getHealth());
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
                if(!currentPlayer.firstPhase()){
                    nextTurn();
                }
            }
            System.out.println("Current Player: " + players.peek());
            Card chosenCard=ActionMenu.showMenu(players.peek().getHand(),true);
            //if chosenCard is null, skip
            if(chosenCard!=null){
                chosenCard.played();
            }
            else{
                currentPlayer.discardPhase();
                nextTurn();
            }

        }
    }
    public void CreateAndAddPlayers(GameBoard gameBoard, Game game){
        players.add(new SuzyLafayette("1",game,gameBoard, Role.OUTLAW));
        players.add(new SlabTheKiller("2",game,gameBoard, Role.SHERIFF));
        players.add(new CalamityJanet("3",game,gameBoard, Role.OUTLAW));
        players.add(new WillyTheKid("4",game,gameBoard, Role.VICE));
        //sheriff plays first
        while(!this.players.peek().isSheriff()) {
            nextTurn();
        }
    }

    public void nextTurn(){
        Player player = this.players.remove();
        this.players.add(player);
    }
    public Player getNextPlayer() {
        return new ArrayList<>(players).get(1);
    }
    public Queue<Player> getPlayers() {
        return players;
    }
    public void removePlayer(Player player){
        players.remove(player);
    }
    public boolean hasVultureSam(){
        for(Player player: players){
            if(player.getCharacter() == Character.VULTURE_SAM){
                return true;
            }
        }
        return false;
    }
    public Player getVultureSam(){
        for(Player player: players){
            if(player.getCharacter() == Character.VULTURE_SAM){
                return player;
            }
        }
        return null;
    }
    public boolean isRenegadeOnlyOneAlive(){
        return players.peek().getRole() == Role.RENEGADE && players.size() == 1;
    }
    public boolean isAllOutlawsAndRenegedeDead(){
        for(Player player: players){
            if(player.getRole() == Role.OUTLAW || player.getRole() == Role.RENEGADE){
                return false;
            }
        }
        return true;
    }
}
