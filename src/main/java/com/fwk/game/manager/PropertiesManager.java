package com.fwk.game.manager;

import java.io.IOException;
import java.util.Properties;

/**
 * 枚举单例（java作者推荐的最完美的方式之一）
 * 能保证线程安全，并且反序列化后依旧能保持单例
 */
public enum PropertiesManager {
    PROPERTIES(new Properties());

    private Properties properties;

    PropertiesManager(Properties properties){
        try {
            properties.load(PropertiesManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties=properties;
    }

    public int getInt(String key){
        return Integer.valueOf(this.properties.getProperty(key));
    }

    public String getString(String key){
        return this.properties.getProperty(key);
    }
}
