package com.edu.smu.track2career.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class LuceneManager {

    private static Analyzer analyzer;
    private static Analyzer industryAnalyzer;
    public static Directory index;
    public static Directory industryIndex;

    public static void startGatheringDocuments() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String base = context.getRealPath(".");
        String folder = File.separator + "data";
        String file = File.separator + "data.txt";

        try {
            // 0. Specify the analyzer for tokenizing text.
            //    The same analyzer should be used for indexing and searching
            analyzer = new StandardAnalyzer();

            // 1. create the index
            index = new RAMDirectory();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            IndexWriter writer = new IndexWriter(index, config);

            Scanner scanner = new Scanner(new File(base + folder + file));

            while (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                Document document = new Document();
                document.add(new TextField("skill", value, Field.Store.YES));
                writer.addDocument(document);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initializeIndustryAnalyzer(List<String> industries) {
        try {
            industryAnalyzer = new StandardAnalyzer();

            industryIndex = new RAMDirectory();

            IndexWriterConfig industryConfig = new IndexWriterConfig(industryAnalyzer);

            IndexWriter industryWriter = new IndexWriter(industryIndex, industryConfig);

            for (String industry : industries) {
                Document document = new Document();
                document.add(new TextField("industry", industry, Field.Store.YES));
                industryWriter.addDocument(document);
            }

            industryWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TopDocs searchQuery(String word) {
        try {
            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
//            TermQuery query = new TermQuery(new Term("skill", word)); // All NaN
            Query query = new QueryParser("skill", analyzer).parse(word);
            BooleanQuery bq = new BooleanQuery.Builder()
                    .add(query, BooleanClause.Occur.MUST)
                    .build();
//            FuzzyQuery fq = new FuzzyQuery(new Term("skill", word), (int) FuzzyQuery.defaultMinSimilarity, 0);
//            FuzzyQuery query = new FuzzyQuery(new Term("skill", word));

            // 3. search
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(bq, hitsPerPage);

            return docs;
//            return docs.getMaxScore();
//            ScoreDoc[] hits = docs.scoreDocs;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TopDocs searchIndustryQuery(String industry) {
        try {
            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
//            TermQuery query = new TermQuery(new Term("skill", word)); // All NaN
            Query query = new QueryParser("industry", industryAnalyzer).parse(industry);
            BooleanQuery bq = new BooleanQuery.Builder()
                    .add(query, BooleanClause.Occur.MUST)
                    .build();
//            FuzzyQuery fq = new FuzzyQuery(new Term("skill", word), (int) FuzzyQuery.defaultMinSimilarity, 0);
//            FuzzyQuery query = new FuzzyQuery(new Term("skill", word));

            // 3. search
            int hitsPerPage = 1;
            IndexReader reader = DirectoryReader.open(industryIndex);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(bq, hitsPerPage);

            return docs;
//            return docs.getMaxScore();
//            ScoreDoc[] hits = docs.scoreDocs;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TopDocs searchFuzzyQuery(String word) {
        try {
            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
//            TermQuery query = new TermQuery(new Term("skill", word)); // All NaN
            Query query = new QueryParser("skill", analyzer).parse(word);
            BooleanQuery bq = new BooleanQuery.Builder()
                    .add(query, BooleanClause.Occur.MUST)
                    .build();
//            FuzzyQuery fq = new FuzzyQuery(new Term("skill", word), (int) FuzzyQuery.defaultMinSimilarity, 2);
//            FuzzyQuery fq = new FuzzyQuery(new Term("skill", word));

            // 3. search
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
//            ScoreDoc[] docs = searcher.search(fq, 10).scoreDocs;
            TopDocs docs = searcher.search(bq, hitsPerPage);

            return docs;
//            return docs.getMaxScore();
//            ScoreDoc[] hits = docs.scoreDocs;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
