package com.springboot.st.config.oauth2.provider;

import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo{

    Map<String, Object> attributes;
    Map<String, Object> properties;
    Map<String,Object>  account;


    public KakaoOAuth2UserInfo(Map<String, Object> attributes, Map<String, Object> properties, Map<String, Object> account) {
        this.attributes = attributes;
        this.properties = properties;
        this.account = account;
    }

    @Override
    public String getProviderId() {
        return  String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String)account.get("email");
    }

    @Override
    public String getName() {
        return (String)properties.get("nickname");
    }
}
