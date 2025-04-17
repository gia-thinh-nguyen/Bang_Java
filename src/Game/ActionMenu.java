package Game;

import Cards.Card;
import Cards.Equipments.Equipment;
import Cards.Equipments.EquipmentType;
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
            //can CatBalou if the target has cards or equipments
            boolean hasEquipment = players.get(i).getEquipmentMap().values().stream().anyMatch(Objects::nonNull);
            boolean hasCards = !players.get(i).getHand().isEmpty();
            boolean canCatBalou = hasCards || hasEquipment;
            if((targetType==TargetType.CATBALOU && canCatBalou) || (targetType==TargetType.DUEL) ||
                (targetType==TargetType.JAIL && !players.get(i).isSheriff()) ||
                (targetType==TargetType.BANG&& isTargetable) ||
                (targetType==TargetType.PANIC && isTargetable && hasCards)){
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
                target.playFistCardFromHand("Missed");
                System.out.println("Player uses missed.");
                break;
            case "b":
                //take damage and use beer
                target.takeDamage(currentPlayer,1);
                target.playFistCardFromHand("Beer");
                System.out.println("Player uses beer.");
                break;
            case "s":
                //use saloon
                target.playFistCardFromHand("Saloon");
                System.out.println("Player uses saloon.");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
    public static void showCatBalouMenu(Player target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + target.getName() + " is targeted by CatBalou");
        System.out.println("Choose a card to discard: ");
        //option 1 discard a random card from hand, other options are to discard equipments if any
        Map<Integer, EquipmentType> keyToEquipmentMap = new HashMap<>();
        keyToEquipmentMap.put(1, null);
        System.out.println("1. Remove random card from hand");
        //remove equipments from 2 onwards
        int i = 2;
        for(EquipmentType key : target.getEquipmentMap().keySet()){
            if(target.getEquipmentMap().get(key) == null){
                continue;
            }
            keyToEquipmentMap.put(i, key);
            System.out.println(i + ". Remove " + key);
            i++;
        }
        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToEquipmentMap.containsKey(key));
        switch (key){
            case 1:
                Card removedCard = target.removeRandomCardFromHand();
                target.getGameBoard().Discard(removedCard);
                System.out.println("Player " + target.getName() + " discards " + removedCard.toString());
                break;
            default:
                //remove equipment
                EquipmentType equipmentType = keyToEquipmentMap.get(key);
                target.removeEquipment(equipmentType);
                System.out.println("Player " + target.getName() + " remove their " + equipmentType);
        }
    }
    public static void showDuelMenu(Player dueler, Player target) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + dueler.getName() + " is dueling Player " + target.getName());

        // Start the duel loop
        Player currentPlayer = target;
        Player opponent = dueler;

        while (true) {
            System.out.println("Current duel action: Player " + currentPlayer.getName());
            int options = 1;
            System.out.println("1. Fail to respond");
            if (currentPlayer.hasCard("Bang")) {
                options++;
                System.out.println("2. Use Bang");
            }

            int key;
            do {
                key = scanner.nextInt();
            } while (key < 1 || key > options);

            if (key == 1) {
                // Fail to respond
                currentPlayer.takeDamage(opponent, 1);
                System.out.println("Player " + currentPlayer.getName() + " fails to respond and takes 1 damage.");
                break; // End the duel
            } else if (key == 2) {
                // Use Bang
                currentPlayer.discardFirstCardFromHand("Bang");
                System.out.println("Player " + currentPlayer.getName() + " uses Bang.");
            }

            // Swap players for the next turn
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }
}
