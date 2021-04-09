# MapReduce

## Examples

- [`WordCount`](./src/WordCount)
- [`EvenOdd`](./src/EvenOdd)
- [`MyCricket`](./src/MyCricket)

## Box Classes

`Serialization` is the process of converting object data into byte stream data for transmission over a network across different nodes in a cluster or for persistent data storage.

Since hadoop use serilization for optimization, native java wrapper class are not used, but rather box classes similar to them are used in MapReduce programs.

Java Native | MapReduce  | Import 
---|---|---
`Boolean` |`BooleanWritable`| `import org.apache.hadoop.io.BooleanWritable`
`Byte` |`ByteWritable`| `import org.apache.hadoop.io.ByteWritable`
`Integer` |`IntWritable`| `import org.apache.hadoop.io.IntWritable;`
`long int` | `VIntWritable`| `import org.apache.hadoop.io.VIntWritable`
`Float` | `FloatWritable`| `import org.apache.hadoop.io.FloatWritable`
`Long` | `LongWritable`| `import org.apache.hadoop.io.LongWritable;`
`long long` | `VLongWritable`| `import org.apache.hadoop.io.VLongWritable`
`Double` |`DoubleWritable`| `import org.apache.hadoop.io.DoubleWritable;`
`String` | `Text` | `import org.apache.hadoop.io.Text;`

## Mapper Class

Any mapper class for a `MapReduce` program extends the abstract `Mapper` class.
And then we have to override the `map` function, which takes the `key-value` pair and reference to a `Context` variable, which is them handled by the `reduce` function.

Basic template for a mapper class -
```java
public class [mapper-class] extends Mapper<[key-type1], [value-type-1], [key-type-2], [value-type-2]> {
	public void map([key-type-1] key, [value-type-1] value, Context context) {
        // body of mapper
    }
}
```

Needed imports

```java
import java.io.IOException;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
```

## Reducer Class

Reducer class for a `MapReduce` program extends the abstract class `Reducer`. The `reduce` method is to be overridden in this class.

Basic template of reducer class -
```java
public class [reducer-class] extends Reducer<[key-type-1], [value-type-1], [key-type-2], [value-type-2]> {
	public void reduce([key-type-1] key, Iterable<[value-type-1]> values, Context context){
        //body of reducer
    }
}
```

Needed imports

```java
import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
```

## Driver Class

Driver class is the main class which controls the execution of the program. Here we create a `Job` object and set the driver, mapper, and reducer class used in our program.

Basic template for a driver class-
```java
public class [driver-class] {
	public static void main(String[] args) {
		Job j = new Job();
		j.setJobName("My First Job");
		j.setJarByClass([driver-class].class);
		j.setMapperClass([mapper-class].class);
		j.setReducerClass([reducer-class].class);
		j.setOutputKeyClass([key-type].class);
		j.setOutputValueClass([value-type].class);
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
```

Needed imports

```java
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
```

## How to execute MapReduce 

### Step 1

- execute the following command to store the location of all the jar files needed for the execution of the map reduce program.

```bash
H_CLASSPATH=$(hadoop classpath)
```

Check if the command was succesful.

```bash
echo $H_CLASSPATH
```

### Step 2

- compile to `.java` files to there respective `.class` files, either individually or all at once.

For individual files, here `-cp` flag stands for `classpath`

```bash
javac -cp $H_CLASSPATH filename.java
```

or all `.java` files at once using wildcards like `*.java`

```bash
javac -cp $H_CLASSPATH *.java
```

### Step 3

- make a jar file of all the `.class` files, using this command

```
jar -xcf [jarfilename.jar] *.class
```

replace `[jarfilename.jar]` with the name of jar file you want.

### Step 4

- put the files which you want to use `MapReduce` program on, to the `HDFS Filesystem`

```bash
hadoop fs -put [path to files]
```

### Step 5

- execute the `MapReduce` program

```bash
hadoop jar [jar-file-name.jar] [input-file] [output-folder]
```

### Step 6

- see the output

```
hadoop fs -ls [output-folder]
```

- this will show the contents of the output folder, to see the contents of the files in the output folder use this command

```bash 
hadoop fs -cat [output-folder]/[filename]
```

### Example

- for [`WordCount`](./src/WordCount) program (clone this repo to try it, make sure you are in the `WordCount` directory while executing these commands)

```sh
H_CLASSPATH=$(hadoop classpath)
javac *.java
jar -xcf wordcount.jar *.class
hadoop -fs -put poem.txt
hadoop jar wordcount.jar poem.txt wordcountout
hadoop fs -ls wordcountout
```

> See complete explanation of a sample WordCount MapReduce program - [WordCount](./map-reduce-explain.md)