package org.alexkozlov.test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.Currency;
import java.util.List;

@Component
public class DataReceiverCSV implements  DataReceiver {
    @Override
    public void readFrom(Reader file, List<OutputData> orders) {
        try {
            Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(file);
            int lineNum = 1;
            for( CSVRecord rec : csvRecords) {
               long count = rec.size();
               OutputData order = new OutputData();
               order.line = lineNum++;
               try {
                   for (int i = 0; i < count; ++i) {
                   //System.out.print(rec.get(i) + " ");
                       switch (i) {
                           case 0:
                               order.id = Integer.parseInt(rec.get(i));
                               break;
                           case 1:
                               order.amount = Double.parseDouble(rec.get(i));
                               break;
                           case 2:
                               order.currency = Currency.getInstance(rec.get(i));
                               break;
                           case 3:
                               order.comment = rec.get(i);
                               break;
                       }
                   }
                   order.result = "OK";
               }

               catch(NullPointerException e) {
                   order.result = "Invalid currency code";
                   order.result = "Error";
               }
               catch( NumberFormatException e) {
                   order.result = "Not a number in the id or amount fields";
                   order.result = "Error";
               }
               catch(IllegalArgumentException e) {
                   order.result = "Invalid currency code";
                   order.result = "Error";
               }
               //System.out.println();
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
