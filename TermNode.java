
public class TermNode extends SimNode{
	private int type;
	
	public TermNode(String id, String data){
		super(id, data);
		this.type = 3;
	}
	
	public int getType(){return this.type;}
}
