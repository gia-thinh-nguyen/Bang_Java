package Game;

import Cards.Card;
import Cards.Equipments.EquipmentType;
import Cards.SUIT;
import Cards.TargetType;
import Players.Character;
import Players.Player;

import java.util.*;

public class ActionMenu {

    public static Card showMenu(List<Card> hand,boolean skipOption) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Card> keyToCardMap = new HashMap<>();
        int i;
        for(i=1; i<=hand.size(); i++){
            keyToCardMap.put(i, hand.get(i-1));
            System.out.println(i + ". " + hand.get(i-1).toString());
        }
        if(skipOption){
            keyToCardMap.put(i, null);
            System.out.println(i + ". Skip");
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
        int range = (targetType==TargetType.PANIC ? 1: currentPlayer.getGunRange()) + currentPlayer.getAttackHorse();
        for (int i = 1; i< players.size();i++){
            int targetDefHorse = players.get(i).getDefenseHorse();
            boolean isTargetable = (i<=range- targetDefHorse || players.size()-i<=range-targetDefHorse);
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
        handleBangResponse(currentPlayer, target, 1);
    }

    public static void showRespondToSTKBang(Player currentPlayer, Player target) {
        handleBangResponse(currentPlayer, target, 2);
    }

    private static void handleBangResponse(Player currentPlayer, Player target, int missedCardsNeeded) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + currentPlayer.getName() + " banged Player " + target.getName());

        // Check barrels and reduce missed cards needed
        missedCardsNeeded = checkBarrels(target, missedCardsNeeded);
        if (missedCardsNeeded == 0) return;

        // Display response options
        System.out.println("Respond: ");
        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        System.out.println("1. Skip");
        if (target.numMissedCards() >= missedCardsNeeded) {
            options.add("2");
            System.out.println("2. Play Missed");
        }

        // Get user input
        String key;
        do {
            key = scanner.next();
        } while (!options.contains(key));

