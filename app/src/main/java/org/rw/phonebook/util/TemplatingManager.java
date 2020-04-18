package org.rw.phonebook.util;

import freemarker.template.Configuration;


public class TemplatingManager {
    private static Configuration freemarkerConfig;

    private TemplatingManager() {
    }

    public static synchronized Configuration getFreemarkerConfig() {
        if (freemarkerConfig == null) {
            freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
            freemarkerConfig.setClassForTemplateLoading(Utilities.class, "/");
            freemarkerConfig.setDefaultEncoding("UTF-8");
        }
        return freemarkerConfig;
    }
}
