package lambdasinaction.chap9.third.party;


import java.util.Arrays;
import java.util.List;
import lambdasinaction.chap9.api1.Resizable;
import lambdasinaction.chap9.api1.Square;
import lambdasinaction.chap9.api1.Triangle;

public class Game {

    public static void main(String...args){
        List<Resizable> resizableShapes = Arrays.asList(new Square(), new Triangle(), new Ellipse());
        Utils.paint(resizableShapes);
    }
}

