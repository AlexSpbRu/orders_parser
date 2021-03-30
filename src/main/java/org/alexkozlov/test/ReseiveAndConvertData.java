package org.alexkozlov.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("reseiveAndConvertDataBean")
public class ReseiveAndConvertData {

    protected DataConverter   converter;
    @Autowired
    protected DataReceiverCreator dataReceiverCreator;

    public ReseiveAndConvertData() {}

    public void setConverter(DataConverter converter) {
        this.converter = converter;
    }

    public String addData(String fileName) {

        List<OutputData>  orders  = new ArrayList<>();
        try {
            Reader file = new FileReader(fileName);
            DataReceiver reseiver = dataReceiverCreator.getReceiver(fileName);

            if( reseiver != null) {
                reseiver.readFrom(file, orders);
                orders.stream().map( order -> {
                    if(order.filename == null )
                        order.filename = fileName;
                    return order;
                } ).collect(Collectors.toList());
            }

            file.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return converter.convertData(orders);
    }
 }
