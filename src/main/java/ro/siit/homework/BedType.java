package ro.siit.homework;

public enum BedType {
    QueenSize{
        @Override
        public String toString() {
            return "Queen Size";
        }
    },
    KingSize{
        @Override
        public String toString() {
            return "King Size";
        }
    },
    StandardOnePerson{
        @Override
        public String toString() {
            return "Standard Size";
        }
    };

    public static BedType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
