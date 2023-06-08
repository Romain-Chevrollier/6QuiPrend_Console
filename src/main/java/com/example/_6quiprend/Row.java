package com.example._6quiprend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.*;
public class Row {
    @Getter @Setter ArrayList<Card> cards;

    public void addCard(Card card, AbstractPlayer player, int test){
        ArrayList<Card> list = this.getCards();
        if (this.isFull() || test == 0){
            this.clear(player);
        }
        list.add(card);
        this.setCards(list);
    }

    public Card getLastCard(){
        ArrayList<Card> listCardsRow = this.getCards();
        return listCardsRow.get(listCardsRow.size()-1);
    }

    public boolean isFull(){
        ArrayList<Card> listCardsRow = this.getCards();
        return listCardsRow.size() == 5;
    }

    public void clear(AbstractPlayer player){
        ArrayList<Card> pileOfCards = player.getPileOfCards();      //We take the pile of the player and add the 5 cards
        pileOfCards.addAll(this.getCards());                        //of the row in it
        ArrayList<Card> list = this.getCards();
        list.clear();                                               //Clear the row
        this.setCards(list);
    }
}
