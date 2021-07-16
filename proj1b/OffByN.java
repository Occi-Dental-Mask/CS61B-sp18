public class OffByN implements CharacterComparator{
    public int offset;
    public OffByN(int N) {
        offset = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (x - y == offset || y - x == offset) {
            return true;
        }
        return false;
    }
}
