package com.tianjie.study.y2020.solr;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@Data
public class User implements Serializable {

    @Field("id")
    private String id;
}
