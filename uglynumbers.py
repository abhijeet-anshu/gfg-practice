'''

File : uglynumbers.py
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/ugly-numbers/0


To run the file, run below command:
python uglynumbers.py <uglynumbers.txt


'''

import math

class Ugly:
	def __init__(self, p2=None, p3=None, p5=None):  
		self.p2 = p2
		self.p3 = p3
		self.p5 = p5
		self.logVal = p2*math.log(2) + p3*math.log(3) + p5*math.log(5)

	def __str__(self):
		return str(self.logVal)
	
	def myVal(self, force=False):
		if force is None or not force:
			return self.logVal
		return (2**self.p2)*(3**self.p3)*(5**self.p5)

def getNthNumber(n):
  return n+1

def initalize(limit2, limit3, limit5):
	myAllNodeList = [5] * (1+limit2)*(1+limit3)*(1+limit5)
	currentIndex = -1
	for p2 in range(0, limit2+1):
		for p3 in range(0, limit3+1):
			for p5 in range(0, limit5+1):
				currentIndex += 1
				_permutation = Ugly(p2, p3, p5)
				myAllNodeList[currentIndex] = _permutation
	import operator
	myAllNodeList.sort(key=operator.attrgetter('logVal'))
	_debug_prev = -1
	myl = len(myAllNodeList)
	assert myl>10000
	#for i in range
	#for val in myAllNodeList:
	max5 = max3 = max2 = 0
	
	for i in range(0, 10000):
		val = myAllNodeList[i]
		max5 = max(max5, val.p5)
		max3 = max(max3, val.p3)
		max2 = max(max2, val.p2)

		_thisVal = val.myVal(True)
		assert(_debug_prev<_thisVal)
		_debug_prev = _thisVal
	
	return myAllNodeList

	print(len(myAllNodeList))
	print("2:" + str(max2))
	print("3:" + str(max3))
	print("5:" + str(max5))
	
	

if __name__=="__main__": 
	myAllNodeList = initalize(60, 40, 30)
	t = int(input())
	for num in range(0, t):
		n = int(input())
		print(myAllNodeList[n-1].myVal(True))