# EvenOdd

- [Driver Class](./MyDriver.java)
- [Mapper Class](./MyMapper.java)
- [Reducer Class](./MyReducer.java)
- [Input File](./evenodd.txt)

### How to execute

1. `H_CLASSPATH=$(hadoop classpath)`
   - creating a varible to store the path for the jar files needed for compiling
2. `javac *.java -cp $H_CLASSPATH`
   - compiling files to create `.class` files
3. `jar -cvf even_odd.jar *.class`
   - creating jar file
4. `hadoop fs -put evenodd.txt`
   - uploading the input file to HDFS
5. `hadoop jar even_odd.jar MyDriver evenodd.txt evenodd`
   - executing the map reduce program
6. `hadoop fs -ls evenodd`
   - listing the output files
7. `hadoop fs -cat evenodd\part-r-00000`
   - lising the contents of output file (only if step 5 was successful)