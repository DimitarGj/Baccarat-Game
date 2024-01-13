import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

    Card card = new Card("Spades", 10);;
    BaccaratDealer dealer = new BaccaratDealer();
    BaccaratGameLogic gameLogic  = new BaccaratGameLogic();

    @Test
    public void testCardConstructor() {
        assertNotNull(card);
        assertEquals("Spades", card.suite);
        assertEquals(10, card.value);
    }

    @Test
    public void testBaccaratDealerConstructor() {
        assertNotNull(dealer);
    }

    @Test
    public void testGenerateDeck1() {
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }
   
    @Test
    public void testGenerateDeck2() {
        dealer.generateDeck();
        dealer.dealHand();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void testDealHand1() {
        dealer.shuffleDeck();
        assertEquals(2, dealer.dealHand().size());
    }

    @Test
    public void testDealHand2() {
        dealer.shuffleDeck();
        assertEquals(2, dealer.dealHand().size());
        assertEquals(50, dealer.deckSize());
    }

    @Test
    public void testShuffleDeck1() {
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void testShuffleDeck2() {
        dealer.generateDeck();
        dealer.dealHand();
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void testDeckSize1() {
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void testDeckSize2() {
        dealer.generateDeck();
        dealer.dealHand();
        assertEquals(50, dealer.deckSize());
    }

    @Test
    public void testBaccaratGameLogicConstructor() {
        assertNotNull(gameLogic);
    }

    @Test
    public void testWhoWon1() {
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 4);
        Card card3 = new Card("Hearts",7);
        Card card4 = new Card("Clubs", 0);
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        hand1.add(card1);
        hand1.add(card2);
        hand2.add(card3);
        hand2.add(card4);
        assertEquals("Bank", gameLogic.whoWon(hand1, hand2), "Wrong Value"); 
    }

    @Test
    public void testWhoWon2() {
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 4);
        Card card3 = new Card("Hearts",5);
        Card card4 = new Card("Clubs", 1);
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        hand1.add(card1);
        hand1.add(card2);
        hand2.add(card3);
        hand2.add(card4);
        assertEquals("Tie", gameLogic.whoWon(hand1, hand2), "Wrong Value"); 
    }

    @Test
    public void testHandTotal1() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 4);
        hand.add(card1);
        hand.add(card2);
        assertEquals(6, gameLogic.handTotal(hand), "Wrong Value"); 
    }

    @Test
    public void testHandTotal2() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 6);
        Card card2 = new Card("Hearts", 4);
        hand.add(card1);
        hand.add(card2);
        assertEquals(0, gameLogic.handTotal(hand), "Wrong Value"); 
    }

    @Test
    public void testEvaluatePlayerDraw1() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 6);
        Card card2 = new Card("Hearts", 2);
        hand.add(card1);
        hand.add(card2);
        assertEquals(false, gameLogic.evaluatePlayerDraw(hand), "Wrong Value"); 
    }

    @Test
    public void testEvaluatePlayerDraw2() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 2);
        hand.add(card1);
        hand.add(card2);
        assertEquals(true, gameLogic.evaluatePlayerDraw(hand), "Wrong Value"); 
    }

    @Test
    public void testEvaluateBankerDraw1() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 2);
        hand.add(card1);
        hand.add(card2);
        Card playCard = new Card("Diamonds", 4);
        assertEquals(true, gameLogic.evaluateBankerDraw(hand, playCard), "Wrong Value"); 
    }

    @Test
    public void testEvaluateBankerDraw2() {
        ArrayList<Card> hand = new ArrayList<>();
        Card card1 = new Card("Spades", 2);
        Card card2 = new Card("Hearts", 2);
        hand.add(card1);
        hand.add(card2);
        Card playCard = new Card("Diamonds", 1);
        assertEquals(false, gameLogic.evaluateBankerDraw(hand, playCard), "Wrong Value"); 
    }



}
