package ro.siit.homework;

public enum RoomType {
    StandardSingleRoom {
        @Override
        public String toString() {
            return "Standard Single-Room";
        }
    },
    StadardDoubleRoom {
        @Override
        public String toString() {
            return "Standard Double-Room";
        }
    },
    PremiumDubbleRoom {
        @Override
        public String toString() {
            return "Premium Double-Room";
        }
    },
    KingSuite{
        @Override
        public String toString() {
            return "King Suite";
        }
    },
    QueenSuite{
        @Override
        public String toString() {
            return "Queen Suite";
        }
    },
    FamilySuite{
        @Override
        public String toString() {
            return "Family Suite";
        }
    };

    public static RoomType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
