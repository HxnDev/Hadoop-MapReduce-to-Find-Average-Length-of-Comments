//--------------------------//
// Hassan Shahzad
// 18i-0441
// PDC A3
//--------------------------//

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgLengthReducer						// last comment of the file
    extends Reducer<Text, IntWritable, Text, IntWritable> {
	int length = 0;		// Length of a comment
	int count = 0;		// Number of comments
  
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {		// If last comment has been read
    	Text key = new Text();
		int avglen;					// Average length of comments
    	avglen = length/count;		// Calculating average
    	key.set("The average length of comments is = ");
        context.write(key, new IntWritable(avglen));
    }	
	
	
@Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
    
    String str = key.toString();	// Converting the comment into string
    length += str.length();			// Storing length of the comment
    count++;						// Increasing count after each comment
    
  }
}