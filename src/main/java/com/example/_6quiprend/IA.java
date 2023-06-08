package com.example._6quiprend;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

public class IA extends AbstractPlayer implements InterfacePlayer{
    @Getter @Setter int level;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_ORANGE ="\u001B[38;5;208m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void chooseAILevel(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a level for the AI: "+ANSI_GREEN+"1. Easy"+ANSI_ORANGE+"  2. Medium"+ANSI_RESET+ANSI_RESET+ANSI_RED+"  3. Hard"+ANSI_RESET);
        int number = scanner.nextInt();

        switch (number) {
            case (1) -> this.setLevel(1);
            case (2) -> this.setLevel(2);
            case (3) -> this.setLevel(3);
        }
    }

    @Override
    public Card chooseCard(Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        ArrayList<Card> listOfCard = this.getHand();
        Random random = new Random();
        switch (this.getLevel()){
            case(1):
                int number = random.nextInt(listOfCard.size());
                Card cardToPlace = listOfCard.get(number);
                listOfCard.remove(number);
                this.setHand(listOfCard);
                return cardToPlace;
            case(2):
                int number1 = this.findSmallestCard();
                Card card = listOfCard.get(number1);
                listOfCard.remove(number1);
                this.setHand(listOfCard);
                return card;
            case(3):
                int number2 = findSmallestGap(rowOne, rowTwo, rowThree, rowFour);
                Card cardPlace = listOfCard.get(number2);
                listOfCard.remove(number2);
                this.setHand(listOfCard);
                return cardPlace;
        }
        return listOfCard.get(0);
    }

    public void playCard(Card choosedCardIA, Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        Card cardToPlace = choosedCardIA;
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
        Random random = new Random();
        int level = this.getLevel();
        switch (level) {
            case (1) -> {
                int number = random.nextInt(4);
                switch (number) {
                    case (0) -> rowOne.addCard(card, this, 0);
                    case (1) -> rowTwo.addCard(card, this, 0);
                    case (2) -> rowThree.addCard(card, this, 0);
                    case (3) -> rowFour.addCard(card, this, 0);
                }
            }
            case  (2),(3) -> {
                switch (findBestRow(rowOne, rowTwo, rowThree, rowFour)) {
                    case (0) -> rowOne.addCard(card, this, 0);
                    case (1) -> rowTwo.addCard(card, this, 0);
                    case (2) -> rowThree.addCard(card, this, 0);
                    case (3) -> rowFour.addCard(card, this, 0);
                }
            }
        }
    }

    private int findBestRow(Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        List<Row> elements = Arrays.asList(rowOne, rowTwo, rowThree, rowFour);
        int k=0;
        int numberRow = 0;
        int count = 8;
        int tampon = 0;
        for (Row i : elements){
            ArrayList<Card> cards = i.getCards();
            for (Card j : cards){
                tampon += j.getTeteDeBoeuf();
            }
            if (tampon < count){
                count = tampon;
                numberRow = k;
            }
            k+=1;
        }
        return numberRow;
    }

    private int findSmallestCard(){
        ArrayList<Card> listHand = this.getHand();
        int count = 105;
        int cardToReturn = 0;
        for (int i = 0; i < listHand.size();i++){
            if (listHand.get(i).getValue()<count){
                count = listHand.get(i).getValue();
                cardToReturn = i;
            }
        }
        return cardToReturn;
    }

    private int findSmallestGap(Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        Card cardOne = rowOne.getLastCard();
        Card cardTwo = rowTwo.getLastCard();
        Card cardThree = rowThree.getLastCard();
        Card cardFour = rowFour.getLastCard();
        List<Card> elements = Arrays.asList(cardOne, cardTwo, cardThree, cardFour);
        ArrayList<Card> listCardsRow = new ArrayList<>(elements);
        int gap = 1000;
        int k = 0;
        for (int j = 0; j < this.getHand().size();j++){
            int numberCard = this.getHand().get(j).getValue();
            for (int i = 0 ; i<=3 ; i++){
                int num = numberCard-listCardsRow.get(i).getValue();
                if (num > 0 && num < gap){
                    gap = num;
                    k=j;
                }
            }
        }
        if (gap == 1000){
            return this.findSmallestCard();
        }
        return k;
    }
}
