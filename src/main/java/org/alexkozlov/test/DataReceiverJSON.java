package org.alexkozlov.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class DataReceiverJSON implements DataReceiver {
    static protected  int line = 1;

    static protected OutputData convert(String str) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = new Order();
        OutputData outData = new OutputData();
        outData.result = "OK";
        outData.line = line++;
        try {
            order = mapper.readValue(str, Order.class);
            outData.assign(order);
        } catch (IOException e) {
            e.printStackTrace();
            outData.result = "Convertion error";
        }
        return outData;
     }
    //Function<String, OutputData> converter = DataReceiverJSON::convert;
    @Override
    public void readFrom(Reader file, List<OutputData> orders) {
        BufferedReader buffReader = new BufferedReader(file);

        List<OutputData> ordersData =   buffReader.lines()
                .map(DataReceiverJSON::convert)
                .collect(Collectors.toList());
        orders.addAll(ordersData);

    }
}
