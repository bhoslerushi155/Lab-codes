#include<iostream>
#include<omp.h>
#include<stdlib.h>
using namespace std;

int threshold;
class merge{
	int n;
	int* arr;
public:
	void mergeSerial(int ,int);
	void mergeParallel(int ,int);
	void initArray();
	merge(int n1);
	int isSorted();
	void mergeArray(int,int,int);
};

merge::merge(int n1){
	n=n1;
	arr=new int[n];
}

void merge::initArray(){
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

void merge::mergeSerial(int low,int high){
	int mid=(high+low)/2;
	if(low<high){
		mergeSerial(low,mid);
		mergeSerial(mid+1,high);
		mergeArray(low,mid,high);
	}
}
	
void merge::mergeParallel(int low,int high){
	int mid=(high+low)/2;
	
	if((high-low)>threshold){
		#pragma omp parallel sections
		{
			#pragma omp section
			{
				mergeParallel(low,mid);
			}
			#pragma omp section
			{
				mergeParallel(mid+1,high);
			}
		}
		mergeArray(low,mid,high);	
	}
	else{
		mergeSerial(low,high);
	}

}

int merge::isSorted(){
	for(int i=0;i<n-1;i++){
		if(arr[i]>arr[i+1]){
			return 0;
		}
	}
	return 1;
}

void merge::mergeArray(int low,int mid,int high){
	int pt1=low,pt2=mid+1;
	int tarr[high-low+1];
	int iter=0;
	while(pt1<=mid && pt2<=high){
		if(arr[pt1]<arr[pt2]){
			tarr[iter++]=arr[pt1++];
		}
		else{
			tarr[iter++]=arr[pt2++];
		}
	}
	while(pt1<=mid){
		tarr[iter++]=arr[pt1++];
	}
	while(pt2<=high){
		tarr[iter++]=arr[pt2++];
	}
	for(int i=low,iter=0;i<=high;i++,iter++){
		arr[i]=tarr[iter];
	}
	
}
int main(){
	int n;
	cout<<"enter no of elements"<<endl;
	cin>>n;
	threshold=500;
	merge obj(n);
	
	int iter=30;
	float start,end,sum=0;
	
	for(int i=0;i<iter;i++){
	obj.initArray();
	start =omp_get_wtime();
	obj.mergeSerial(0,n-1);
	end=omp_get_wtime();
	sum+=end-start;
	}
	
	cout<<"avg time taken for serial sort : "<<sum/iter<<endl;
	cout<<"is sorted "<<obj.isSorted()<<endl;
	sum=0;
	for(int i=0;i<iter;i++)
	{
	obj.initArray();
	omp_set_num_threads(4);
	start =omp_get_wtime();
	obj.mergeParallel(0,n-1);
	end=omp_get_wtime();
	sum+=end-start;
	}
	cout<<"avg time taken for parallel sort : "<<sum/iter<<endl;
	cout<<"is sorted "<<obj.isSorted()<<endl;
}

