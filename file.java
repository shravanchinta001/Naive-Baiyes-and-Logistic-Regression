import java.util.HashMap;


public class file {
	
	private HashMap<String,Double> wordCount;
	private double fileType;
	
	public file()
	{
		fileType=0;
		wordCount=new HashMap<String,Double>();
	}
	public double getFileType() {
		return fileType;
	}

	public void setFileType(double fileType) {
		this.fileType = fileType;
	}

	public HashMap<String, Double> getWordCount() {
		return wordCount;
	}

	public void setWordCount(HashMap<String, Double> wordCount) {
		this.wordCount = wordCount;
	}
	
}
