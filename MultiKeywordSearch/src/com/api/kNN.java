/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

/**
 *
 * @author SAJAN
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class kNN{
	
/*	public static void knn(String trainingFile, String testFile, int K, int metricType){
		//get the current time
		final long startTime = System.currentTimeMillis();
		
		// make sure the input arguments are legal
		if(K <= 0){
			System.out.println("K should be larger than 0!");
			return;
		}
		
		// metricType should be within [0,2];
		if(metricType > 2 || metricType <0){
			System.out.println("metricType is not within the range [0,2]. Please try again later");
			return;
		}
		
		//TrainingFile and testFile should be the same group
		String trainGroup = extractGroupName(trainingFile);
		String testGroup = extractGroupName(testFile);
		
		if(!trainGroup.equals(testGroup)){
			System.out.println("trainingFile and testFile are illegal!");
			return;
		}
		
		
//              TrainRecord[] trainingSet =  FileManager.readTrainFile(trainingFile);
 //               TestRecord[] testingSet =  FileManager.readTestFile(testFile);
   //             Metric metric;
                if(metricType == 0)
     //               metric = new CosineSimilarity();
                else if(metricType == 1)
       //             metric = new L1Distance();
                else if (metricType == 2)
         //           metric = new EuclideanDistance();
                else{
                    System.out.println("The entered metric_type is wrong!");
                    return;
                }
                //test those TestRecords one by one
           //     int numOfTestingRecord = testingSet.length;
            //    for(int i = 0; i < numOfTestingRecord; i ++){
             //       TrainRecord[] neighbors = findKNearestNeighbors(trainingSet, testingSet[i], K, metric);
             //       int classLabel = classify(neighbors);
               //     testingSet[i].predictedLabel = classLabel; //assign the predicted label to TestRecord
                }
                //calculate the accuracy
                int correctPrediction = 0;
              //  for(int j = 0; j < numOfTestingRecord; j ++){
           //         if(testingSet[j].predictedLabel == testingSet[j].classLabel)
                        correctPrediction ++;
                }
                final long endTime = System.currentTimeMillis();
                System.out.println("Total excution time: "+(endTime - startTime) / (double)1000 +" seconds.");
        }
    private static String extractGroupName(String trainingFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		
	}
	
	//Find K nearest neighbors of testRecord within trainingSet 
	/*static TrainRecord[] findKNearestNeighbors(TrainRecord[] trainingSet, TestRecord testRecord,int K, Metric metric){
		int NumOfTrainingSet = trainingSet.length;
		assert K <= NumOfTrainingSet : "K is lager than the length of trainingSet!";
		
		TrainRecord[] neighbors = new TrainRecord[K];
		int index;
		for(index = 0; index < K; index++){
			trainingSet[index].distance = metric.getDistance(trainingSet[index], testRecord);
			neighbors[index] = trainingSet[index];
		}
	
		add the current trainingSet[index] into neighbors if applicable
			if(neighbors[maxIndex].distance > trainingSet[index].distance)
				neighbors[maxIndex] = trainingSet[index];
		}
		
		return neighbors;
	}*/
//Get the class label by using neighbors
//	static int classify(TrainRecord[] neighbors){
//		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
	//	int num = neighbors.length;
		
	//	for(int index = 0;index < num; index ++){
	//		TrainRecord temp = neighbors[index];
	//		int key = temp.classLabel;
	//		if(!map.containsKey(key))
	//			map.put(key, 1 / temp.distance);
	//		else{
	//			double value = map.get(key);
	//			value += 1 / temp.distance;
	//			map.put(key, value);
	//		}
//		}	
		
//		double maxSimilarity = 0;
	//	int returnLabel = -1;
	//	Set<Integer> labelSet = map.keySet();
	//	Iterator<Integer> it = labelSet.iterator();
	//	while(it.hasNext()){
	//		int label = it.next();
	//		double value = map.get(label);
	//		if(value > maxSimilarity){
	//			maxSimilarity = value;
	//			returnLabel = label;
	//		}
//		}
//		
//		return returnLabel;
//	}
	
//	static String extractGName(String filePath){
//		StringBuilder groupName = new StringBuilder();
//		for(int i = 15; i < filePath.length(); i++)
  //              {
//			if(filePath.charAt(i) != '_')
  //                      {	
    //                        groupName.append(filePath.charAt(i));
      //                  }     
        //                else
	//			break;
	//	}
		
	//	return groupName.toString(); 
//	}*/
}

