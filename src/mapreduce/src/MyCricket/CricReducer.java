import java.io.IOException;
import java.util.Iterator;

// exceptions import
import java.io.IOException;

// import box classes
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

// import reducer class
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CricReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterator<IntWritable> value, Context context) throws IOException {
		// TODO Auto-generated method stub
		int count = 0;
		while (value.hasNext()) {
			count += value.next().get();
		}
		context.write(key, new IntWritable(count));
	}

}
