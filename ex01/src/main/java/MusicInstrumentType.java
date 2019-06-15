import lombok.Getter;

public enum MusicInstrumentType {

    CLASSIC_GUITAR("An acoustic wooden string instrument with strings made of gut or nylon") {
        public boolean isElectroInstrument() {
            return false;
        }
    },
    ELECTRO_GUITAR("Guitar that uses one or more pickups to convert the vibration of its strings into electrical signals") {
        public boolean isElectroInstrument() {
            return true;
        }
    },
    UKULELE("Four-stringed musical instrument made from wood that resembles a small classical guitar") {
        public boolean isElectroInstrument() {
            return false;
        }
    },
    SYNTHESIZER("Electronic musical instrument that generates audio signals that may be converted to sound") {
        public boolean isElectroInstrument() {
            return true;
        }
    },
    PIANO("An acoustic, stringed musical instrument in which the strings are struck by hammers") {
        public boolean isElectroInstrument() {
            return false;
        }
    },
    FLUTE("An aerophone or reedless wind instrument that produces its sound from the flow of air across an opening") {
        public boolean isElectroInstrument() {
            return false;
        }
    },
    VIOLA("a string instrument that is bowed or played with varying techniques") {
        public boolean isElectroInstrument() {
            return false;
        }
    },
    CELLO("Bowed (and occasionally plucked) string instrument of the violin family") {
        public boolean isElectroInstrument() {
            return false;
        }
    };

    @Getter
    private String description;


    MusicInstrumentType(String description) {
        this.description = description;
    }

    //по дефолту toString() возвращает name()
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public abstract boolean isElectroInstrument();
}
