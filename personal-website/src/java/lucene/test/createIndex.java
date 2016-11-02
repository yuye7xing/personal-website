package lucene.test;

import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;  
import java.nio.file.Paths;  
  


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.standard.StandardAnalyzer;  
import org.apache.lucene.document.Document;  
import org.apache.lucene.document.Field;  
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;  
import org.apache.lucene.document.StringField;  
import org.apache.lucene.document.TextField;  
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;  
import org.apache.lucene.index.IndexWriterConfig;  
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;  
import org.apache.lucene.store.FSDirectory;  
import org.apache.lucene.store.RAMDirectory;  
import org.apache.lucene.util.Version;
  
/** 
 * @author: IT学习者 
 * @官网 www.itxxz.com 
 * @version: 2015年5月28日 下午8:54:48 
 */  
public class createIndex {  
  static String path="E:\\work_inspur\\lucene\\index";
    /** 
     * @author: IT学习者 
     * @官网 www.itxxz.com 
     * @version: 2015年5月28日 下午8:54:48 
     * @throws IOException 
     * @throws ParseException 
     */  
    public static void main(String[] args) throws IOException, ParseException {  
//        createIndex();  
        lookindex();
//    	  Indexnum();
    }  
  /**
   * 功能：查询索引
   * @throws IOException
   * @throws ParseException
   * 2016年10月25日下午4:56:05
   * @author--zyz
   */
    private static void lookindex() throws IOException, ParseException {
    	Date date=new Date();
    	Directory dir = FSDirectory.open(new File(path));//打开索引位置
    	IndexReader ir = DirectoryReader.open(dir);//读取索引
    	IndexSearcher searcher = new IndexSearcher(ir);
    	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
    	QueryParser parser = new QueryParser(Version.LUCENE_46, "mc", analyzer);
    	Query query = parser.parse("+");//查找内容
    	TopDocs topDocs = searcher.search(query, 100);
    	ScoreDoc[] hits = topDocs.scoreDocs;
    	List list = new ArrayList();//查找结果
    	for (ScoreDoc sd : hits) {
    		   Document d = searcher.doc(sd.doc);
    		   Map m = new HashMap<String, Object>();
    		   m.put("content", d.get("content"));
    		   System.out.println(d.get("mc")+"------"+d.get("content"));
    		   list.add(m); //以list的形式输出来
    		  }
		Date date2=new Date();
		System.out.println("查询索引-----耗时：" + (date2.getTime() - date2.getTime()) + "ms\n");
	}
/**
 * 功能：生成索引
 * @throws IOException
 * 2016年10月25日下午4:55:47
 * @author--zyz
 */
	public static void createIndex() throws IOException {
    	long start=System.currentTimeMillis();
    	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
    	Directory dire = FSDirectory.open(new File(path));//索引存放位置。
    	IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,  analyzer); 
    	IndexWriter iw = new IndexWriter(dire, iwc);
    	for (int j = 0; j < 20; j++) {
    		Document doc = new Document();
        	doc.add(new TextField("mc", "test+"+j, Store.YES));
        	iw.addDocument(doc);
		}
    	iw.commit();//提交；
    	iw.close(); // 关闭读写器
    	long end=System.currentTimeMillis();
    	System.out.println("消耗时间："+(end-start));
    }  
/*	public static void Indexnum() throws IOException {
    	long start=System.currentTimeMillis();
    	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
    	Directory dire = FSDirectory.open(new File(path));//索引存放位置。
    	IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,  analyzer); 
    	IndexWriter iw = new IndexWriter(dire, iwc);
    	for (int j = 0; j < iw.numDocs(); j++) {
    		Document d =iw.
    	}
	}*/
}
