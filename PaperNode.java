
public class PaperNode extends SimNode{
	private int type;
	
	public PaperNode(String id, String data){
		super(id, data);
		this.type = 1;
	}
	
	public int getType(){return this.type;}
}
