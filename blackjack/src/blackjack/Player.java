package blackjack;

public class Player {
    private String Name;
    private int Score = 0;
    private Card[] Player_cards = new Card[11];
    int card_counter = 0;
    boolean busted = false;
    boolean won = false;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Card[] getPlayer_cards() {
        return Player_cards;
    }

    //set score for the cards
    public void setPlayer_cards(Card incoming_card, int index) {
        if(card_counter < 11) {
            Player_cards[index] = incoming_card;
            card_counter++;
            Score += incoming_card.getValue();
        }
    }
}
