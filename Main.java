import java.util.Map;


public class Main {
	
	static char qualityChar = 'I';
	static String qualString;
	static int readLength;
	static int readShift;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		/*
		 * args[0] = input fasta
		 * args[1] = read length
		 * args[2] = read shift
		 */
		
		Map<String,String> seqs;
		try{
		seqs = FASTAParser.parseDNA(args[0]);
		readLength = Integer.parseInt(args[1]);
		readShift = Math.max(Integer.parseInt(args[2]),1);
		}
		catch(Exception e)
		{
			System.err.println("Usage:   java -jar Genome2Reads.jar input.fasta read_length[Int] read_shift[int] > out.fastq");
			System.err.println("Example: java -jar Genome2Reads.jar genome.fasta 100 1 > reads.fastq");
			System.err.println("read_length: length of the reads generated");
			System.err.println("read_shift: tiling density; next read starts at position i + read_shift");
			System.err.println("An error occured when parsing the command line:");
			e.printStackTrace();
			return;
		}
		
		
		StringBuffer qualBuffer = new StringBuffer(readLength);
		for(int i=0; i<readLength; i++)
			qualBuffer.append(qualityChar);
		qualString = qualBuffer.toString();
		
		String seq;
		String readname;
		int count;
		String readseq;
		for(String name : seqs.keySet())
		{
			seq = seqs.get(name);
			count = 1;
			readname = name.split("[\\s]+")[0] + "_read_";
			
			for(int i=0; i<seq.length()-readLength+1; i+=readShift)
			{
				readseq=seq.substring(i, i+readLength);
				readseq=readseq.toUpperCase();
				System.out.println("@"+readname+count+"\n"+readseq+"\n+\n"+qualString);
				count++;
			}
				
		}
		
	}

}
