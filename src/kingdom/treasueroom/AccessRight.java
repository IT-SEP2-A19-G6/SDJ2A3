package kingdom.treasueroom;

public interface AccessRight {
    void acquireRead(Object name);
    void releaseRead(Object name);
    void acquireWrite(Object name);
    void releaseWrite(Object name);
}
