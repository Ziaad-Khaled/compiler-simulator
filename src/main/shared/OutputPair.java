package main.shared;

public class OutputPair {
    int state;
    String output;

    public OutputPair(int state, String output)
    {
        this.state = state;
        this.output = output;
    }

    @Override
    public String toString() {
        return output + "," + state;
    }
}
