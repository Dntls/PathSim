import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		
		/////////////////////////////////////////////////////////////////////////////
		/*
		 * caution: 
		 * when trying to run the program, you need to make sure,
		 * 1. the change me! part indicated on this page is set to correct author id
		 * 2. the change me! part indicated in Network.java is set to correct author id
		 * 3. the compareTo method in AuthorNode.java is set correctly corresponding to APVPA, or APTPA
		 * 
		 * I should have integrate them together, but just didn't have sufficient time. sorry about that.
		 * However, the NEWP-PageRank program doesn't have this problem.
		 */
		////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		/////////
		// APVPA
		////////
		// change here for choosing authors and paths
//		Network dblp = new Network(new File("author.txt"), new File("paper.txt"), 
//				new File("venue.txt"), new File("term.txt"), new File("relation.txt"));
//		dblp.connect();
		//////////////////////////////////////////////////////////////////////////////////////////////////
//		dblp.calculatePXY_APVPA(51360); // "68855" stands for "Christos Faloutsos", change me
		//////////////////////////////////////////////////////////////////////////////////////////////////
//		dblp.calculatePXX_APVPA();
//		
//		ArrayList<AuthorNode> similarAuthors = dblp.PathSim_APVPA();
//		for(int i = 0; i < 10; i++){
//			int n = similarAuthors.size();
//			System.out.println(similarAuthors.get(n - i - 1).getData() + '\t' + similarAuthors.get(n-i-1).get_sim_APVPA());
//		}
		
		
		//////////
		// APTPA
		/////////
		Network dblp = new Network(new File("author.txt"), new File("paper.txt"), 
				new File("venue.txt"), new File("term.txt"), new File("relation.txt"));
		dblp.connect();
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		dblp.calculatePXY_APTPA(59090); // "68855" stands for "Christos Faloutsos", change me
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		dblp.calculatePXX_APTPA();
		
		ArrayList<AuthorNode> similarAuthors = dblp.PathSim_APTPA();
		for(int i = 0; i < 10; i++){
			int n = similarAuthors.size();
			System.out.println(similarAuthors.get(n - i - 1).getData() + '\t' + similarAuthors.get(n-i-1).get_sim_APTPA());
			//System.out.println(similarAuthors.get(i).getData() + '\t' + similarAuthors.get(i).get_sim_APTPA());
		}
		
		
		System.out.println("haha");
		
	}
}

