package com.example._6quiprend;

public interface InterfacePlayer {
    Card chooseCard(Row rowOne, Row rowTwo, Row rowThree, Row rowFour);
    void chooseRow(Card card, Row rowOne, Row rowTwo, Row rowThree, Row rowFour);
}
