package com.edw.configuration;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.jboss.marshalling.commons.GenericJBossMarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  com.edw.configuration.InfinispanConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 12 Sep 2024 18:59
 */
@Configuration
public class InfinispanConfiguration {
    @Bean
    public RemoteCacheManager remoteCacheManager() {
        return new RemoteCacheManager(
                new ConfigurationBuilder()
                        .addServers("epf-datagrid-poc.datagrid.svc.cluster.local:11222")
                        .security().authentication().username("developer").password("tYxtberWYosXk9aY")
                        .clientIntelligence(ClientIntelligence.HASH_DISTRIBUTION_AWARE)
                        .marshaller(new GenericJBossMarshaller())
                        .addJavaSerialWhiteList(".*")
                        .build());
    }
}
