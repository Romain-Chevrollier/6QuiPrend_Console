package com.example._6quiprend;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Collections;
public class Deck {
    @Getter @Setter ArrayList<Card> cards;

    public void shuffle(){
        ArrayList<Card> deck = this.getCards();
        Collections.shuffle(deck);
        this.setCards(deck);
    }

    public void drawCard(Player faker, IA ia, Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        ArrayList<Card> liste = this.getCards();
        for (int i = 0; i<=23; i++){

            if (i <= 9){
                ArrayList<Card> liste2 = faker.getHand();
                Card card = liste.get(i);
                liste2.add(card);
                faker.setHand(liste2);
            }
            else if (i <= 19){
                ArrayList<Card> liste2 = ia.getHand();
                Card card = liste.get(i);
                liste2.add(card);
                ia.setHand(liste2);
            }
            else if (i <= 20){
                ArrayList<Card> listRowOne = rowOne.getCards();
                Card card = liste.get(i);
                listRowOne.add(card);
                rowOne.setCards(listRowOne);
            }
            else if (i <= 21){
                ArrayList<Card> listRowTwo = rowTwo.getCards();
                Card card = liste.get(i);
                listRowTwo.add(card);
                rowTwo.setCards(listRowTwo);
            }
            else if (i <= 22){
                ArrayList<Card> listRowThree = rowThree.getCards();
                Card card = liste.get(i);
                listRowThree.add(card);
                rowThree.setCards(listRowThree);
            }
            else if (i <= 23){
                ArrayList<Card> listRowFour = rowFour.getCards();
                Card card = liste.get(i);
                listRowFour.add(card);
                rowFour.setCards(listRowFour);
            }
        }

    }

}
