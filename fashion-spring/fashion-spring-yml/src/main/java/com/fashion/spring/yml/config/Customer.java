package com.fashion.spring.yml.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**

 * Created by xyp on 2019/4/16.
 */
public class Customer implements Serializable{

    private static final long serialVersionUID = 6615995153439440798L;
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String appId;
    @Getter
    @Setter
    private String publicKey;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Long xfdCompanyId;
}
