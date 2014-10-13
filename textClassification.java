import java.util.HashSet;


public class textClassification {

	public static void main(String[] args)
	{
	
		System.out.println("Name: Shravan Chinta --------- id - sxc129530");
		String[] training=new String[2];
		training[0]=args[0];
		training[1]=args[1];
	
		
		String[] filePaths=new String[2];
		filePaths[0]=args[2];
		filePaths[1]=args[3];
		
		String stopWord=args[4];
		
		NaiveBayes object=new NaiveBayes(training);
		
		System.out.println("Accuracy for Naive Bayes without stopwords");
		
		
		System.out.println(object.applyMultinomialNaiveBayes(filePaths));
		
		System.out.println("");
		System.out.println("");
				
		HashSet<String> stopWords=new HashSet<String>();
		
		InputReader in=new InputReader(stopWord);
		
		while(!in.isEmpty())
		{
			stopWords.add(in.readString());
		}
		
		Dictionary.getDictionary().clear();
		
		NaiveBayes object1=new NaiveBayes(training,stopWords);
		System.out.println("Accuracy for Naive Bayes with stopwords");
		System.out.println(object1.applyMultinomialNaiveBayes(filePaths));

		System.out.println("");
		System.out.println("");
		Dictionary.getDictionary().clear();
		
		LogisticRegression logObject=new LogisticRegression(training);
		
		for(int i=0;i<1;i++)
		{
			logObject.newWeights(logObject);
		}
		System.out.println("Accuracy for Logistic Regression without stopwords");
		System.out.println(logObject.logisticRegressionAccuracy(filePaths));
		
		Dictionary.getDictionary().clear();
		
		LogisticRegression logObject2=new LogisticRegression(training,stopWords);
		
		for(int i=0;i<1000;i++)
		{
			logObject2.newWeights(logObject);
		}
		System.out.println("Accuracy for Logistic Regression with stopwords");
		System.out.println(logObject2.logisticRegressionAccuracy(filePaths));
		
	}
	
	
}
