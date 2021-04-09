# WordCount

- [Driver Class](./WordCountDriver.java)
- [Mapper Class](./WordCountMapper.java)
- [Reducer Class](./WordCountReducer.java)
- [Input File](./poem.txt)

### How to execute

1. `H_CLASSPATH=$(hadoop classpath)`
   - creating a varible to store the path for the jar files needed for compiling
2. `javac *.java -cp $H_CLASSPATH`
   - compiling files to create `.class` files
3. `jar -cvf wordcount.jar *.class`
   - creating jar file
4. `hadoop fs -put poem.txt`
   - uploading the input file to HDFS
5. `hadoop jar wordcount.jar WordCountDriver poem.txt wordcountout`
   - executing the map reduce program
6. `hadoop fs -ls wordcountout`
   - listing the output files
7. `hadoop fs -cat wordcountout\part-r-00000`
   - lising the contents of output file (only if step 5 was successful)