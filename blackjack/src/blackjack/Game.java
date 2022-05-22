package blackjack;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Player[] players = new Player[4];
    Card[] deck = new Card[52];
    static int highest_score; //<=21

    //setting the deck cards
    public void set_deck(Card cards[]) {
        int rank = 0;
        int value = 1;
        for (int i = 0; i < 13; i++) {
            if( i >= 10){
                deck[i] = new Card(0, rank, 10);
                continue;
            }
            else {
                deck[i] = new Card(0, rank, value);
                rank++;
                value++;
            }
        }
        rank = 0;
        value = 1;
        for (int i = 13; i < 26; i++) {
            if( i >= 23){
                deck[i] = new Card(1, rank, 10);
                continue;
            }
            else deck[i] = new Card(1, rank, value);
                rank++;
                value++;
        }

        rank = 0;
        value = 1;
        for (int i = 26; i < 39; i++) {
            if( i >= 36){
                deck[i] = new Card(2, rank, 10);
                continue;
            }
            else deck[i] = new Card(2, rank, value);
                rank++;
                value++;
        }

        rank = 0;
        value = 1;
        for (int i = 39; i < 52; i++) {
            if( i >= 49){
                deck[i] = new Card(3, rank, 10);
                continue;
            }
            else deck[i] = new Card(3,rank,value);
            rank++;
            value++;
        }

    }
    //drawing a random card from the deck and setting its place to random
    public Card drawrandom(Card cards[]){
        Random rand = new Random();
        Card newcard = null ;
        int randomCard;
        do{
                randomCard = rand.nextInt(52);
                newcard = deck[randomCard];
                deck[randomCard] = null ;
        }while(newcard == null);
    return newcard;
    }
        /*Adding new players and giving each 2 cards and adjusting initial
            and highest score for each player
         */
        public void setPlayers(){
            Scanner input = new Scanner(System.in);
            for(int i = 0 ; i < 3 ; i ++) {
                System.out.println("Enter player " + (i+1) + " Name : ");
                String Name = input.next();
                players[i] = new Player();
                players[i].setName(Name);
                players[i].setPlayer_cards(drawrandom(deck),0);
                players[i].setPlayer_cards(drawrandom(deck),1);
                System.out.println("player" + (i+1) + "name is : " + players[i].getName());
                System.out.println("...........................");
            }
            //Adding dealer and setting his cards and score
            players[3] = new Player();
            players[3].setName("Dealer");
            players[3].setPlayer_cards(drawrandom(deck),0);
            players[3].setPlayer_cards(drawrandom(deck),1);
        }

        //Updating the highest Score of the game
    public void setHighest_score()
    {
        for (int i = 0; i < 4; i++)
        {
            if (players[i].getScore() > highest_score)
            {
                if(players[i].getScore() > 21)
                    continue;
                highest_score = players[i].getScore();

            }
        }
    }
}
