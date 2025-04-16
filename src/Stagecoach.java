public class Stagecoach extends Card{
    public Stagecoach(SUIT suit, LABEL label) {
        super(suit, label, "Stagecoach");
    }
    public void played(Player source, Player target) {
        source.draw(2);
    }
}
