//Dimitar Gjorgievski, dgjor2@uic.edu
//CS 342
//Project 2 - Baccarat Game


import java.util.ArrayList;
import java.util.Random;

public class BaccaratDealer {
    ArrayList<Card> deck;

    BaccaratDealer(){
        deck = new ArrayList<>(52);
    }

    public void generateDeck(){
        deck = new ArrayList<>(52);
        
        String s = "Spades";

        for(int i=0; i<52; i++){
            int j=i%13;

            if(i>12 && i<=25){
                s = "Hearts";
            }else if(i>25 && i<=38){
                s = "Diamonds";
            }else if(i>38 && i<=51){
                s = "Clubs";
            }

            Card c = new Card(s, j);
            deck.add(i, c);
        }
    }

    public ArrayList<Card> dealHand(){

        ArrayList<Card> Hand = new ArrayList<>(2);
        
        Hand.add(drawOne());
        Hand.add(drawOne());

        return Hand;
    }

    public Card drawOne(){
        Random random = new Random();
        int draw = random.nextInt(51);
        
        while(deck.get(draw)==null){
            draw = random.nextInt(51);
        }

        String s = deck.get(draw).suite;
        int val = deck.get(draw).value;

        Card c = new Card(s, val);

        deck.set(draw, null);

        return c;

    }

    public void shuffleDeck(){
        generateDeck();
    }

    public int deckSize(){
        int i=0;

        for(Card c : deck){
            if(c!=null){
                i++;
            }
        }

        return i;
    }
}
