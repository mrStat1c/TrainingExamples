public class MusicInstrumentUtil {

    public static int getStringCount(MusicInstrumentType musicInstrumentType) {
        switch (musicInstrumentType) {
            case CLASSIC_GUITAR:
            case ELECTRO_GUITAR:
                return 6;
            case UKULELE:
            case CELLO:
            case VIOLA:
                return 4;
            default:
                return 0;
        }
    }

    public static boolean isSixStringGuitar(MusicInstrumentType musicInstrumentType) {
        return musicInstrumentType == MusicInstrumentType.CLASSIC_GUITAR
                || musicInstrumentType == MusicInstrumentType.ELECTRO_GUITAR;
    }
}
