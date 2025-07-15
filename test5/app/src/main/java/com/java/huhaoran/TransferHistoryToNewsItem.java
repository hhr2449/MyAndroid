package com.java.huhaoran;

import com.java.huhaoran.note.BrowseHistoryNote;

import java.util.ArrayList;
import java.util.List;


//这个是一个工具类，用于将一个表单对象转化为一个新闻对象
public class TransferHistoryToNewsItem {

    public static List<FetchNews.NewsItem> transfer(List<BrowseHistoryNote> browseHistoryNotes) {
        if(browseHistoryNotes == null) {
            return null;
        }
        //创建一个新闻对象列表
        List<FetchNews.NewsItem> newsItems = new ArrayList<>();
        //遍历表单，转换为新闻对象
        for(BrowseHistoryNote browseHistoryNote : browseHistoryNotes) {
            FetchNews.NewsItem newsItem = new FetchNews.NewsItem();
            newsItem.title = browseHistoryNote.title;
            newsItem.publishTime = browseHistoryNote.publishTime;
            newsItem.content = browseHistoryNote.content;
            newsItem.publisher = browseHistoryNote.publisher;
            newsItem.category = browseHistoryNote.category;
            newsItem.image = browseHistoryNote.image;
            newsItem.video = browseHistoryNote.video;
            newsItems.add(newsItem);
        }
        return newsItems;
    }
}
