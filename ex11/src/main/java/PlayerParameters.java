public class PlayerParameters {

    public static class PrepareTime {

        private static final int MIN = 1;
        private static final int MAX = 5;

        public static int random(){
            return PlayerParameters.random(MIN, MAX);
        }
    }

    public static class Damage {

        private static final int MIN = 25;
        private static final int MAX = 75;

        public static int random(){
            return PlayerParameters.random(MIN, MAX);
        }
    }

    private static int random(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
