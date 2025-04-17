package Cards;

import Cards.DrawCards.*;
import Cards.Equipments.GunCards.*;
import Cards.Equipments.*;
import Cards.HealCards.*;

import java.util.ArrayList;
import java.util.Arrays;


public class CardDatabase {
    public static final ArrayList<Card> cards = new ArrayList<>(Arrays.asList(
            new Jail(LABEL.TWO, SUIT.DIAMOND),
            new Stagecoach(LABEL.ACE, SUIT.SPADE),
            new WellsFargo(LABEL.JACK, SUIT.DIAMOND),
            new Barrel(LABEL.ACE, SUIT.HEART),
            new Dynamite(LABEL.TEN, SUIT.SPADE),

            new Mustang(LABEL.FOUR, SUIT.HEART),
            new Remington(LABEL.FIVE, SUIT.SPADE),
            new Schofield(LABEL.SEVEN, SUIT.DIAMOND),
            new RevCarabine(LABEL.EIGHT, SUIT.HEART),
            new Winchester(LABEL.NINE, SUIT.SPADE),
            new Volcanic(LABEL.QUEEN, SUIT.DIAMOND),
            new Beer(LABEL.SIX, SUIT.HEART),
            new Saloon(LABEL.KING, SUIT.SPADE)
    ));
}
