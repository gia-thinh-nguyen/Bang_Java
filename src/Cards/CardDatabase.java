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
            // Bang cards
            new Bang(LABEL.ACE, SUIT.SPADE),
            new Bang(LABEL.TWO, SUIT.DIAMOND), new Bang(LABEL.THREE, SUIT.DIAMOND), new Bang(LABEL.FOUR, SUIT.DIAMOND),
            new Bang(LABEL.FIVE, SUIT.DIAMOND), new Bang(LABEL.SIX, SUIT.DIAMOND), new Bang(LABEL.SEVEN, SUIT.DIAMOND),
            new Bang(LABEL.EIGHT, SUIT.DIAMOND), new Bang(LABEL.NINE, SUIT.DIAMOND), new Bang(LABEL.TEN, SUIT.DIAMOND),
            new Bang(LABEL.JACK, SUIT.DIAMOND), new Bang(LABEL.QUEEN, SUIT.DIAMOND), new Bang(LABEL.KING, SUIT.DIAMOND),
            new Bang(LABEL.ACE, SUIT.DIAMOND),
            new Bang(LABEL.TWO, SUIT.CLUB), new Bang(LABEL.THREE, SUIT.CLUB), new Bang(LABEL.FOUR, SUIT.CLUB),
            new Bang(LABEL.FIVE, SUIT.CLUB), new Bang(LABEL.SIX, SUIT.CLUB), new Bang(LABEL.SEVEN, SUIT.CLUB),
            new Bang(LABEL.EIGHT, SUIT.CLUB), new Bang(LABEL.NINE, SUIT.CLUB),
            new Bang(LABEL.QUEEN, SUIT.HEART), new Bang(LABEL.KING, SUIT.HEART), new Bang(LABEL.ACE, SUIT.HEART),

            // Missed cards
            new Missed(LABEL.TEN, SUIT.CLUB), new Missed(LABEL.JACK, SUIT.CLUB), new Missed(LABEL.QUEEN, SUIT.CLUB),
            new Missed(LABEL.KING, SUIT.CLUB), new Missed(LABEL.ACE, SUIT.CLUB),
            new Missed(LABEL.TWO, SUIT.SPADE), new Missed(LABEL.THREE, SUIT.SPADE), new Missed(LABEL.FOUR, SUIT.SPADE),
            new Missed(LABEL.FIVE, SUIT.SPADE), new Missed(LABEL.SIX, SUIT.SPADE), new Missed(LABEL.SEVEN, SUIT.SPADE),
            new Missed(LABEL.EIGHT, SUIT.SPADE),

            // Barrel cards
            new Barrel(LABEL.QUEEN, SUIT.SPADE), new Barrel(LABEL.KING, SUIT.SPADE),

            // Dynamite card
            new Dynamite(LABEL.TWO, SUIT.HEART),

            // Jail cards
            new Jail(LABEL.JACK, SUIT.SPADE), new Jail(LABEL.FOUR, SUIT.HEART), new Jail(LABEL.TEN, SUIT.SPADE),

            // Mustang cards
            new Mustang(LABEL.EIGHT, SUIT.HEART), new Mustang(LABEL.NINE, SUIT.HEART),

            // Remington card
            new Remington(LABEL.KING, SUIT.CLUB),

            // RevCarabine card
            new RevCarabine(LABEL.ACE, SUIT.CLUB),

            // Schofield cards
            new Schofield(LABEL.JACK, SUIT.CLUB), new Schofield(LABEL.QUEEN, SUIT.CLUB), new Schofield(LABEL.KING, SUIT.SPADE),

            // Scope card
            new Scope(LABEL.ACE, SUIT.SPADE),

            // Volcanic cards
            new Volcanic(LABEL.TEN, SUIT.CLUB), new Volcanic(LABEL.TEN, SUIT.SPADE),

            // Winchester card
            new Winchester(LABEL.EIGHT, SUIT.SPADE),

            // Beer cards
            new Beer(LABEL.SIX, SUIT.HEART), new Beer(LABEL.SEVEN, SUIT.HEART), new Beer(LABEL.EIGHT, SUIT.HEART),
            new Beer(LABEL.NINE, SUIT.HEART), new Beer(LABEL.TEN, SUIT.HEART), new Beer(LABEL.JACK, SUIT.HEART),

            // CatBalou cards
            new CatBalou(LABEL.KING, SUIT.HEART), new CatBalou(LABEL.NINE, SUIT.DIAMOND), new CatBalou(LABEL.TEN, SUIT.DIAMOND),
            new CatBalou(LABEL.JACK, SUIT.DIAMOND),

            // Duel cards
            new Duel(LABEL.QUEEN, SUIT.DIAMOND), new Duel(LABEL.JACK, SUIT.SPADE), new Duel(LABEL.EIGHT, SUIT.CLUB),

            // Gatling card
            new Gatling(LABEL.TEN, SUIT.HEART),

            // GeneralStore cards
            new Emporio(LABEL.NINE, SUIT.CLUB), new Emporio(LABEL.QUEEN, SUIT.SPADE),

            // Indians cards
            new Indiani(LABEL.ACE, SUIT.DIAMOND), new Indiani(LABEL.KING, SUIT.DIAMOND),

            // Panic cards
            new Panic(LABEL.JACK, SUIT.HEART), new Panic(LABEL.QUEEN, SUIT.HEART), new Panic(LABEL.ACE, SUIT.HEART),
            new Panic(LABEL.EIGHT, SUIT.DIAMOND),

            // Saloon card
            new Saloon(LABEL.FIVE, SUIT.HEART),

            // Stagecoach cards
            new Stagecoach(LABEL.NINE, SUIT.SPADE), new Stagecoach(LABEL.NINE, SUIT.SPADE),

            // WellsFargo card
            new WellsFargo(LABEL.THREE, SUIT.HEART)
    ));
}