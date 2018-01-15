//package com.example.demo.config;
//
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
///**
// * Modifies enhancer to add more fields to the JwtToken
// */
//public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//
//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//        Map<String, Object> info = new LinkedHashMap<String, Object>(
//                accessToken.getAdditionalInformation());
//
//        info.put("anyInfoHere", "customObjects here like    user^");
//
//        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
//        customAccessToken.setAdditionalInformation(info);
//
//        return super.enhance(customAccessToken, authentication);
//    }
//
//
//
//    @Override
//    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
//        OAuth2Authentication o = super.extractAuthentication(map);
//        o.setDetails("3333333333333333333333333");
//        return o;
//    }
//}
