package org.alexkozlov.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dataReceiverCreatorBean")
public class DataReceiverCreator {
    public DataReceiverCreator() {}
    @Autowired
    DataReceiverCSV     dataReceiverCSV;
    @Autowired
    DataReceiverJSON    dataReceiverJSON;

    public DataReceiver getReceiver( String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
        switch( ext ) {
            case "csv" :
            case "txt" :
                return  dataReceiverCSV;
            case "json" :
                return  dataReceiverJSON;
        }
        return null;
    }
}
