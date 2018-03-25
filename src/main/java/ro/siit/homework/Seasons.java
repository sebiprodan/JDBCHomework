package ro.siit.homework;

public enum Seasons {
    Spring,
    Summer,
    Autumn,
    Winter;

    public static Seasons getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
