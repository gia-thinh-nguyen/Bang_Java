package Game;

import Cards.Card;
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
    public static Player showTargetablePlayer(Queue<Player> playerQueue, int range){
        System.out.println("Choose a target :");
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Player> keyToPlayerMap = new HashMap<>();
        List<Player> players = new ArrayList<>(playerQueue);
        //add clockwise players
        for(int i=1; i<=range;i++){
            keyToPlayerMap.put(i, players.get(i));
            System.out.println(i + ". Player: " + players.get(i));
        }
        //add counter-clockwise players
        for(int i=1; i<=range;i++){
            keyToPlayerMap.put(i+range, players.get(players.size()-i));
            System.out.println((i+range) + ". Player: " + players.get(players.size()-i));
        }
        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToPlayerMap.containsKey(key));
        return keyToPlayerMap.get(key);

    }
}
