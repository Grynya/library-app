package com.onlineapp.libraryapp.config;

import com.onlineapp.libraryapp.model.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@PropertySource("classpath:application.yml")
public class ResultConfig {

    private Map<String, String>addElementResultSuccessAdded=new HashMap<>();

    private Map<String, String> addElementResultNotAdded=new HashMap<>();

    private Map<String, String> removeElementResultSuccessRemoved=new HashMap<>();

    private Map<String, String> removeElementResultNotRemoved=new HashMap<>();

    private Map<String, String> modifyElementResultSuccessModified=new HashMap<>();

    private Map<String, String> modifyElementResultNotModified=new HashMap<>();

    private Map<String, String> hasReferences = new HashMap<>();


    @Autowired
    private Environment env;

    public Result result(){
        addElemFromProperties(addElementResultSuccessAdded, "add_element_result_success_added");
        addElemFromProperties(addElementResultNotAdded, "add_element_result_not_added");
        addElemFromProperties(removeElementResultSuccessRemoved, "remove_element_result_success_removed");
        addElemFromProperties(removeElementResultNotRemoved, "remove_element_result_not_removed");
        addElemFromProperties(modifyElementResultSuccessModified, "modify_element_result_success_modified");
        addElemFromProperties(modifyElementResultNotModified, "modify_element_result_not_modified");
        addElemFromProperties(hasReferences, "has_references");

        return new Result(addElementResultSuccessAdded, addElementResultNotAdded,
                removeElementResultSuccessRemoved, removeElementResultNotRemoved,
                modifyElementResultSuccessModified, modifyElementResultNotModified, hasReferences);
    }
    private void addElemFromProperties(Map<String, String> map, String mapName){
        map.put("eng", env.getProperty(mapName+".eng"));
        map.put("rus", env.getProperty(mapName+".rus"));
        map.put("ukr", env.getProperty(mapName+".ukr"));

    }


}
