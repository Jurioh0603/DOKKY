package lunch.service;

public class DeleteRequest {
    
    private int lunchNumber;
    private int imageNumber;
    
    public int getLunchNumber() {
    	return lunchNumber;
    }
    
    public void setLunchNumber(int lunchNumber) {
    	this.lunchNumber = lunchNumber;
    }
    
    public int getImageNumber() {
    	return imageNumber;
    }
    
    public void setImageNumber(int imageNumber) {
    	this.imageNumber = imageNumber;
    }
}