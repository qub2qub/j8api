package lambdasinaction.chap9.api2;

public interface Rotatable {
    
    void setRotationAngle(int angleInDegrees);
    
    int getRotationAngle();
    
    default void rotateBy(int angleInDegrees) {
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
    }
}