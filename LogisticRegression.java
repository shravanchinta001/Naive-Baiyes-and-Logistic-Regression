import java.io.File;
import java.util.HashMap;
import java.util.HashSet;


public class LogisticRegression {

	file[] files;
	HashMap<String,Double> oldWeights=new HashMap<String,Double>();
	HashMap<String,Double> newWeights=new HashMap<String,Double>();
	
	
	public LogisticRegression(String[] arrayOfFilepaths){
	
		for(int i=0;i<arrayOfFilepaths.length;i++){
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			
			if(files==null)
			{
				files=new file[directoryListing.length];
			}
			else
			{
				file[] temp=new file[files.length];
				temp=files;
				files=new file[temp.length+directoryListing.length];
				for(int j=0;j<temp.length;j++)
				{
					files[j]=temp[j];
				}
			}
		}
		
		int count=0;
		for(int i=0;i<arrayOfFilepaths.length;i++)
		{
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			if(directoryListing != null)
			{
				for(File file:directoryListing)
				{
					files[count]=new file();
					files[count].setFileType(i);
					
					InputReader in=new InputReader(file.getPath());
					
					while(!in.isEmpty())
					{
						String temps=in.readString();
						Dictionary.getDictionary().add(temps);
						oldWeights.put(temps, 1.0);
						newWeights.put(temps, 1.0);
						
						if(files[count].getWordCount().containsKey(temps))
						{
							double temp=files[count].getWordCount().get(temps);
							temp++;
							files[count].getWordCount().put(temps, temp);
						}
						else
						{
							files[count].getWordCount().put(temps, 1.0);
						}
					}
					count++;
				}
			
			}
		}
		
	}
	

	public LogisticRegression(String[] arrayOfFilepaths,HashSet<String> stopWords){
		
		for(int i=0;i<arrayOfFilepaths.length;i++){
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			
			if(files==null)
			{
				files=new file[directoryListing.length];
			}
			else
			{
				file[] temp=new file[files.length];
				temp=files;
				files=new file[temp.length+directoryListing.length];
				for(int j=0;j<temp.length;j++)
				{
					files[j]=temp[j];
				}
			}
		}
		
		int count=0;
		for(int i=0;i<arrayOfFilepaths.length;i++)
		{
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			if(directoryListing != null)
			{
				for(File file:directoryListing)
				{
					files[count]=new file();
					files[count].setFileType(i);
					
					InputReader in=new InputReader(file.getPath());
					
					while(!in.isEmpty())
					{
						String temps=in.readString();
						if(!stopWords.contains(temps))
						{
							Dictionary.getDictionary().add(temps);
							oldWeights.put(temps, 1.0);
							newWeights.put(temps, 1.0);
						
							if(files[count].getWordCount().containsKey(temps))
							{
								double temp=files[count].getWordCount().get(temps);
								temp++;
								files[count].getWordCount().put(temps, temp);
							}
							else
							{
								files[count].getWordCount().put(temps, 1.0);
							}
						}
					}
					count++;
				}
			
			}
		}
		
	}
	
	public LogisticRegression(String[] arrayOfFilepaths,int test){
		
		for(int i=0;i<arrayOfFilepaths.length;i++){
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			
			if(files==null)
			{
				files=new file[directoryListing.length];
			}
			else
			{
				file[] temp=new file[files.length];
				temp=files;
				files=new file[temp.length+directoryListing.length];
				for(int j=0;j<temp.length;j++)
				{
					files[j]=temp[j];
				}
			}
		}
		
		int count=0;
		for(int i=0;i<arrayOfFilepaths.length;i++)
		{
			File dir=new File(arrayOfFilepaths[i]);
			File[] directoryListing=dir.listFiles();
			if(directoryListing != null)
			{
				for(File file:directoryListing)
				{
					files[count]=new file();
					files[count].setFileType(i);
					
					InputReader in=new InputReader(file.getPath());
					
					while(!in.isEmpty())
					{
						String temps=in.readString();
						oldWeights.put(temps, 1.0);
						newWeights.put(temps, 1.0);
						if(files[count].getWordCount().containsKey(temps))
						{
							double temp=files[count].getWordCount().get(temps);
							temp++;
							files[count].getWordCount().put(temps, temp);
						}
						else
						{
							files[count].getWordCount().put(temps, 1.0);
						}
					}
				}
				count++;
			}
		}
		
	}
	
