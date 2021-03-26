# MapReduce

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

### Examples

```

