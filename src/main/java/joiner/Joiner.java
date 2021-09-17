package joiner;

import java.io.FileWriter;

public interface Joiner<T> {

    boolean join(T fromA, T fromB, FileWriter fw);
}
