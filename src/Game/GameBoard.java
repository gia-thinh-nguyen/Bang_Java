package Game;

import Cards.Card;
import Cards.CardDatabase;
import Cards.LABEL;
import Cards.SUIT;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameBoard {
    private Stack<Card> drawPile;
    private Stack<Card> discardPile;
    public GameBoard(){
        drawPile = new Stack<>();
        discardPile = new Stack<>();
        //shuffle first then add to draw pile
        ArrayList<Card> shuffledCards = new ArrayList<>(CardDatabase.cards);
        Collections.shuffle(shuffledCards);
        AddCardsToDrawPile(shuffledCards);
    }
    public void Discard(Card card){
        discardPile.push(card);
    }
    public void Shuffle(){
        // Randomly shuffle the discard pile and add it to the draw pile
        ArrayList<Card> temp = new ArrayList<>(discardPile);
        Collections.shuffle(temp);
        AddCardsToDrawPile(temp);
        discardPile.clear();
    }
    public Card DrawFromPile(){
        if(drawPile.isEmpty()){
            Shuffle();
        }
        return drawPile.pop();
    }
    public void AddCardsToDrawPile(ArrayList<Card> cards){
        for(int i= cards.size(); i>0; i--){
            drawPile.push(cards.get(i-1));
        }
    }
    public void PutCardBack(Card card){
        drawPile.push(card);
    }

    public Stack<Card> getDrawPile() {
        return drawPile;
    }
    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    public boolean checkTopCard(SUIT suit,boolean checkDynamite){
        Card card = DrawFromPile();
        System.out.println("Top card: " + card);
        SUIT topSuit= card.getSuit();
        int topLabel = card.getLabel().getValue();
        Discard(card);
        if(checkDynamite){
            return topSuit==SUIT.SPADE && topLabel>=2 && topLabel<=9;
        }
        else{
            return topSuit==suit;
        }
    }
}
