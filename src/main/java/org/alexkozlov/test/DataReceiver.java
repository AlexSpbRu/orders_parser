package org.alexkozlov.test;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.Reader;
import java.util.List;

public interface DataReceiver  {
    public void readFrom(Reader file, List<OutputData>  orders);
}
