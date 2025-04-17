package Game;

import Cards.Card;
import Cards.TargetType;
import Players.Player;

import java.util.*;

public class ActionMenu {

    public static Card showMenu(List<Card> hand) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Card> keyToCardMap = new HashMap<>();
        int i;
        for(i=1; i<=hand.size(); i++){
            keyToCardMap.put(i, hand.get(i-1));
            System.out.println(i + ". " + hand.get(i-1).toString());
        }
        keyToCardMap.put(i, null);
        System.out.println(i + ". Skip");
        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToCardMap.containsKey(key));
        return keyToCardMap.get(key);
    }
    public static Player showTargetablePlayer(Player currentPlayer, TargetType targetType) {
        System.out.println("Choose a target :");
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Player> keyToPlayerMap = new HashMap<>();
        List<Player> players = new ArrayList<>(currentPlayer.getPlayers());
        ArrayList<Player> targetablePlayers = new ArrayList<>();
        int range = (targetType==TargetType.PANIC ? 1: currentPlayer.getGunRange()) + (currentPlayer.getHorse() == -1 ? 1:0);
        for (int i = 1; i< players.size();i++){
            boolean isTargetable = (i<=range || players.size()-i<=range) ||
                    (players.get(i).getHorse()==1 && (i<=range-1 || players.size()-i<=range-1));
            if((targetType==TargetType.CATBALOU) || (targetType==TargetType.DUEL) ||
                (targetType==TargetType.JAIL && !players.get(i).isSheriff()) ||
                ((targetType==TargetType.BANG||targetType==TargetType.PANIC)&& isTargetable)){
                targetablePlayers.add(players.get(i));
            }
        }
        for(int i=1; i<=targetablePlayers.size(); i++){
            keyToPlayerMap.put(i, targetablePlayers.get(i-1));
            System.out.println((i) + ". " + targetablePlayers.get(i-1).toString());
        }

        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToPlayerMap.containsKey(key));
        return keyToPlayerMap.get(key);
    }
    public static void showRespondToBangMenu(Player currentPlayer, Player target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + currentPlayer.getName() + " banged Player " + target.getName());
        //if has barrel, check the top card to see if its suit is heart
        if(currentPlayer.hasBarrel()){
            System.out.println("Checking barrel...");
            Card topCard = currentPlayer.getGameBoard().checkTopCard();
            if(topCard.getSuit() == Cards.SUIT.HEART){
                System.out.println("Barrel activated! You are safe.");
                return;
            }
            else{
                System.out.println("Barrel failed! You are hit.");
            }
        }
        System.out.println("Respond: ");
        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        System.out.println("1. Skip");
        //if has missed cards, add option to use them
        if(target.hasCard("Missed")){
            options.add("m");
            System.out.println("m. Missed");
        }
        //if almost dead, add option to use heal (beer and saloon)
        if(target.hasCard("Beer")){
            options.add("b");
            System.out.println( "b. Beer");
        }
        if(target.hasCard("Saloon")){
            options.add("s");
            System.out.println("s. Saloon");
        }
        String key;
        do {
            key = scanner.next();
        } while (!options.contains(key));
        switch (key){
            case "1":
                //take damage
                target.takeDamage(currentPlayer,1);
                System.out.println("Player does not respond to bang and takes 1 damage.");
                break;
            case "m":
                //use missed
                target.playCardToBang("Missed");
                System.out.println("Player uses missed.");
                break;
            case "b":
                //take damage and use beer
                target.takeDamage(currentPlayer,1);
                target.playCardToBang("Beer");
                System.out.println("Player uses beer.");
                break;
            case "s":
                //use saloon
                target.playCardToBang("Saloon");
                System.out.println("Player uses saloon.");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}
