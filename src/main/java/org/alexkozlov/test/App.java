package org.alexkozlov.test;

import org.alexkozlov.test.configuration.OrderConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(OrderConfiguration.class);
        ReseiveAndConvertData converter = context.getBean("reseiveAndConvertDataBean", ReseiveAndConvertData.class);
        converter.setConverter( new DataConverterJSON());

        ExecutorService execService = Executors.newFixedThreadPool(args.length);

        Future<String>[] result =  new Future[args.length];

        for( int i = 0; i <  args.length ; ++i ) {
            String arg = args[i];
            result[i] = execService.submit(( ) -> {
                return converter.addData(arg);
            });
        }

        boolean stop = false;
        while( !stop ) {
            stop = true;
            for( Future res : result) {
                if( !res.isDone() ) {
                    stop = false;
                }
            }
        }

        for( Future res : result) {
            try {
                System.out.println( res.get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        execService.shutdown();

        context.close();
    }
}
