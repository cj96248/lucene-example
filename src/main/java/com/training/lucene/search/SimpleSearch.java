package com.training.lucene.search;


import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SimpleSearch {
	
	public static void search(){
		Directory dir;
		Analyzer analyzer = new StandardAnalyzer();
		try {
			dir = FSDirectory.open(Paths.get("d://myindex"));
			DirectoryReader reader = DirectoryReader.open(dir);
			IndexSearcher isearcher = new IndexSearcher(reader);
			QueryParser parser = new QueryParser("fieldname", analyzer);
			Query query = parser.parse("business");
			ScoreDoc[] hits = isearcher.search(query, 1000, null).scoreDocs;
			System.out.println(hits.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		search();
	}
}
