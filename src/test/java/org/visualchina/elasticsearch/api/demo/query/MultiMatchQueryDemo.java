package org.visualchina.elasticsearch.api.demo.query;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;
import org.visualchina.elasticsearch.api.util.ResponseUtil;

/**
 * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.5/java-full-text-queries.html#java-query-dsl-simple-query-string-query'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-08
 */
public class MultiMatchQueryDemo extends XPackBaseDemo {

    @Test
    public void testForClient() throws Exception {
        QueryBuilder qb = QueryBuilders.multiMatchQuery(
                "elasticsearch match query",
                "title", "descrption"
        );
       SearchResponse searchResponse =  client.prepareSearch()
                .setIndices("blogs")
                .setTypes("blog")
                .setQuery(qb)
                .execute()
                .actionGet();

       System.out.println(ResponseUtil.parse(searchResponse));
    }
}
