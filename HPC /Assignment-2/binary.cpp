#include<iostream>
#include<cstdlib>
#include<omp.h>
using namespace std;

class binary{
	int* arr;
	int size;
public:
	void initArray(int);
	void binarySerial(int arr[],int n);
	void binaryParallel(int arr[],int n);
	int binarySearch(int low,int high,int key);
};

void binary::initArray(int s){
	size=s;
	arr=new int[size];
	int temp=rand()%100;
	for(int i=0;i<size;i++){
		arr[i]=i+temp;
	}
}
void binary::binarySerial(int ar[],int n){
	for(int i=0;i<n;i++){
		int ind=binarySearch(0,size-1,ar[i]);
		if(ind==-1){
			//cout<<"Key not found"<<endl;
		}
		else{
			//cout<<"key found at index :"<<ind<<endl;
		}
	}
}

void binary::binaryParallel(int ar[],int n){
	#pragma omp parallel for
	for(int i=0;i<n;i++){
		int ind=binarySearch(0,size-1,ar[i]);
		if(ind==-1){
			//cout<<"Key not found"<<endl;
		}
		else{
			//cout<<"key found at index :"<<ind<<endl;
		}
	}
}

int binary::binarySearch(int low,int high,int key){
	int mid=(high+low)/2;
	if(low<high){
		if(key==arr[mid]){
			return mid;
		}
		else if(key<arr[mid]){
			binarySearch(low,mid-1,key);
		}
		else if(key>arr[mid]){
			binarySearch(mid+1,high,key);	
		}
	}
	else{
		return -1;
	}
	
}

int main(){
	int size,num;
	cout<<"Enter size of array"<<endl;
	cin>>size;
	binary obj;
	obj.initArray(size);
	
	cout<<"Enter no of elements to search"<<endl;
	cin>>num;
	cout<<"Keys are generated randomly"<<endl;
	int ar[num],temp;
	for(int i=0;i<num;i++){
		ar[i]=rand()%size;
	}
	
	int iter=60;
	float start,end,sum=0;
	for(int i=0;i<iter;i++){
		start =omp_get_wtime();
		obj.binarySerial(ar,num);
		end=omp_get_wtime();
		sum+=end-start;
	}
	
	cout<<"avg time taken for serial binary search : "<<sum/iter<<endl;
	
	sum=0;
	for(int i=0;i<iter;i++){
		start =omp_get_wtime();
		obj.binaryParallel(ar,num);
		end=omp_get_wtime();
		sum+=end-start;
	}
	
	cout<<"avg time taken for parallel binary search : "<<sum/iter<<endl;
	
	
}

/*
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-2$ ./a.out 
Enter size of array
2000000
Enter no of elements to search
100000
Keys are generated randomly
avg time taken for serial binary search : 0.067513
avg time taken for parallel binary search : 0.046875

virus@virus-VirtualBox:~/Desktop/HPC/Assignment-2$ ./a.out 
Enter size of array
2000000
Enter no of elements to search
300000
Keys are generated randomly
avg time taken for serial binary search : 0.21875
avg time taken for parallel binary search : 0.145964

*/
