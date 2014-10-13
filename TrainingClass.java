import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class TrainingClass {
	
	private	double numberOfFiles;
	private double prior;
	private double sigmaConditionalProb;
	
	public double getSigmaConditionalProb() {
		return sigmaConditionalProb;
	}

	public void setSigmaConditionalProb(double sigmaConditionalProb) {
		this.sigmaConditionalProb = sigmaConditionalProb;
	}

	private	Map<String,Double> wordCount;
	private Map<String,Double> conditionalProb;
	
	public TrainingClass()
	{
		
	}
	
	public TrainingClass(String filepath)
	{
		numberOfFiles=0;
		prior=0;
		wordCount=new HashMap<String,Double>();
		conditionalProb=new HashMap<String,Double>();
		
		File dir=new File(filepath);
		File[] directoryListing=dir.listFiles();
		
		if(directoryListing != null)
		{
			for(File file:directoryListing)
			{
				InputReader in=new InputReader(file.getPath());
				while(!in.isEmpty())
				{
					String tempWord=in.readString();
					Dictionary.dictionaryAdd(tempWord);
					if(!wordCount.containsKey(tempWord))
					{
						wordCount.put(tempWord, 1.0);
					}
					else
					{
						double temp=wordCount.get(tempWord);
						temp++;
						wordCount.put(tempWord, temp);
					}
				}
				
				numberOfFiles++;
			}
		}
		
	}

	public TrainingClass(String filepath, HashSet<String> stopWords)
	{
		numberOfFiles=0;
		prior=0;
		wordCount=new HashMap<String,Double>();
		conditionalProb=new HashMap<String,Double>();
		
		File dir=new File(filepath);
		File[] directoryListing=dir.listFiles();
		
		if(directoryListing != null)
		{
			for(File file:directoryListing)
			{
				InputReader in=new InputReader(file.getPath());
				while(!in.isEmpty())
				{
					String tempWord=in.readString();
					if(!stopWords.contains(tempWord))
					{
						Dictionary.dictionaryAdd(tempWord);
						if(!wordCount.containsKey(tempWord))
						{
							wordCount.put(tempWord, 1.0);
						}
						else
						{
							double temp=wordCount.get(tempWord);
							temp++;
							wordCount.put(tempWord, temp);
						}
					}
				}
				
				numberOfFiles++;
			}
		}
		
	}
	
	public double getPrior() {
		return prior;
	}

	public void setPrior(double prior) {
		this.prior = prior;
	}

	public Map<String, Double> getConditionalProb() {
		return conditionalProb;
	}

	public void setConditionalProb(Map<String, Double> conditionalProb) {
		this.conditionalProb = conditionalProb;
	}

	public void setWordCount(Map<String, Double> wordCount) {
		this.wordCount = wordCount;
	}

	public double getNumberOfFiles() {
		return numberOfFiles;
	}

	public void setNumberOfFiles(double numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}

	public Map<String, Double> getWordCount() {
		return wordCount;
	}

	public void setWordCount(HashMap<String, Double> wordCount) {
		this.wordCount = wordCount;
	}

}
