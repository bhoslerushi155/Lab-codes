#include<iostream>
#include<omp.h>
#include<stdlib.h>
using namespace std;

class bubble{
	int n;
	int* arr;
public:
	void bubbleSerial();
	void bubbleParallel();
	void initArray();
	bubble(int n1);
	int isSorted();
};

bubble::bubble(int n1){
	n=n1;
	arr=new int[n];
}

void bubble::initArray(){
	for(int i=0;i<n;i++){
		arr[i]=i;
	}
	for(int i=0;i<n;i++){
		int j=rand()%n;
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}

void bubble::bubbleSerial(){
	for(int i=0;i<n;i++){
		for(int j=0;j<n-1;j++){
			if(arr[j]>arr[j+1]){
			int temp=arr[j];
			arr[j]=arr[j+1];
			arr[j+1]=temp;
			}
		}
	}
}

void bubble::bubbleParallel(){
	#pragma omp parallel
	{	
		#pragma omp for
		for(int i=0;i<n;i++){
			for(int j=0;j<n-1;j++){
				if(arr[j]>arr[j+1]){
				int temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
				}
			}	
		}
	}
	
}

int bubble::isSorted(){
	for(int i=0;i<n-1;i++){
		if(arr[i]>arr[i+1]){
			return 0;
		}
	}
	return 1;
}
int main(){
	int n;
	cout<<"enter no of elements"<<endl;
	cin>>n;
	bubble obj(n);
	obj.initArray();
	float start,end;
	start =omp_get_wtime();
	obj.bubbleSerial();
	end=omp_get_wtime();
	cout<<"time taken for serial sort : "<<end-start<<endl;
	cout<<"is sorted "<<obj.isSorted()<<endl;
	obj.initArray();
	start =omp_get_wtime();
	obj.bubbleParallel();
	end=omp_get_wtime();
	cout<<"time taken for parallel sort : "<<end-start<<endl;
	cout<<"is sorted "<<obj.isSorted()<<endl;
}

/*
virus@virus-VirtualBox:~/Desktop$ ./a.out
enter no of elements
1000
time taken for serial sort : 0.00537109
is sorted 1
time taken for parallel sort : 0.00390625
is sorted 1

virus@virus-VirtualBox:~/Desktop$ ./a.out
enter no of elements
10000 
time taken for serial sort : 0.601562
is sorted 1
time taken for parallel sort : 0.328125
is sorted 1

virus@virus-VirtualBox:~/Desktop$ ./a.out
enter no of elements
100000
time taken for serial sort : 59.6592
is sorted 1
time taken for parallel sort : 31.6455
is sorted 1

*/
