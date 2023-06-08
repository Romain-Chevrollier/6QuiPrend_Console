package com.example._6quiprend;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class  AbstractPlayer {
    @Getter @Setter ArrayList<Card> hand;
    @Getter @Setter int score;
    @Getter @Setter ArrayList<Card> pileOfCards;

    public void actualiseScore(){
        ArrayList<Card> pileOfCard = this.getPileOfCards();
        int score = 0;
        for (Card i : pileOfCard){
            score += i.getTeteDeBoeuf();
        }
        this.setScore(score);
    }
}
