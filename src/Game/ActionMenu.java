package Game;

import Cards.Card;
import Cards.TargetType;
import Players.Player;

import java.util.*;

public class ActionMenu {

    public static Card showMenu(List<Card> hand) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Card> keyToCardMap = new HashMap<>();
        for(int i=1; i<=hand.size(); i++){
            keyToCardMap.put(i, hand.get(i-1));
            System.out.println(i + ". " + hand.get(i-1).toString());
        }
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
            if(targetType==TargetType.CATBALOU || targetType==TargetType.DUEL ||
                targetType==TargetType.JAIL && !players.get(i).isSheriff() ||
                (targetType==TargetType.BANG||targetType==TargetType.PANIC)&& isTargetable){
                targetablePlayers.add(players.get(i));
            }
        }
        for(int i=1; i<targetablePlayers.size(); i++){
            keyToPlayerMap.put(i, targetablePlayers.get(i-1));
            System.out.println((i) + ". " + targetablePlayers.get(i-1).toString());
        }

        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToPlayerMap.containsKey(key));
        return keyToPlayerMap.get(key);
    }
}
