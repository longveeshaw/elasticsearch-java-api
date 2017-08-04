package org.visualchina.elasticsearch.api.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

/**
 * Elasticsearch 5.5.0 的client 和 ElasticsearchTemplate的初始化
 * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.5/transport-client.html'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-07
 */
public class BaseDemo {

    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;
    protected RestClient restClient ;

    @Before
    public void setUp() throws Exception {
        /**
         * 这里的连接方式指的是没有安装x-pack插件,如果安装了x-pack则参考{@link XPackBaseDemo}
         * 1. java客户端的方式是以tcp协议在9300端口上进行通信
         * 2. http客户端的方式是以http协议在9200端口上进行通信
         */
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.60.249"), 9300));
        elasticsearchTemplate = new ElasticsearchTemplate(client);
        restClient = RestClient.builder(new HttpHost("192.168.60.249",9200)).build();
    }

    @Test
    public void testConnection() throws Exception {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("Sean Archer");
        ActionFuture<AnalyzeResponse> analyzeResponseActionFuture =  elasticsearchTemplate.getClient().admin().indices().analyze(analyzeRequest);
        System.out.println(analyzeResponseActionFuture.actionGet().getTokens());
    }
}
