import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Network {
	private HashMap<Integer, AuthorNode> authors = new HashMap<Integer, AuthorNode>();
	private HashMap<Integer, PaperNode> papers = new HashMap<Integer, PaperNode>();
	private HashMap<Integer, VenueNode> venues = new HashMap<Integer, VenueNode>();
	private HashMap<Integer, TermNode> terms = new HashMap<Integer, TermNode>();
	private ArrayList<String[]> relations = new ArrayList<String[]>();
	
	public Network(File authorFile, File paperFile, File venueFile, File termFile, File relationFile) throws IOException{
		ArrayList<String[]> authorList = TextParser.splitFile(authorFile);
		ArrayList<String[]> paperList = TextParser.splitFile(paperFile);
		ArrayList<String[]> venueList = TextParser.splitFile(venueFile);
		ArrayList<String[]> termList = TextParser.splitFile(termFile);
		this.relations = TextParser.splitFile(relationFile);
		
		this.authors = TextParser.authorList2Table(authorList);
		this.papers = TextParser.paperList2Table(paperList);
		this.venues = TextParser.venueList2Table(venueList);
		this.terms = TextParser.termList2Table(termList);
	}
	
	// the constructor is just setting up the nodes
	// this method is setting up the edges
	public void connect(){
		for(String[] relation : relations){
			int paperID = Integer.valueOf(relation[0]); // id for a specific paper
			PaperNode paper = papers.get(paperID);
			Integer key = new Integer(relation[1]); // key for the right column node
			if(authors.containsKey(key) && authors.get(key).getType() == 0){ // paperID relates to an author
				AuthorNode author = authors.get(key);
				paper.setNeighbors(author);
				author.setNeighbors(paper);
			}
			else if(venues.containsKey(key) && venues.get(key).getType() == 2){ // paperID relates to a venue
				VenueNode venue = venues.get(key);
				venue.setNeighbors(paper);
				paper.setNeighbors(venue);
			}
			else if(terms.containsKey(key) && terms.get(key).getType() == 3){ // paperID relates to a term
				TermNode term = terms.get(key);
				term.setNeighbors(paper);
				paper.setNeighbors(term);
			}
		}
	}
	
	// The following parts are used to calculate Sim
	// APVPA
	// number of path from author x to author y shown on the dividend
	
	public void calculatePXY_APVPA(Integer xid){
		AuthorNode x = authors.get(xid);
		ArrayList<SimNode> xneighbors = x.getNeighbors();
		for(SimNode aneighbor : xneighbors){ // traversing the neighbors of author x
			if(aneighbor instanceof PaperNode){ // aneighbor is a paper
				ArrayList<SimNode> pneighbors = aneighbor.getNeighbors();
				for(SimNode pneighbor : pneighbors){
					if(pneighbor instanceof VenueNode){ //pneighbor is a venue
						ArrayList<SimNode> vneighbors = pneighbor.getNeighbors();
						for(SimNode vneighbor : vneighbors){
							if(vneighbor instanceof PaperNode){ //veneighbor is a paper
								ArrayList<SimNode> prneighbors = vneighbor.getNeighbors();
								for(SimNode prneighbor : prneighbors){
									if(prneighbor instanceof AuthorNode){ // prneighbor is an author
										((AuthorNode) prneighbor).incrementOPath();
									}
								} // end PR
							}
						} // end V
					}
				} // end PL
			}
		} // end AL
	}
	
	public void calculatePXX_APVPA(Integer xid){
		AuthorNode x = authors.get(xid);
		ArrayList<SimNode> xneighbors = x.getNeighbors();
		for(SimNode aneighbor : xneighbors){ // traversing the neighbors of author x
			if(aneighbor instanceof PaperNode){ // aneighbor is a paper
				ArrayList<SimNode> pneighbors = aneighbor.getNeighbors();
				for(SimNode pneighbor : pneighbors){
					if(pneighbor instanceof VenueNode){ //pneighbor is a venue
						ArrayList<SimNode> vneighbors = pneighbor.getNeighbors();
						for(SimNode vneighbor : vneighbors){
							if(vneighbor instanceof PaperNode){ //veneighbor is a paper
								ArrayList<SimNode> prneighbors = vneighbor.getNeighbors();
								for(SimNode prneighbor : prneighbors){
									if(prneighbor.equals(x)){ // prneighbor is an author
										x.incrementSPath();
									}
								} // end PR
							}
						} // end V
					}
				} // end PL
			}
		} // end AL
	}
	
	
	public void calculatePXX_APVPA(){
		for(AuthorNode author : authors.values()){
			calculatePXX_APVPA(author.getId());
		}
	}
	
	// return the sorted authors as an arraylist
	public ArrayList<AuthorNode> PathSim_APVPA(){
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		AuthorNode x = authors.get(51360); // x is "Christos Faloutsos", change me !!!!!!!!!!!!!!!!!!!
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		int pxx = x.getSPath();
		ArrayList<AuthorNode> ret = new ArrayList<AuthorNode>(); 
		for(AuthorNode author : authors.values()){
			int dividend = 2 * author.getOPath();
			int divisor = pxx + author.getSPath();
			double sim = divisor == 0 ? 0 : ((double)dividend / divisor);
			author.set_sim_APVPA(sim);
			ret.add(author);
		}
		Collections.sort(ret);
		return ret;
	}
	
	public void calculatePXY_APTPA(Integer xid){
		AuthorNode x = authors.get(xid);
		ArrayList<SimNode> xneighbors = x.getNeighbors();
		for(SimNode aneighbor : xneighbors){ // traversing the neighbors of author x
			if(aneighbor instanceof PaperNode){ // aneighbor is a paper
				ArrayList<SimNode> pneighbors = aneighbor.getNeighbors();
				for(SimNode pneighbor : pneighbors){
					if(pneighbor instanceof TermNode){ //pneighbor is a venue
						ArrayList<SimNode> vneighbors = pneighbor.getNeighbors();
						for(SimNode vneighbor : vneighbors){
							if(vneighbor instanceof PaperNode){ //veneighbor is a paper
								ArrayList<SimNode> prneighbors = vneighbor.getNeighbors();
								for(SimNode prneighbor : prneighbors){
									if(prneighbor instanceof AuthorNode){ // prneighbor is an author
										((AuthorNode) prneighbor).incrementOPath();
									}
								} // end PR
							}
						} // end T
					}
				} // end PL
			}
		} // end AL
	}
	
	public void calculatePXX_APTPA(Integer xid){
		AuthorNode x = authors.get(xid);
		ArrayList<SimNode> xneighbors = x.getNeighbors();
		for(SimNode aneighbor : xneighbors){ // traversing the neighbors of author x
			if(aneighbor instanceof PaperNode){ // aneighbor is a paper
				ArrayList<SimNode> pneighbors = aneighbor.getNeighbors();
				for(SimNode pneighbor : pneighbors){
					if(pneighbor instanceof TermNode){ //pneighbor is a venue
						ArrayList<SimNode> vneighbors = pneighbor.getNeighbors();
						for(SimNode vneighbor : vneighbors){
							if(vneighbor instanceof PaperNode){ //veneighbor is a paper
								ArrayList<SimNode> prneighbors = vneighbor.getNeighbors();
								for(SimNode prneighbor : prneighbors){
									if(prneighbor.equals(x)){ // prneighbor is an author
										x.incrementSPath();
									}
								} // end PR
							}
						} // end V
					}
				} // end PL
			}
		} // end AL
	}
	
	
	public void calculatePXX_APTPA(){
		for(AuthorNode author : authors.values()){
			calculatePXX_APTPA(author.getId());
		}
	}
	
	// return the sorted authors as an arraylist
	public ArrayList<AuthorNode> PathSim_APTPA(){
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		AuthorNode x = authors.get(59090); // x is "Christos Faloutsos", change me !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		int pxx = x.getSPath();
		ArrayList<AuthorNode> ret = new ArrayList<AuthorNode>(); 
		for(AuthorNode author : authors.values()){
			int dividend = 2 * author.getOPath();
			int divisor = pxx + author.getSPath();
			double sim = divisor == 0 ? 0 : ((double)dividend / divisor);
			author.set_sim_APTPA(sim);
			ret.add(author);
		}
		Collections.sort(ret);
		return ret;
	}
	
}





