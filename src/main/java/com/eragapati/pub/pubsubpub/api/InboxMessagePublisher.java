package com.eragapati.pub.pubsubpub.api;

import com.eragapati.pub.pubsubpub.model.InboxMessage;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.pubsub.v1.PushConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.eragapati.pub.pubsubpub.util.Constant.INBOX_TOPIC;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class InboxMessagePublisher {

    private final PubSubTemplate pubSubTemplate;
    private final TopicAdminClient topicAdminClient;

    private final SubscriptionAdminClient subscriptionAdminClient;

    @PostMapping(path = "/api/inbox/publish")
    public String inboxMsgPublisher(@RequestBody InboxMessage msg) throws ExecutionException, InterruptedException {
        Map<String, String> headers = Collections.singletonMap("key1", "val1");
        pubSubTemplate.publish(INBOX_TOPIC, msg, headers).get();
        return "Success";
    }


    @PostMapping(path = "/setup")
    public String createTopic(InboxMessage msg) throws ExecutionException, InterruptedException {
        topicAdminClient.createTopic("projects/ravi-project-id/topics/" + INBOX_TOPIC);
        subscriptionAdminClient.createSubscription(
                "projects/ravi-project-id/subscriptions/ravi-sub",
                "projects/ravi-project-id/topics/" + INBOX_TOPIC,
                PushConfig.newBuilder().build(),
                10);
        return "Success";
    }

}
