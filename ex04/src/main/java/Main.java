import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {

    public static void main(String[] args) {

        WireMockServer wireMockServer = new WireMockServer(options()
                        .port(8000)
                        .extensions(new TestTransformer()));

        wireMockServer.stubFor(
                post("/mock").willReturn(aResponse().withTransformers("testTransformer")));

        wireMockServer.stubFor(
                get("/ololo").willReturn(ok().withBody("ololo"))
        );

        wireMockServer.start();

    }
}
