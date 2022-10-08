package com.eragapati.pub.pubsubpub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboxMessage {
    private String id;
    private String title;
    private String body;
    private String metadata;
}
