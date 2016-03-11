import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TextParser {
	
	// extract data from original file and put to arrayList
	static public ArrayList<String[]> splitFile(File file) throws IOException{
		ArrayList<String[]> list = new ArrayList<String[]>();
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String contentLine = br.readLine();
		while(contentLine != null){
			String[] splited = contentLine.split("\\t");
			list.add(splited);
			contentLine = br.readLine();
		}
		br.close();
		return list;
	}
	
	static public HashMap<Integer, AuthorNode>  authorList2Table(ArrayList<String[]> list){
		HashMap<Integer, AuthorNode> ret = new HashMap<Integer, AuthorNode>();
		for(String[] ele : list){
			ret.put(new Integer(ele[0]), new AuthorNode(ele[0], ele[1]));
		}
		return ret;
	}
	
	static public HashMap<Integer, PaperNode>  paperList2Table(ArrayList<String[]> list){
		HashMap<Integer, PaperNode> ret = new HashMap<Integer, PaperNode>();
		for(String[] ele : list){
			ret.put(new Integer(ele[0]), new PaperNode(ele[0], ele[1]));
		}
		return ret;
	}
	
	static public HashMap<Integer, VenueNode>  venueList2Table(ArrayList<String[]> list){
		HashMap<Integer, VenueNode> ret = new HashMap<Integer, VenueNode>();
		for(String[] ele : list){
			ret.put(new Integer(ele[0]), new VenueNode(ele[0], ele[1]));
		}
		return ret;
	}
	
	static public HashMap<Integer, TermNode>  termList2Table(ArrayList<String[]> list){
		HashMap<Integer, TermNode> ret = new HashMap<Integer, TermNode>();
		for(String[] ele : list){
			ret.put(new Integer(ele[0]), new TermNode(ele[0], ele[1]));
		}
		return ret;
	}
}
