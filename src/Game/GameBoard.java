package Game;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameBoard {
    private Stack<Card> drawPile;
    private Stack<Card> discardPile;
    public GameBoard(ArrayList<Card> cards){
        drawPile = new Stack<>();
        discardPile = new Stack<>();
        for(int i= cards.size(); i>0; i--){
            drawPile.push(cards.get(i-1));
        }
    }
    public void Discard(Card card){
        discardPile.push(card);
    }
    public void Shuffle(){
        // Randomly shuffle the discard pile and add it to the draw pile
        ArrayList<Card> temp = new ArrayList<>(discardPile);
        Collections.shuffle(temp);
        for(int i= temp.size(); i>0; i--){
            drawPile.push(temp.get(i-1));
        }
        discardPile.clear();
    }
    public Card DrawFromPile(){
        if(drawPile.isEmpty()){
            Shuffle();
        }
        return drawPile.pop();
    }
}
