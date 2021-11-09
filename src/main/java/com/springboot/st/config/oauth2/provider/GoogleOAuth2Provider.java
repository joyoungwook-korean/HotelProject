package com.springboot.st.config.oauth2.provider;

import java.util.Map;

public class GoogleOAuth2Provider implements OAuth2UserInfo{

    private Map<String,Object> attributes;

    public GoogleOAuth2Provider(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
