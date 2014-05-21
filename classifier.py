#email spam filter using Naive Bayes classification

import os
import re
from subprocess import Popen
import spamfilter
from os import listdir
from os.path import isfile

#Training the classifier with the given training dataset
with open('SPAMTrain.txt') as spamRead:
	
	for line in spamRead:
		l = line.strip()
		parts = l.split(" ")
		classLabel = parts[0]	
		contentFile = parts[1]
		
		with open(contentFile) as trainingData:
			#trainDataRead=trainingData.read().replace('\n', '')
				
			temp = re.split("[^a-zA-Z]+", trainingData.read())
			temp = [x.lower() for x in temp]				
			
			resultString = ""
			for x in temp:
				resultString += x + " " 
			 			
			spamfilter.learn(resultString,int(classLabel))
	
#Displaying the probability of a test data being spam or ham
	
files = os.listdir("TEST/")
for file in files:
	with open('TEST/'+file) as test:
		temp = re.split("[^a-zA-Z]+", test.read())
		temp = [x.lower() for x in temp]				
		resultString = ""
		for x in temp:
			resultString += x + " " 
		if spamfilter.predict(resultString,10) > 0.5:
			print "SPAM"
		else:
			print "HAM"




			
			
			
