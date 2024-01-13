//Dimitar Gjorgievski, dgjor2@uic.edu
//CS 342
//Project 2 - Baccarat Game


import java.util.ArrayList;


public class BaccaratGameLogic {

    BaccaratDealer dealer = new BaccaratDealer();

    BaccaratGameLogic(){
        dealer = new BaccaratDealer();
    }
    
     
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int totalP = 0;
        int totalB = 0;

        if(hand1.size()==2){
            totalP = handTotal(hand1);
        }else{
            totalP = hand1.get(0).value + hand1.get(1).value;
            
            if(totalP>9){
                totalP -= 10;
            }
            
            totalP+=hand1.get(2).value;
            
            if(totalP>9){
                totalP -= 10;
            }

        }

        if(hand2.size()==2){
            totalB = handTotal(hand2);
        }else{
            totalB = hand2.get(0).value + hand2.get(1).value;
            
            if(totalB>9){
                totalB -= 10;
            }
            
            totalB+=hand2.get(2).value;
            
            if(totalB>9){
                totalB -= 10;
            }

        }


        if(totalP > totalB){
            return "Player";

        }else if(totalP < totalB){
            return "Bank";
            
        }

        return "Tie";

    }

    public int handTotal(ArrayList<Card> hand){
        int val;
        int total = 0;

        for(Card c : hand){
            val = c.value;

            if(val < 1 || val > 9){
                val = 0;
            }

            total += val;
        }

        if(total > 9){
           return (10-total); 
        }

        return total;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){

        if(handTotal(hand) < 3){
            return true;

        }else if(handTotal(hand) < 6 && playerCard == null){
            return true;

        }else if(handTotal(hand) == 6 && (playerCard.value == 6 || playerCard.value == 7)){
            return true;

        }else if(handTotal(hand) == 5  && playerCard.value < 8 && !(playerCard.value > -1 && playerCard.value < 4)){
            return true;

        }else if(handTotal(hand) == 4  && playerCard.value < 8 && playerCard.value !=0 && playerCard.value != 1){
            return true;

        }

        return false;

    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand){

        if(handTotal(hand) < 6){
            return true;
        }

        return false;
    }

}
