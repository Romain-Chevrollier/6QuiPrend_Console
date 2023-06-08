package com.example._6quiprend;

import java.util.ArrayList;

public class Game{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";



    public void startGame(){
        Player faker = new Player();
        IA ia = new IA();
        Row rowOne = new Row();
        Row rowTwo = new Row();
        Row rowThree = new Row();
        Row rowFour = new Row();
        ArrayList<Card> list1 = new ArrayList<>();
        ArrayList<Card> list2 = new ArrayList<>();
        ArrayList<Card> list3 = new ArrayList<>();
        ArrayList<Card> list4 = new ArrayList<>();
        ArrayList<Card> list5 = new ArrayList<>();
        ArrayList<Card> list6 = new ArrayList<>();
        ArrayList<Card> list7 = new ArrayList<>();
        ArrayList<Card> list8 = new ArrayList<>();
        faker.setPileOfCards(list1);
        faker.setHand(list2);
        ia.setHand(list3);
        ia.setPileOfCards(list4);
        rowOne.setCards(list5);
        rowTwo.setCards(list6);
        rowThree.setCards(list7);
        rowFour.setCards(list8);
        newDeck(faker, ia, rowOne, rowTwo, rowThree, rowFour);
        ia.chooseAILevel();
        while (!isGameOver(faker)){
            playRound(faker, ia, rowOne, rowTwo, rowThree, rowFour);
            showScore(faker, ia);
        }
        if (faker.getScore() < ia.getScore()){
            System.out.println(ANSI_GREEN+"You are the winner!"+ANSI_RESET);
        }else{
            System.out.println(ANSI_RED+"You lost!"+ANSI_RESET);
        }

    }

    private void playRound(Player faker, IA ia ,Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        showBoard(rowOne, rowTwo, rowThree, rowFour);
        Card choosedCardPlayer = faker.chooseCard(rowOne, rowTwo, rowThree, rowFour);
        Card choosedCardIA = ia.chooseCard(rowOne, rowTwo, rowThree, rowFour);

        System.out.println("Your card is : " +ANSI_GREEN+ choosedCardPlayer.getValue()+ANSI_RESET+ " The AI card is : " +ANSI_GREEN+ choosedCardIA.getValue()+ANSI_RESET);

        if (choosedCardPlayer.getValue()<choosedCardIA.getValue()){
            faker.playCard(choosedCardPlayer, rowOne, rowTwo, rowThree, rowFour);
            ia.playCard(choosedCardIA, rowOne, rowTwo, rowThree, rowFour);
        }else{
            ia.playCard(choosedCardIA, rowOne, rowTwo, rowThree, rowFour);
            faker.playCard(choosedCardPlayer, rowOne, rowTwo, rowThree, rowFour);
        }

    }

    private boolean isGameOver(Player faker){
        return faker.getHand().isEmpty();
    }


    private void newDeck(Player faker, IA ia, Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        Deck deck = new Deck();
        ArrayList<Card> liste = new ArrayList<>();
        for (Card i : Card.values()){
            liste.add(i);
            deck.setCards(liste);
        }
        deck.shuffle();
        deck.drawCard(faker, ia, rowOne, rowTwo, rowThree, rowFour);
    }

    private void showBoard(Row rowOne, Row rowTwo, Row rowThree, Row rowFour){
        System.out.print("Row 1 : ");
        for (Card i : rowOne.getCards()){
            System.out.print(" " +ANSI_YELLOW+i.getValue()+ANSI_RESET);
        }
        System.out.println();
        System.out.print("Row 2 : ");
        for (Card i : rowTwo.getCards()){
            System.out.print(" " + ANSI_YELLOW +i.getValue() + ANSI_RESET);
        }
        System.out.println();
        System.out.print("Row 3 : ");
        for (Card i : rowThree.getCards()){
            System.out.print(" " +ANSI_YELLOW+i.getValue()+ANSI_RESET);
        }
        System.out.println();
        System.out.print("Row 4 : ");
        for (Card i : rowFour.getCards()){
            System.out.print(" " +ANSI_YELLOW+i.getValue()+ANSI_RESET);
        }
        System.out.println();
    }

    private void showScore(Player faker, IA ia){
        faker.actualiseScore();
        ia.actualiseScore();
        System.out.println("Your score is : " + ANSI_RED+faker.getScore() +ANSI_RESET+ " The AI score is : " +ANSI_RED+ ia.getScore()+ANSI_RESET);
    }
}
