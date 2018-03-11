package lambdasinaction.chap9.third.party;

import lambdasinaction.chap9.api1.Resizable;

public class Ellipse implements Resizable {
    
    @Override
    public int getWidth() {
        return 0;
    }
    
    @Override
    public int getHeight() {
        return 0;
    }
    
    @Override
    public void setWidth(int width) {
    
    }
    
    @Override
    public void setHeight(int height) {
    
    }
    
    @Override
    public void setAbsoluteSize(int width, int height) {
    
    }
    
    // IMPOSSIBLE TO ADD THIS TO 3RD PARTY BINARIES
//    @Override
//    public void setRelativeSize(int widthFactor, int heightFactor) { }
    
    @Override
    public void draw() {
    
    }
}
