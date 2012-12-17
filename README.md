# Periodic Subgraph Miner
This is an algorithm to mine periodic subgraphs in a dynamic network. The original algorithm is discussed in the paper ["Periodic subgraph mining in dynamic networks"](http://compbio.cs.uic.edu/~mayank/papers/LahiriBergerWolf_PeriodicSubgraph09.pdf "Periodic subgraph mining in dynamic networks") by Lahiri and Berger-Wolf, and the implementation is located [here](http://compbio.cs.uic.edu/software/periodic/ "PSE Miner"). The algorithm in this project is based on the paper "Speedup for a periodic subgraph miner" by Apostolico, Barbares, and Pizzi.

##Purpose
The goal of the algorithm is to capture periodic and frequent interactions in a dynamic network. A dynamic network can be represented as a series of graphs at different time steps. 

![screenshot](https://raw.github.com/parthtejani/periodic-subgraph-miner/master/screenshots/network.png "Dynamic Network")

In this network, there are 4 periodic subgraphs with a minimum count of 2:

	1. (1,2,3,4,5,6,7,8,9)	Period: 2 	Start Time: 1	Count: 2
	2. (1,4,5,8,9)			Period: 2	Start Time: 1	Count: 3
	3. (1,2,3,6,7) 			Period: 3	Start Time: 1	Count: 2
	4. (1,2,3,6,7) 			Period: 1	Start Time: 3	Count: 2
	5. (1) 					Period: 1	Start Time: 3	Count: 3
	
	*Edge 1-2 = 6, Edge 1-3 = 7, Edge 1-4 = 8, Edge 1-5 = 9
 
## Usage
### Input Arguments

##### Required Parameters

	1. An input file		-i
	2. An output file		-o

##### Input File
The input file contains lines with tokens separated by whitespace that represent the graph at a particular time step. The first token is the actual time (can be a second, a minute, a date, a year, etc). The rest of the tokens are a series of integers in ascending order that represent the graph at that particular timestamp.

Example input file text:

	1s 1 2 3 4 5 6 7 8 9
	
	3s 1 2 3 4 5 6 7 8 9
	4s 1 2 3 6 7
	5s 1 4 5 8 9
	
The integers representing the graph are uniquely mapped to vertexes and edges as shown below.

![screenshot](https://raw.github.com/parthtejani/periodic-subgraph-miner/master/screenshots/graph-representation.png "Graph Representation")

##### Output File
The output file contains data specific for grading this project. To view the actual periodic subgraphs, use the argument "-d" when calling the program to print the subgraphs to the console. 

Example console output:

	start 3 psup 2 p 1 m 0 [1 2 3 6 7]
	start 1 psup 2 p 2 m 0 [1 2 3 4 5 6 7 8 9]
	start 3 psup 3 p 1 m 0 [1]
	start 1 psup 3 p 2 m 0 [1 4 5 8 9]
	start 1 psup 2 p 3 m 0 [1 2 3 6 7]

start = start time,
psup = support size/count,
p = period,
m = phase

##### Optional Parameters
	1. Minimum support size (at least 2)		-s		default:3
	2. Minimum period (at least 1)				-Pmin	default:1
	3. Maximum period (at least minimum period)	-Pmax	default:10
	4. Smooth step (can only be 1)				-smooth	default:1
	5. Turn subsumption off	(flag only)			-f
	6. Print graphs to console (flag only)		-d

Time smoothing is not implemented because it was not required for this project.
### Commands

##### Setup:

    git clone https://github.com/parthtejani/periodic-subgraph-miner.git
    cd periodic-subgraph-miner
    mkdir bin
    javac -d bin src/com/parthtejani/listminer/*.java
    cd bin
    
##### Example Usage:
	java com.parthtejani.listminer.ListMiner -i /some/input/file -o /some/output/file -s 3 -Pmin 1 -Pmax 10

You may need to change the heap size depending on the size of the input data, and the parameters used. The maximum heap size can be set with the "-Xmx" flag. -Xmx1024M sets the maximum heap size to 1024 MB.