package com.example.demo.myConfigurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "myCustom")
public class MyApplicationProperties {

    private String stockservice;
    private String data;
    private double fraction;
    private List<URL> urls;
    private MyTestProperties testProperties;

    public String getStockservice() {
        return stockservice;
    }

    public void setStockservice(String stockservice) {
        this.stockservice = stockservice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getFraction() {
        return fraction;
    }

    public void setFraction(double fraction) {
        this.fraction = fraction;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public MyTestProperties getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(MyTestProperties testProperties) {
        this.testProperties = testProperties;
    }

    @Override
    public String toString() {
        return "MyApplicationProperties{" +
                "stockservice='" + stockservice + '\'' +
                ", data='" + data + '\'' +
                ", fraction=" + fraction +
                ", urls=" + urls +
                ", testProperties=" + testProperties +
                '}';
    }

    // NESTED PROPS
    public static class MyTestProperties {
        private String otherData;

        public String getOtherData() {
            return otherData;
        }

        public void setOtherData(String otherData) {
            this.otherData = otherData;
        }

        @Override
        public String toString() {
            return "MyTestProperties{" +
                    "otherData='" + otherData + '\'' +
                    '}';
        }
    }
}
