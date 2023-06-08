package com.example._6quiprend;

import java.util.ArrayList;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Player extends AbstractPlayer implements InterfacePlayer {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public Card chooseCard(Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a card to play: ");
        ArrayList<Card> listHand = this.getHand();
        int cnt = 1;
        for (Card i : listHand){
            System.out.print("  "+cnt + ".");
            System.out.print(" "+ANSI_BLUE+i.value+ANSI_RESET);
            cnt ++;
        }
        System.out.println();
        int number = scanner.nextInt();
        Card cardToPlace = listHand.get(number - 1);
        listHand.remove(number - 1);
        this.setHand(listHand);
        return cardToPlace;
    }


    public void playCard(Card choosedCardPlayer, Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
            Card cardToPlace = choosedCardPlayer;
            Card cardOne = rowOne.getLastCard();
            Card cardTwo = rowTwo.getLastCard();
            Card cardThree = rowThree.getLastCard();
            Card cardFour = rowFour.getLastCard();
            List<Card> elements = Arrays.asList(cardOne, cardTwo, cardThree, cardFour);
            ArrayList<Card> listCardsRow = new ArrayList<>(elements);
            int numberCard = cardToPlace.getValue();
            int count = 10000;
            int place = 0;
            for (int i = 0 ; i<=3 ; i++){
                int num = numberCard-listCardsRow.get(i).getValue();
                if (num > 0 && num < count){
                    count = num;
                    place = i;
                }
            }
            if (count == 10000){
                this.chooseRow(cardToPlace, rowOne, rowTwo, rowThree, rowFour);
            }
            else{
                switch (place){
                    case (0) -> rowOne.addCard(cardToPlace, this, 1);
                    case (1) -> rowTwo.addCard(cardToPlace, this, 1);
                    case (2) -> rowThree.addCard(cardToPlace, this, 1);
                    case (3) -> rowFour.addCard(cardToPlace, this, 1);
                }
            }
        }
    @Override
    public void chooseRow(Card card, Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a row: ");
        int number = scanner.nextInt();

        switch (number) {
            case (1) -> rowOne.addCard(card, this, 0);
            case (2) -> rowTwo.addCard(card, this, 0);
            case (3) -> rowThree.addCard(card, this, 0);
            case (4) -> rowFour.addCard(card, this, 0);
        }
    }
}
