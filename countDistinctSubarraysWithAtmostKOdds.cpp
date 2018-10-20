/*


File : countDistinctSubarraysWithAtmostKOdds.cpp
Author : abhijeet baranwal

Source Problem URL:
http://codeforces.com/blog/entry/62536


To run the file, run below command:
g++ countDistinctSubarraysWithAtmostKOdds.cpp && ./a.out <countDistinctSubarraysWithAtmostKOdds.txt


*/

#include <bits/stdc++.h>


const int ELEM_MAX_VAL = 200;
const int ARRAY_MAXLENGTH = 1000;

struct trie
{
	trie *child[ELEM_MAX_VAL];
	bool isLeaf;
	int face;
	trie() {
		isLeaf = false;
	}
	trie(int element) {
		isLeaf = true;
		face = element;
		for(int i=0;i<ELEM_MAX_VAL;i++)
			child[i] = NULL;
	}
};

int countDistinctSubarraysWithAtmostKOdds(const int n,const int k,const int arr[]) {
	int odd_count;
	int count_valid_subarrays;
	trie *Trie[ELEM_MAX_VAL], *temp_trie;
	for(int i=0;i<ELEM_MAX_VAL;i++) Trie[i] = NULL;
	count_valid_subarrays = 0;
	//iterate all subarrays
	for(int i=0;i<n;i++) {
		odd_count = 0;
		for(int j = i;j<n;j++) {
			int length_subarray = j-i+1;
			int element = arr[j];
			if((element%2)==1) odd_count++;
			if(odd_count>k) break;
			//analyze if this valid sub array is already counted or not
			if(length_subarray==1) {
				if(Trie[element-1]==NULL) {
					Trie[element-1] = new trie(element);
					count_valid_subarrays++;
				}
				temp_trie = Trie[element-1];
			}
			else {
				//check current element already counted or not
				if(temp_trie->child[element-1]==NULL) {
					temp_trie->child[element-1] = new trie(element);
					count_valid_subarrays++;
				}
				temp_trie = temp_trie->child[element-1];
			}
		}
	}
	return count_valid_subarrays;
}

int main(void) {
	int n, k;
	int arr[ARRAY_MAXLENGTH];
	scanf("%d", &n);
	scanf("%d", &k);
	assert(n>0 && n<=ELEM_MAX_VAL);
	assert(k>=0);
	for(int i=0;i<n;i++) {
		scanf("%d", arr+i);
		assert(arr[i]>=0 && arr[i]<ELEM_MAX_VAL);
	}
	printf("%d\n", countDistinctSubarraysWithAtmostKOdds(n, k, arr));

}