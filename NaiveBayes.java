import java.io.File;
import java.util.HashSet;


public class NaiveBayes {

	TrainingClass[] classes;
	double totalNumberOfDocs;
	
	public NaiveBayes(String[] arrayOfFilepaths)
	{	
		classes=new TrainingClass[arrayOfFilepaths.length];
		totalNumberOfDocs=0;
		
		for(int i=0;i<arrayOfFilepaths.length;i++)
		{
			classes[i]=new TrainingClass(arrayOfFilepaths[i]);
			totalNumberOfDocs+=classes[i].getNumberOfFiles();
		}
		
		
		for(int i=0;i<classes.length;i++)
		{
			classes[i].setPrior(classes[i].getNumberOfFiles()/totalNumberOfDocs);
			for(int j=0;j<Dictionary.getDictionary().size();j++)
			{
				double temp=1;
				if(classes[i].getWordCount().containsKey(Dictionary.getDictionary().toArray()[i].toString()))
				{
					temp+=classes[i].getWordCount().get(Dictionary.getDictionary().toArray()[i].toString());
				}
				
				classes[i].setSigmaConditionalProb(classes[i].getSigmaConditionalProb()+temp);
			}
		}
		
		
		
		for(int i=0;i<classes.length;i++)
		{
			for(int j=0;j<Dictionary.getDictionary().size();j++)
			{
				if(classes[i].getWordCount().containsKey(Dictionary.getDictionary().toArray()[j].toString()))
				{
					double numerator=classes[i].getWordCount().get(Dictionary.getDictionary().toArray()[j])+1.0;
					double denomanator=classes[i].getSigmaConditionalProb();
					
					classes[i].getConditionalProb().put(Dictionary.getDictionary().toArray()[j].toString(), (numerator/denomanator));
				}
				else
				{
					double numerator=1.0;
					double denomanator=classes[i].getSigmaConditionalProb();
					
					classes[i].getConditionalProb().put(Dictionary.getDictionary().toArray()[j].toString(), (numerator/denomanator));
				}
			}
		}
		
		
	}

	public NaiveBayes(String[] arrayOfFilepaths, HashSet<String> stopWords)
	{	
		classes=new TrainingClass[arrayOfFilepaths.length];
		totalNumberOfDocs=0;
		
		for(int i=0;i<arrayOfFilepaths.length;i++)
		{
			classes[i]=new TrainingClass(arrayOfFilepaths[i],stopWords);
			totalNumberOfDocs+=classes[i].getNumberOfFiles();
		}
		
		
		for(int i=0;i<classes.length;i++)
		{
			classes[i].setPrior(classes[i].getNumberOfFiles()/totalNumberOfDocs);
			for(int j=0;j<Dictionary.getDictionary().size();j++)
			{
				double temp=1;
				if(classes[i].getWordCount().containsKey(Dictionary.getDictionary().toArray()[i].toString()))
				{
					temp+=classes[i].getWordCount().get(Dictionary.getDictionary().toArray()[i].toString());
				}
				
				classes[i].setSigmaConditionalProb(classes[i].getSigmaConditionalProb()+temp);
			}
		}
		
		
		
		for(int i=0;i<classes.length;i++)
		{
			for(int j=0;j<Dictionary.getDictionary().size();j++)
			{
				if(classes[i].getWordCount().containsKey(Dictionary.getDictionary().toArray()[j].toString()))
				{
					double numerator=classes[i].getWordCount().get(Dictionary.getDictionary().toArray()[j])+1.0;
					double denomanator=classes[i].getSigmaConditionalProb();
					
					classes[i].getConditionalProb().put(Dictionary.getDictionary().toArray()[j].toString(), (numerator/denomanator));
				}
				else
				{
					double numerator=1.0;
					double denomanator=classes[i].getSigmaConditionalProb();
					
					classes[i].getConditionalProb().put(Dictionary.getDictionary().toArray()[j].toString(), (numerator/denomanator));
				}
			}
		}
		
		
	}

	public double applyMultinomialNaiveBayes(String[] filePaths)
	{
		double accuracy=0;
		double correctDoc=0;
		double  numberOfDocs=0;
		
		for(int i=0;i<filePaths.length;i++)
		{
			File dir=new File(filePaths[i]);
			File[] directoryListing=dir.listFiles();
			numberOfDocs+=directoryListing.length;
			
			if(directoryListing != null)
			{
				for(File file:directoryListing)
				{
					double[] score=new double[this.classes.length];	
					int maxClassIndex=Integer.MIN_VALUE;
					double max=-1000000000;
					
					InputReader in = new InputReader(file.getPath());
					
					for(int k=0;k<classes.length;k++)
					{
						score[k]=Math.log(this.classes[k].getPrior());
						
					}
														
					while(!in.isEmpty())
					{
						String temp=in.readString();
						if(Dictionary.getDictionary().contains(temp))
						{
							for(int j=0;j<this.classes.length;j++)
							{
								score[j]+=Math.log(this.classes[j].getConditionalProb().get(temp));
								
							}
						}
					}
					
					for(int l=0;l<score.length;l++)
					{
						if(score[l]>max)
						{
							max=score[l];
							maxClassIndex=l;
						}
					}
					
					if(i==maxClassIndex)
					{
						correctDoc++;
					}
				}
			}
		}
		
		
		accuracy=(correctDoc/numberOfDocs)*100;
		return accuracy;
	}
	
	
	public TrainingClass[] getClasses() {
		return classes;
	}

	public void setClasses(TrainingClass[] classes) {
		this.classes = classes;
	}

	public double getTotalNumberOfDocs() {
		return totalNumberOfDocs;
	}

	public void setTotalNumberOfDocs(double totalNumberOfDocs) {
		this.totalNumberOfDocs = totalNumberOfDocs;
	}
}