	public double conditionalProb(LogisticRegression logObject,file fileObject)
	{
		double returnValue=0;
		double temp=0;
		
		for(int i=0;i<fileObject.getWordCount().size();i++)
		{
			
				temp+=fileObject.getWordCount().get(fileObject.getWordCount().keySet().toArray()[i].toString())*logObject.getOldWeights().get(fileObject.getWordCount().keySet().toArray()[i].toString());
			
		}
		
		if(fileObject.getFileType()==1)
		{
			returnValue=1/(1+Math.pow(Math.E, temp));
		}
		else
		{
			returnValue=Math.pow(Math.E, temp)/(1+Math.pow(Math.E, temp));
		}
		
		return returnValue;
	}
	
	
	public void newWeights(LogisticRegression logObject)
	{
		for(int i=0;i<logObject.getOldWeights().size();i++)
		{
			double temp=0;
			for(int j=0;j<logObject.getFiles().length;j++)
			{
				if(logObject.getFiles()[j].getWordCount().containsKey(logObject.getOldWeights().keySet().toArray()[i].toString()))
				{
					temp+=logObject.getFiles()[j].getWordCount().get(logObject.getOldWeights().keySet().toArray()[i].toString())*(logObject.getFiles()[j].getFileType()-logObject.conditionalProb(logObject, logObject.getFiles()[j]));
				}
			}
			
			temp=temp*0.1;
			temp=temp-(0.1)*(0.01)*(logObject.getOldWeights().get(logObject.getOldWeights().keySet().toArray()[i].toString()));
			temp=temp+logObject.getOldWeights().get(logObject.getOldWeights().keySet().toArray()[i].toString());
			logObject.getNewWeights().put(logObject.getOldWeights().keySet().toArray()[i].toString(), temp);
		}
	}
	
	
	public double logisticRegressionAccuracy(String[] arrayOfFilepaths)
	{
		double accuracy=0;
		double count=0;
		
		LogisticRegression testObject=new LogisticRegression(arrayOfFilepaths,0);
		
		for(int i=0;i<testObject.getFiles().length;i++)
		{
			double score=0;
			
			for(int j=0;j<testObject.getFiles()[i].getWordCount().keySet().toArray().length;j++)
			{
				if(this.getNewWeights().containsKey(testObject.getFiles()[i].getWordCount().keySet().toArray()[j].toString()))
				{
					score+=this.getNewWeights().get(testObject.getFiles()[i].getWordCount().keySet().toArray()[j].toString())*testObject.getFiles()[i].getWordCount().get(testObject.getFiles()[i].getWordCount().keySet().toArray()[j].toString());
				}
			}
			if(score>0.5 && testObject.getFiles()[i].getFileType()==1)
			{
				count++;
			}
			else if(score<0.5 && testObject.getFiles()[i].getFileType()==0)
			{
				count++;
			}
				
		}
		accuracy=(count/testObject.getFiles().length)*100;
		return accuracy;
	}
	
	
	public file[] getFiles() {
		return files;
	}

	public void setFiles(file[] files) {
		this.files = files;
	}


	public HashMap<String, Double> getOldWeights() {
		return oldWeights;
	}


	public void setOldWeights(HashMap<String, Double> oldWeights) {
		this.oldWeights = oldWeights;
	}


	public HashMap<String, Double> getNewWeights() {
		return newWeights;
	}


	public void setNewWeights(HashMap<String, Double> newWeights) {
		this.newWeights = newWeights;
	}

	
	
}
