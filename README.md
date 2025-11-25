# Genome2Reads

The program reads genomic data as fasta files (chromosmes/contigs) and writes artificial sequencing reads (fastq) to stdout. Read length and coordinate shift are taken as parameters.

Usage:   `java -jar Genome2Reads.jar input.fasta read_length[Int] read_shift[int] > out.fastq`

Example: `java -jar Genome2Reads.jar genome.fasta 100 1 > reads.fastq`

read_length: length of the reads generated

read_shift: tiling density; next read starts at position i + read_shift