        // Handle response
        if (key.equals("1")) {
            target.takeDamage(currentPlayer);
            System.out.println("Player " + target.getName() + " does not respond to bang and takes 1 damage.");
        } else {
            while (missedCardsNeeded > 0) {
                target.discardFirstCardFromHand("Missed");
                System.out.println("Player " + target.getName() + " discard a Missed ");
                missedCardsNeeded--;
            }
        }
    }

    private static int checkBarrels(Player target, int missedCardsNeeded) {
        if (target.getCharacter() == Character.JOURDONNAIS && target.checkBarrel()) {
            missedCardsNeeded--;
        }
        if (missedCardsNeeded > 0 && target.hasBarrel() && target.checkBarrel()) {
            missedCardsNeeded--;
        }
        return missedCardsNeeded;
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

        if (key == 1) {
            Card removedCard = target.removeRandomCardFromHand();
            target.getGameBoard().Discard(removedCard);
            System.out.println("Player " + target.getName() + " discards " + removedCard.toString());
        } else {//remove equipment
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
            // Calamity Janet can use Missed instead of Bang
            if (currentPlayer.getCharacter() == Character.CALAMITY_JANET && currentPlayer.hasCard("Missed")) {
                options++;
                System.out.println("3. Use Missed To Duel");
            }

            int key;
            do {
                key = scanner.nextInt();
            } while (key < 1 || key > options);

            if (key == 1) {
                // Fail to respond
                currentPlayer.takeDamage(opponent);
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
    public static void showGatlingMenu(Player currentPlayer){
        ArrayList<Player> players = new ArrayList<>(currentPlayer.getPlayers());
        for(int i =1; i<players.size();i++){
            showRespondToBangMenu(currentPlayer, players.get(i));
        }
    }
    public static void showIndianiMenu(Player currentPlayer){
        Scanner scanner = new Scanner(System.in);
        //every other has to discard a bang or lose 1 life
        ArrayList<Player> players = new ArrayList<>(currentPlayer.getPlayers());
        System.out.println("Player " + currentPlayer.getName() + " plays Indiani");
        for(int i=1;i<players.size();i++){
            System.out.println("Player " + players.get(i).getName() + "respond to Indiani: ");
            int options = 1;
            System.out.println("1. Not drop Bang and lose 1 life");
            if (players.get(i).hasCard("Bang")) {
                options++;
                System.out.println("2. Drop Bang to respond Indiani");
            }
            //Calamity Janet can drop a Missed instead
            if(players.get(i).getCharacter()== Character.CALAMITY_JANET && players.get(i).hasCard("Missed")){
                options++;
                System.out.println("3. Drop Missed");
            }
            int key;
            do {
                key = scanner.nextInt();
            } while (key < 1 || key > options);
            if (key == 1) {
                // Fail to respond
                players.get(i).takeDamage(currentPlayer);
                System.out.println("Player " + players.get(i).getName() + " fails to respond and takes 1 damage.");
            } else if (key == 2) {
                // Use Bang
                players.get(i).discardFirstCardFromHand("Bang");
                System.out.println("Player " + players.get(i).getName() + " uses Bang to Indiani.");
            } else if (key == 3) {
                // Use Missed
                players.get(i).discardFirstCardFromHand("Missed");
                System.out.println("Player " + players.get(i).getName() + " uses Missed to Indiani.");
            }
        }
    }
    public static void showEmporioMenu(Player currentPlayer){
        System.out.println("Player " + currentPlayer.getName() + " plays Emporio");
        //Draw from pile as many as players, each player chooses one card to keep
        ArrayList<Player> players = new ArrayList<>(currentPlayer.getPlayers());
        GameBoard gameBoard = currentPlayer.getGameBoard();
        ArrayList<Card> emporioCards = new ArrayList<>(players.size());
        for(Player player : players){
            emporioCards.add(gameBoard.DrawFromPile());
        }
        for (Player player : players) {
            System.out.println("Player " + player.getName() + " choose a card to keep: ");
            Card chosenCard = showMenu(emporioCards,false);
            player.addToHand(chosenCard);
            emporioCards.remove(chosenCard);
        }
    }

    public static Player showJesseJonesMenu(Player player){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jesse Jones can steal a card or draw from the pile");
        Map<Integer, Player> keyToPlayerMap = new HashMap<>();
        keyToPlayerMap.put(1,null);
        System.out.println("1. Draw from pile");
        List<Player> players = new ArrayList<>(player.getPlayers());
        int options = 2;
        for(int i =1; i< players.size();i++){
            Player p= players.get(i);
            if(p.getHand().isEmpty()) continue;
            keyToPlayerMap.put(options, p);
            System.out.println(options + ". Steal from " + p.getName());
            options++;
        }
        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToPlayerMap.containsKey(key));
        return keyToPlayerMap.get(key);
    }
    public static Card showKitCarlsonMenu(Card[] drawnCards){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kit Carlson choose a card to put back");
        Map<Integer, Card> keyToCardMap = new HashMap<>();
        for(int i=1; i<=drawnCards.length; i++){
            keyToCardMap.put(i, drawnCards[i-1]);
            System.out.println(i + ". " + drawnCards[i-1].toString());
        }
        int key;
        do {
            key = scanner.nextInt();
        } while (!keyToCardMap.containsKey(key));
        return keyToCardMap.get(key);
    }
    public static Card showPedroRamirezMenu(GameBoard gb){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pedro Ramirez can draw a card from the discard pile");
        int options = 1;
        System.out.println("1. Draw from draw pile");
        if(!gb.getDiscardPile().isEmpty()){
            System.out.println("2. Draw from discard pile");
            options++;
        }
        int key;
        do {
            key = scanner.nextInt();
        } while (key < 1 || key > options);
        if(key==1){
            return gb.DrawFromPile();
        }
        else{
            return gb.DrawFromDiscard();
        }
    }
}
