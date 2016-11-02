package lucene.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
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
import org.apache.lucene.util.Version;

public  class Aboutlucence {
	static String path="E:\\work_inspur\\lucene\\index";
	/**
	 * 功能：给查询出来的list建立索引
	 * @param list
	 * @throws IOException
	 * 2016年10月26日下午2:10:21
	 * @author--zyz
	 */
	public static void createIndex(List<Map<String,String>> list) throws IOException {
    	long start=System.currentTimeMillis();
    	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
    	Directory dire = FSDirectory.open(new File(path));//索引存放位置。
    	IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,  analyzer); 
    	IndexWriter iw = new IndexWriter(dire, iwc);
    	for (int j = 0; j < list.size(); j++) {
    		Map map=new HashMap<String, Object>();
    		map=(Map) list.get(j);
    		Document doc = new Document();
    		doc.add(new TextField("zlcode", (String) map.get("zlcode"), Store.YES));
    		doc.add(new TextField("zlnr", (String) map.get("zlnr"), Store.YES));
    		/*doc.add(new TextField("zlnrwb", (String) map.get("zlnrwb"), Store.YES));*/
        	iw.addDocument(doc);
		}
    	iw.commit();//提交；
    	iw.close(); // 关闭读写器
    	long end=System.currentTimeMillis();
    	System.out.println("消耗时间："+(end-start));
    }  
	/**
	   * 功能：查询索引
	   * @throws IOException
	   * @throws ParseException
	   * 2016年10月25日下午4:56:05
	   * @author--zyz
	   */
	    public static List searchindex(String key,String keyword,int num) throws IOException, ParseException {
	    	Date date=new Date();
	    	Directory dir = FSDirectory.open(new File(path));//打开索引位置
	    	IndexReader ir = DirectoryReader.open(dir);//读取索引
	    	IndexSearcher searcher = new IndexSearcher(ir);
	    	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
	    	QueryParser parser = new QueryParser(Version.LUCENE_46, key, analyzer);
	    	Query query = parser.parse(keyword);//查找内容
	    	TopDocs topDocs = searcher.search(query, num);
	    	ScoreDoc[] hits = topDocs.scoreDocs;
	    	List list = new ArrayList();//查找结果
	    	for (ScoreDoc sd : hits) {
	    		   Document d = searcher.doc(sd.doc);
	    		   Map m = new HashMap<String, Object>();
	    		   m.put("zlcode", d.get("zlcode"));
	    		   m.put("zlnr", d.get("zlnr"));
	    		  /* m.put("zlnrwb", d.get("zlnrwb"));*/
	    		   list.add(m); //以list的形式输出来
	    		  }
			Date date2=new Date();
			System.out.println("查询索引-----耗时：" + (date2.getTime() - date2.getTime()) + "ms\n");
			return list;
		}

}
