package com.hxx.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.hxx.entity.Blog;
import com.hxx.util.DateUtil;
import com.hxx.util.StringUtil;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @ClassName BlogIndex
 * @Description //TODO 博客索引类
 * @Author haoxx
 * @Date 2019/4/7 - 20:59
 * @Version 1.0
 */
public class BlogIndex {

    private Directory dir;

    /**
     *@author haoxx
     *@Description //TODO 获取IndexWriter实例
     *@Date 2019/4/7 - 21:00
     *@Param []
     *@return org.apache.lucene.index.IndexWriter
     */
    private IndexWriter getWriter()throws Exception{
        dir= FSDirectory.open(Paths.get("C://lucene"));
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     *@author haoxx
     *@Description //TODO  添加博客索引
     *@Date 2019/4/7 - 21:00
     *@Param [blog]
     *@return void
     */
    public void addIndex(Blog blog)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    public void updateIndex(Blog blog)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        writer.close();
    }
    /**
     *@author haoxx
     *@Description //TODO 删除指定博客的索引
     *@Date 2019/4/8 - 12:53
     *@Param [blogId]
     *@return void
     */
    public void deleteIndex(String blogId)throws Exception{
        IndexWriter writer=getWriter();
        writer.deleteDocuments(new Term("id",blogId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
        writer.close();
    }
    
    /**
     *@author haoxx
     *@Description //TODO 查询博客信息
     *@Date 2019/4/7 - 21:23
     *@Param [q]
     *@return java.util.List<com.hxx.entity.Blog>
     */
    public List<Blog> searchBlog(String q)throws Exception{
        dir=FSDirectory.open(Paths.get("C://lucene"));
        IndexReader reader= DirectoryReader.open(dir);
        IndexSearcher is=new IndexSearcher(reader);
        BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        QueryParser parser=new QueryParser("title", analyzer);
        Query query=parser.parse(q);

        QueryParser parser2=new QueryParser("content", analyzer);
        Query query2=parser2.parse(q);

        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

        TopDocs hits=is.search(booleanQuery.build(), 100);
        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);

        List<Blog> blogList=new LinkedList<Blog>();
        for(ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc=is.doc(scoreDoc.doc);
            Blog blog=new Blog();
            blog.setId(Integer.parseInt(doc.get("id")));
            blog.setReleaseDateStr(doc.get("releaseDate"));
            String title=doc.get("title");
            String content=doc.get("content");
            if(title!=null){
                TokenStream tokenStream=analyzer.tokenStream("title", new StringReader(title));
                String hTitle=highlighter.getBestFragment(tokenStream, title);
                if(StringUtil.isEmpty(hTitle)){
                    blog.setTitle(title);
                }else{
                    blog.setTitle(hTitle);
                }
            }

            if(content!=null){
                TokenStream tokenStream=analyzer.tokenStream("content", new StringReader(content));
                String hContent=highlighter.getBestFragment(tokenStream, content);
                if(StringUtil.isEmpty(hContent)){
                    if(content.length()<=200){
                        blog.setContent(content);
                    }else{
                        blog.setContent(content.substring(0, 200));
                    }
                }else{
                    blog.setContent(hContent);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }
}
