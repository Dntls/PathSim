
public class AuthorNode extends SimNode  implements Comparable<AuthorNode>{
	private int type;
	private int path2other;
	private int path2self;
	private double sim_APVPA;
	private double sim_APTPA;
	
	public AuthorNode(String id, String data){
		super(id, data);
		this.type = 0;
		this.path2other = 0;
		this.path2self = 0;
	}
	
	public int getType(){return this.type;}
	public int getOPath(){return this.path2other;}
	public int getSPath(){return this.path2self;}
	public double get_sim_APVPA(){return this.sim_APVPA;}
	public double get_sim_APTPA(){return this.sim_APTPA;}
	
	public void incrementOPath(){this.path2other++;}
	public void incrementSPath(){this.path2self++;}
	
	public void clearOPath(){this.path2other = 0;}
	
	public void set_sim_APVPA(double sim){
		this.sim_APVPA = sim;
	}
	public void set_sim_APTPA(double sim){
		this.sim_APTPA = sim;
	}

	@Override
	public int compareTo(AuthorNode o) {
		return this.sim_APTPA - o.sim_APTPA > 0 ? 1 : -1; // change me !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//return this.sim_APVPA - o.sim_APVPA > 0 ? 1 : -1;
	}
}
