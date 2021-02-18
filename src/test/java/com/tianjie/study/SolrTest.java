package com.tianjie.study;

import com.tianjie.study.y2020.solr.User;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

//@SpringBootTest
public class SolrTest {

    @Autowired
    private SolrClient solrClient;

//    @Test
    public void test1() throws IOException, SolrServerException {
        User user = new User();
        user.setId("12");
        solrClient.addBean(user);
        solrClient.commit();

    }

//    @Test
    public void test2() throws IOException, SolrServerException {
        int i = 0;
        i=i++;
        System.out.println(i);


    }


}
