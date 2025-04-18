package Cards;

import Cards.DrawCards.*;
import Cards.Equipments.GunCards.*;
import Cards.Equipments.*;
import Cards.HealCards.*;
import Cards.OtherCards.*;
import Cards.TargetCards.*;

import java.util.ArrayList;
import java.util.Arrays;


public class CardDatabase {
    public static final ArrayList<Card> cards = new ArrayList<>(Arrays.asList(
            new Gatling(LABEL.TEN, SUIT.SPADE),
            new Duel(LABEL.ACE, SUIT.SPADE),
            new Bang(LABEL.TWO, SUIT.HEART),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Duel(LABEL.ACE, SUIT.SPADE),
            new Bang(LABEL.TWO, SUIT.HEART),
            new Missed(LABEL.SIX, SUIT.SPADE),
            new Bang(LABEL.THREE, SUIT.SPADE),
            new Bang(LABEL.FOUR, SUIT.DIAMOND),
            new CatBalou(LABEL.FIVE, SUIT.HEART),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Remington(LABEL.FIVE, SUIT.SPADE),
            new Schofield(LABEL.SEVEN, SUIT.DIAMOND),
            new RevCarabine(LABEL.EIGHT, SUIT.HEART),
            new Winchester(LABEL.NINE, SUIT.SPADE),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Volcanic(LABEL.QUEEN, SUIT.DIAMOND),
            new Missed(LABEL.SIX, SUIT.SPADE),
            new Missed(LABEL.SEVEN, SUIT.DIAMOND),
            new Beer(LABEL.SIX, SUIT.HEART),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new CatBalou(LABEL.FIVE, SUIT.HEART),
            new Saloon(LABEL.KING, SUIT.SPADE),
            new Panic(LABEL.TWO, SUIT.HEART),
            new Jail(LABEL.TWO, SUIT.DIAMOND),
            new Stagecoach(LABEL.ACE, SUIT.SPADE),

            new WellsFargo(LABEL.JACK, SUIT.DIAMOND),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new CatBalou(LABEL.FIVE, SUIT.HEART),
            new Dynamite(LABEL.TEN, SUIT.SPADE),

            new Mustang(LABEL.FOUR, SUIT.HEART),
            new Remington(LABEL.FIVE, SUIT.SPADE),
            new Schofield(LABEL.SEVEN, SUIT.DIAMOND),
            new RevCarabine(LABEL.EIGHT, SUIT.HEART),
            new Winchester(LABEL.NINE, SUIT.SPADE),
            new Volcanic(LABEL.QUEEN, SUIT.DIAMOND),
            new Mustang(LABEL.FOUR, SUIT.HEART)

    ));
}
