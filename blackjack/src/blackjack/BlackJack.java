package blackjack;
import blackjack.GUI;

import java.util.Scanner;
public class BlackJack {
    static Game Start = new Game();
    static boolean dealer_win = false;
    static int counter_User = 0;
    static boolean dealer_higher = false;

    public static void main(String[] args) {
        GUI gui = new GUI();
        Start.set_deck(Start.deck);
        Start.setPlayers();
        gui.runGUI(Start.deck, Start.players[0].getPlayer_cards(), Start.players[1].getPlayer_cards(), Start.players[2].getPlayer_cards(), Start.players[3].getPlayer_cards());
        hit_Stand();
        dealer_Hit();
        win_Lose();
    }


    //A user drawing a card or passing their level
    public static void hit_Stand() {
        int begin;
        GUI gui = new GUI();
        Scanner input = new Scanner(System.in);

        //main menu
        for (int i = 0; i < 3; i++) {
            System.out.println("..........................");
            int a = 2;
            System.out.println("1- Hit");
            System.out.println("2- Stand");
            System.out.println("For player " + Start.players[i].getName());
            begin = input.nextInt();

            while (begin == 1) {
                Card gui_card = Start.drawrandom(Start.deck);
                Start.players[i].setPlayer_cards(gui_card, a);
                a++;
                gui.updatePlayerHand(gui_card, i);
                System.out.println(Start.players[i].getScore());
                Start.setHighest_score();

                if (Start.players[i].getScore() > 21) {
                    Start.players[i].busted = true;
                    counter_User++;
                    break;
                } else if (Start.players[i].getScore() == 21)
                    break;
                else
                    begin = input.nextInt();
            }
            if (begin == 2) {
                Start.setHighest_score();
                System.out.println(Start.players[i].getScore());
                continue;
            }
        }

    }

    //Dealer drawing Cards from the deck
    public static void dealer_Hit() {
        GUI gui = new GUI();

        Game.highest_score = 0;
        for (int i = 0; i < 3; i++) {
            if (Start.players[i].busted == false) {
                if (Start.players[i].getScore() > Game.highest_score)
                    Game.highest_score = Start.players[i].getScore();
            }
        }
        if (Start.players[3].getScore() > Game.highest_score && Start.players[3].getScore() <= 21 || counter_User == 3)
        {

            System.out.println("Dealer has won");
            System.out.println("Dealer Score " + Start.players[3].getScore());
            dealer_win = true;
            return;
        }
        else if (Start.players[3].getScore() <= Game.highest_score) {
            for (int i = 2; i < 11; i++) {
                Card gui_card = Start.drawrandom(Start.deck);
                Start.players[3].setPlayer_cards(gui_card, i);
                gui.updateDealerHand(gui_card, Start.deck);
                if (Start.players[3].getScore() > Game.highest_score && Start.players[3].getScore() < 21 )
                {
                    System.out.println("Dealer has WON");
                    Start.setHighest_score();
                    dealer_higher = true;
                    return;
                }
                if (Start.players[3].getScore() == 21)
                {
                    Start.setHighest_score();
                    break;
                }
                if (Start.players[3].getScore() > 21)
                {
                    Start.setHighest_score();
                    break;
                }
                if (Start.players[3].getScore() < 21 && Start.players[3].getScore() < Game.highest_score)
                {
                    Start.setHighest_score();
                    continue;
                }
            }
        }
        System.out.println("Dealer Score is " + Start.players[3].getScore());
        System.out.println("................................");
    }

        //Choosing the winner and loser
    public static void win_Lose () {
        if (dealer_win) {
            return;
        } else if (dealer_higher)
            return;

        int counter = 0;
        int blackJackWinner = 0;
        for (int i = 0; i < 4; i++) {
            if (Start.players[i].getScore() == 21) {
                counter++;
                blackJackWinner = i;
            }
        }

        if (counter > 1) {
            System.out.println("PUSH");
            return;
        } else if (counter == 1) {
            System.out.println("Player " + Start.players[blackJackWinner].getName() + " Got a BlackJack (Winner)");
            return;
        }


        int count_busted = 0;
        int winner = 0;
        Game.highest_score = 0;
        for (int i = 0; i < 3; i++) {
            if (Start.players[i].busted) {
                count_busted++;
            } else if (Start.players[i].busted == false) {
                if (Start.players[i].getScore() > Game.highest_score)
                    Game.highest_score = Start.players[i].getScore();
            }
        }
        if (Start.players[3].getScore() > 21)
            count_busted++;

        for (int i = 0; i < 3; i++) {
            if (Start.players[i].busted == false)
                if (Start.players[i].getScore() >= Game.highest_score) {
                    counter++;
                    winner = i;
                }
        }
        if (counter > 1) {
            System.out.println("PUSH");
            return;
        } else if (counter == 1) {
            System.out.println("Player " + Start.players[winner].getName() + "has won");
            return;
        } else if (count_busted == 4) {
            System.out.println("PUSH (Everyone lost)");
            return;
        }
    }
}




