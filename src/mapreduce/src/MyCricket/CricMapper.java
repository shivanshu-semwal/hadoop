import java.io.IOException;

// import Box classes 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

// import mapper class
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class CricMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] data = value.toString().split(",");
		Integer score = new Integer(data[2]);
		Integer four = new Integer(data[3]);
		Integer six = new Integer(data[4]);
		if (score >= 100) {
			context.write(new Text("Total Centuries"), new IntWritable(1));
		}
		if (score >= 50 && score < 100) {
			context.write(new Text("Total 50's"), new IntWritable(1));
		}
		context.write(new Text("Total Boundries"), new IntWritable(four));
		context.write(new Text("Total Six"), new IntWritable(six));
	}

}
