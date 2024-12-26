package ma.zyn.app.config.security.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RestTemplateSslConfig {

//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        char[] keystorePassword = "zynkey".toCharArray();
//
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        keyStore.load(new FileSystemResource("keystore.p12").getInputStream(), keystorePassword);
//
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadKeyMaterial(keyStore, keystorePassword)
//                .loadTrustMaterial(keyStore, (chain, authType) -> true)
//                .build();
//
//        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
//                .setSSLSocketFactory(
//                        SSLConnectionSocketFactoryBuilder.create()
//                                .setSslContext(sslContext)
//                                .build()
//                )
//                .build();
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//
//        return new RestTemplate(requestFactory);
//        //return new RestTemplate();
//    }

}


