package com.java.huhaoran;

import com.java.huhaoran.note.BrowseHistoryNote;
import com.java.huhaoran.note.FavoritesHistoryNote;
import com.java.huhaoran.note.SearchHistoryNote;

import java.util.ArrayList;
import java.util.HashSet;
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

    public static List<FetchNews.NewsItem> transfer2(List<FavoritesHistoryNote> favoritesHistoryNotes) {
        if(favoritesHistoryNotes == null) {
            return null;
        }
        //创建一个新闻对象列表
        List<FetchNews.NewsItem> newsItems = new ArrayList<>();
        //遍历表单，转换为新闻对象
        for(FavoritesHistoryNote favotitesHistoryNote : favoritesHistoryNotes) {
            FetchNews.NewsItem newsItem = new FetchNews.NewsItem();
            newsItem.title = favotitesHistoryNote.title;
            newsItem.publishTime = favotitesHistoryNote.publishTime;
            newsItem.content = favotitesHistoryNote.content;
            newsItem.publisher = favotitesHistoryNote.publisher;
            newsItem.image = favotitesHistoryNote.image;
            newsItem.video = favotitesHistoryNote.video;
            newsItems.add(newsItem);
        }
        return newsItems;
    }

    public static List<SearchData> transfer3(List<SearchHistoryNote> searchHistoryNotes) {
        if(searchHistoryNotes == null) {
            return null;
        }
        List<SearchData> searchDatas = new ArrayList<>();
        for(SearchHistoryNote searchHistoryNote : searchHistoryNotes) {
            SearchData searchData = new SearchData();
            searchData.setKeyword(searchHistoryNote.keyword);
            HashSet<String> categories = new HashSet<>();
            String[] categoryArray = FetchNews.getLinks(searchHistoryNote.categories);
            for(String category : categoryArray) {
                categories.add(category);
            }
            searchData.setCategories(categories);
            searchData.setStartDate(searchHistoryNote.startDate);
            searchData.setEndDate(searchHistoryNote.endDate);
            searchDatas.add(searchData);
        }
        return searchDatas;
    }
}
