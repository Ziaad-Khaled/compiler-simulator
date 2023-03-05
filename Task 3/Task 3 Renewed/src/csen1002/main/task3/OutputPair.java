package csen1002.main.task3;

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
