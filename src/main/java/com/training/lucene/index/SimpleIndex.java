package com.training.lucene.index;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;

public class SimpleIndex {

	public static void index() {
		// 使用了标准分词器，可以选择的分词很多
		Analyzer analyzer = new StandardAnalyzer();
		try {
			IndexWriter writer = null;
			Directory directory = FSDirectory.open(Paths.get("d://myindex"));
//			Directory directory = MMapDirectory.open(Paths.get("d://myindex"));
			IndexWriterConfig config = new IndexWriterConfig();
			config.setUseCompoundFile(false);
			writer = new IndexWriter(directory, config);
			System.out.println(writer.getConfig());
			// 选择一段文本
			String text = "In all this Cuban business there is one man stands out on the horizon of my memory like Mars at perihelion.";
			Document doc = new Document();
			doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
			writer.addDocument(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void indexRAM() {
		// 可以将索引存储在内存中
		Directory directory = new RAMDirectory();
		try {
			IndexWriter writer = null;
			IndexWriterConfig config = new IndexWriterConfig();
			config.setUseCompoundFile(false);
			writer = new IndexWriter(directory, config);
			System.out.println(writer.getConfig());
			// 选择一段文本
			String text = "In all this Cuban business there is one man stands out on the horizon of my memory like Mars at perihelion.";
			Document doc = new Document();
			doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
			writer.addDocument(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		index();
		System.out.println("indexed");
	}
}
