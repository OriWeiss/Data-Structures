package Lab12;

public class CarID implements Comparable<CarID>{
    private String characterSequence;
    private long numericSequence;
    public final static int CHARACTER_SEQUENCE_LENGTH = 3;
    public final static int NUMERIC_SEQUENCE_LENGTH = 14;
    private final String DEFAULT_CHARACTER_SEQUENCE = "???";
    private final long DEFAULT_NUMERIC_SEQUENCE = 10000000000000L;


    public CarID(String charSeq, Long numSeq){
        setCharacterSequence(charSeq);
        setNumericSequence(numSeq);
    }
    public void setCharacterSequence(String seq){
        if(seq.length() == CHARACTER_SEQUENCE_LENGTH){
            this.characterSequence = seq;
        }
        else{
            this.characterSequence = DEFAULT_CHARACTER_SEQUENCE;
        }
    }
    public void setNumericSequence(Long seq){

        if((int)Math.log10((seq)) == NUMERIC_SEQUENCE_LENGTH){
            this.numericSequence = seq;
        }
        else{
            this.numericSequence = this.DEFAULT_NUMERIC_SEQUENCE;
        }
    }
    public String getCharacterSequence(){
        return this.characterSequence;
    }
    public long getNumericSequence(){
        return this.numericSequence;
    }
    @Override
    public boolean equals(Object o){
        boolean same = true;
        if(o == this){
            same = true;
        }
        else if(o == null || o.getClass()!= this.getClass()){
            same = false;
        }
        else{
            CarID other = (CarID) o;
            boolean result = Long.compare(this.numericSequence , other.getNumericSequence()) == 0;
            same = this.characterSequence.equals(other.getCharacterSequence()) && result;
        }
        return same;
    }
    @Override
    public int compareTo(CarID id){
        return Long.compare(this.numericSequence, id.getNumericSequence());
    }
    @Override
    public int hashCode(){
        int G = 31;
        int folded = (int)(this.numericSequence ^ (this.numericSequence >> 32));
        return G * (this.characterSequence.hashCode()) + folded;

    }
    @Override
    public String toString(){
        return this.characterSequence + "  " + this.numericSequence;
    }
}
