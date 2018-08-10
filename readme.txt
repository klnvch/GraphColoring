Input file (.txt format):

	First row:

		First value: maximum vertices per color
		Second value: number of vertices in the graph

	Rows after:
		Each row corresponds to a vertex in the graph
		First row corresponds to vertex with index 0, second row to vertex 1 etc.
		First value in the row is the number of neighbours of the corresponding vertex
		The other values in the row are the indices of the neighbours
		A vertex may not be a neighbour of itself, as graph cycles are disregarded
		in our graph colouring problem and would only complicate calculations.
		An input graph containing cycles may return invalid results!

Program:

File->Open: open text file with input data
File->Save as image: save output to JPEG File
File->Save as text: save output to TXT file

Option->Largest First: LF algorithm
Option->Smallest Last: SL algorithm
Option->Saturated Largest First: SLF algoritm

Output:

	Image:
		Vertex: index and degree
		Edges: just lines
		You can move the vertices in the window by dragging them.

	Text:
		First row: 
		Algorithm execution time in nanoseconds. From first line of code after the graph and the parameters
		have been passed to the algorithm, until the array of colours is filled. Does not include execution time of 
		returning from the function, as well as painting the graph on the screen.

		Second row: Empty

		Rows after:
		Vertex index and corresponding colour on each row of output

If output file already exists, data will be overwritten. No warning message is shown in case of an existing file, so be careful.

Minor bug in the program:
If there's only 1 node per colour, the last node in the graph will have colour -1 instead of n in the output.