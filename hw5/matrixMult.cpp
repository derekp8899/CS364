/*--------------------------------------------------

Derek Popowski
derek.a.popowski@und.edu
CSci 364 Concurrent and distributed programming
hw 5
Matrix multiplication with openMP

--------------------------------------------------*/
#include <iostream>
#include <random>
#include <ctime>
#include <chrono>

using namespace std;

int main(int argc, char* argv[]){

  //initialize the rng engine
  const int seed =1;
  mt19937 engine(seed);
  uniform_int_distribution<int> dist(-5,5);
  
  //default to no printing
  int print = 0;
  
  if(argc > 2 && (*argv[2] == 'y' || *argv[2] == 'Y' || *argv[2] == '1')){
    print = 1;
  }
  int size = 0;
  sscanf(argv[1],"%d",&size);
  //printf("matrix size = %d\nprint : %d\n",size,print);//debug for print set and matrix size

  //init the matrix to the correct size and set all values to 1
  int **M;
  int **N;
  //allocate memmory
  M = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    M[i]=(int*)malloc(size*sizeof(int));

  //initialize the values
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++){
      //if we are in test mode
      #ifdef TEST
      M[i][j]=1;
      //if we are not in test mode
      #else
      M[i][j]=dist(engine);
      #endif
      
    }
  //verify the matrix init

  /*
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++)
      printf("%d ",M[i][j]);
    printf("\n");
  }
  */
  
  //allocate the memory
  N = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    N[i]=(int*)malloc(size*sizeof(int));
  //initalize the values
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++){
      //if we are in test mode
      #ifdef TEST
      N[i][j]=1;
      //if we are not in test mode
      #else
      N[i][j]=dist(engine);
      #endif
    }
  //verify the matrix init
  
  /*
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++)
      printf("%d ",N[i][j]);
    printf("\n");
  }
  */
  
  //init the output matrix
  int **S;
  //allocate the memory
  S = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    S[i]=(int*)malloc(size*sizeof(int));
  //initalize to zero for the sums
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++)
      S[i][j]=0;
  
  //do the multiplication
  int rSum;
  chrono::milliseconds timer = chrono::duration_cast< chrono::milliseconds >(chrono::system_clock::now().time_since_epoch());
  int i=0;
  int j=0;
  int k=0;
  #pragma omp parallel for private(j,k,rSum) schedule (dynamic, 1) 
  for(i = 0; i < size; i++){
    for(j = 0; j < size; j++){
      rSum = 0;
      for(k = 0;k < size; k++)
	rSum+=(M[i][k])*(N[k][j]);
      S[i][j]=rSum;
    }
  }
  timer = timer - chrono::duration_cast< chrono::milliseconds >(chrono::system_clock::now().time_since_epoch());
  printf("duration of the multiplication: %d (ms)\n",-timer);
  //if we had the print flag on command line
  if(print==1)
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
	printf("%d ",S[i][j]);
      }
      printf("\n");
    }
  //free the memeory that we allocated
  for(int i = 0; i < size; i++){
    free(M[i]);
    free(N[i]);
    free(S[i]);
  }
  free(M);
  free(N);
  free(S);
}
