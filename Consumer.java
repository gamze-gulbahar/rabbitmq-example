import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Consumer {
    private static final String EXCHANGE_NAME = "linklogs";

    public static String message = "";

    static void getTitle(String url) throws IOException {
        Document document = loadDocumentFromURL(url);
        String title = document.title();
        System.out.println(title);
    }

    static Document loadDocumentFromURL(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("rmq");
        factory.setPassword("rmq");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            message = new String(delivery.getBody(), "UTF-8");
            getTitle(message);
            //System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume("", true, deliverCallback, consumerTag -> { });

    }
}


