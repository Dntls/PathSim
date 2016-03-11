
public class VenueNode extends SimNode{
	private int type;
	
	public VenueNode(String id, String data){
		super(id, data);
		this.type = 2;
	}
	
	public int getType(){return this.type;}

}
