package Cards;

import Cards.DrawCards.*;
import Cards.Equipments.GunCards.*;
import Cards.Equipments.*;
import Cards.HealCards.*;
import Cards.OtherCards.Missed;
import Cards.TargetCards.Bang;

import java.util.ArrayList;
import java.util.Arrays;


public class CardDatabase {
    public static final ArrayList<Card> cards = new ArrayList<>(Arrays.asList(
            new Bang(LABEL.TWO, SUIT.HEART),
            new Bang(LABEL.THREE, SUIT.SPADE),
            new Bang(LABEL.FOUR, SUIT.DIAMOND),
            new Bang(LABEL.FIVE, SUIT.HEART),

            new Missed(LABEL.SIX, SUIT.SPADE),
            new Missed(LABEL.SEVEN, SUIT.DIAMOND),
            new Beer(LABEL.SIX, SUIT.HEART),
            new Saloon(LABEL.KING, SUIT.SPADE),

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
            new Mustang(LABEL.FOUR, SUIT.HEART),
            new Remington(LABEL.FIVE, SUIT.SPADE),
            new Schofield(LABEL.SEVEN, SUIT.DIAMOND),
            new RevCarabine(LABEL.EIGHT, SUIT.HEART),
            new Winchester(LABEL.NINE, SUIT.SPADE),
            new Volcanic(LABEL.QUEEN, SUIT.DIAMOND)

    ));
}
