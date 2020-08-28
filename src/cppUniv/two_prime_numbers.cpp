/*


File : two_prime_numbers.cpp
Author : luke

Source Problem URL:
#Add URL here:


To run the file, run below command:
g++ two_prime_numbers.cpp && ./a.out <../TestInput/two_prime_numbers.txt


*/

#include <bits/stdc++.h>
using namespace std;
#define DEBUG 0

const int LIMIT = 10001;

int isPrime[LIMIT];
int allPrimes[LIMIT];
int countPrimes;

void initPrimes() {
	isPrime[0] = isPrime[1] = 0;
	for(int num=2;num<LIMIT;num++)
		isPrime[num]=1;
	//sieve
	for(int num=2;num<sqrt(LIMIT);num++) {
		if(!isPrime[num])
			continue;
		for(int nonPrime = num*num;nonPrime<LIMIT;nonPrime+=num)
			isPrime[nonPrime] = 0;
	}
	countPrimes = 0;
	for(int num=2;num<LIMIT;num++) {
		if(isPrime[num])
			allPrimes[countPrimes++]	 = num;
	}
	if(DEBUG) {cout<<"Number of primes" << countPrimes<<endl;}

}

void twoPrimes(const int number) 
{
	if(DEBUG) {cout<<number<<':'<<' ';}
	for(int a=0;a<countPrimes;a++) {
		int _prime = allPrimes[a];
		if(_prime>=number) 
			break;
		int _other_num = number-_prime;
		if(isPrime[_other_num]) {
			printf("%d %d", _prime, _other_num);
			break;
		}
	}
	cout<<endl;
}

int main(void) {
	int t, number;
	initPrimes();
	//return 0;
	scanf("%d", &t);
	while(t--) {
		scanf("%d", &number);
		twoPrimes(number);
	}
	return 0;
}