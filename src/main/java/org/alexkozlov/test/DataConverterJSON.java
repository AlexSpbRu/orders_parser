package org.alexkozlov.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataConverterJSON implements  DataConverter{
    @Override
    public String convertData(List<OutputData> data)  {
        ObjectMapper mapper = new ObjectMapper();

            String str = data.stream().map( order -> {
                try {
                    return mapper.writeValueAsString(order);
                } catch (JsonProcessingException e) {
                    return "";
                }
            }).collect(Collectors.joining("\n"));
            return str;
    }
}
