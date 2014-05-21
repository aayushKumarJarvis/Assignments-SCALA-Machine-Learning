
data_struct = {"total_spam":0,"total_ham":0,"total_spam_words":0,"total_ham_words":0,"bag_of_words":{}}

def learn(message, messagetype):
	bag_of_words = data_struct['bag_of_words']
	words = message.split(" ")
	for word in words:
		try:
			bag_of_words[word][messagetype]+=1	
		except KeyError:
			bag_of_words[word] = [1-messagetype,messagetype]
		data_struct["total_spam_words"]+=messagetype
		data_struct["total_ham_words"]+=(1-messagetype)
		
	data_struct["total_spam"]+=messagetype
	data_struct["total_ham"]+=(1-messagetype)
					
#K is laplacian smoother				
def predict(message,K):
	bag_of_words = data_struct['bag_of_words']
	k=K
	#total messages
	total_messages=data_struct["total_spam"]+data_struct["total_ham"]
	#prior probability of spam
	p_s=(data_struct["total_spam"]+k)/(total_messages*1.0+k*2)
	#prior probability of ham
	p_h=(data_struct["total_ham"]+k)/(total_messages*1.0+k*2)
	words=message.split(" ")
	#p_m_s of message given its spam && p_m_h probability of message given its ham
	p_m_s=1
	p_m_h=1
	for word in words:
		try:
			value1 = bag_of_words[word][0]
			value2 = bag_of_words[word][1]
		except:
			bag_of_words[word] = [0,1]	 
		p_m_s*=((bag_of_words[word][1]*1.0+k)/(data_struct["total_spam_words"]+k*(len(bag_of_words)))) 
		p_m_h*=((bag_of_words[word][0]*1.0+k)/(data_struct["total_ham_words"]+k*(len(bag_of_words))))  
	#bayes rule && p_s_m probability of spam given a particluar message
	if p_m_s*p_s+p_m_h*p_h is float(0.00000):
		return 0
	else:	
		try:
			p_s_m = p_s*p_m_s/(p_m_s*p_s+p_m_h*p_h)	
			return p_s_m
		except:
			return 0	
				

