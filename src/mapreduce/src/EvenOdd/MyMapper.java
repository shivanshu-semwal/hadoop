// exception handling 
import java.io.IOException;

// box classes import
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

// import mapper class
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String data[] = value.toString().split(","); // data = [85 131 993 392 689....]
		for (String num : data) {
			int number = Integer.parseInt(num);
			if ((number % 2) == 1) {
				context.write(new Text("ODD"), new IntWritable(number)); // ODD 85 131 993
			} else {
				context.write(new Text("EVEN"), new IntWritable(number)); // EVEN 392
			}
		}
	}
}
