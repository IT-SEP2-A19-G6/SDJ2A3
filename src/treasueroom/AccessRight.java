package treasueroom;

public interface AccessRight {
    void acquireRead(String name);
    void releaseRead(String name);
    void acquireWrite(String name);
    void releaseWrite(String name);
}
