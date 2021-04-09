# MyCricket

- [Driver Class](./MyCric.java)
- [Mapper Class](./CricMapper.java)
- [Reducer Class](./CricReducer.java)
- [Input File](./input_cric.txt)

Input file structure `101,Shikhar Dhawan,100,10,2`
- `101` - nunber
- `Shikhar Dhawan` - name of player
- `100` - total score
- `10` - total six
- `2` - total boundaries

### How to execute

- to get these files use

```sh
wget https://raw.githubusercontent.com/shivanshu-semwal/hadoop/master/src/mapreduce/src/MyCricket/get.sh
chmod +x get.sh
./get.sh
cd MyCricket
```

1. `H_CLASSPATH=$(hadoop classpath)`
   - creating a varible to store the path for the jar files needed for compiling
2. `javac *.java -cp $H_CLASSPATH`
   - compiling files to create `.class` files
3. `jar -cvf cricket.jar *.class`
   - creating jar file
4. `hadoop fs -put input_cric.txt`
   - uploading the input file to HDFS
5. `hadoop jar cricket.jar MyCric input_cric.txt cricket_out`
   - executing the map reduce program
6. `hadoop fs -ls cricket_out`
   - listing the output files
7. `hadoop fs -cat cricket_out\part-r-00000`
   - lising the contents of output file (only if step 5 was successful)