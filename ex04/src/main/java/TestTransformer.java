import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class TestTransformer extends ResponseDefinitionTransformer {

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        return new ResponseDefinitionBuilder()
                .withStatus(200)
                .withBody(formResponseBody())
                .build();
    }

    private String formResponseBody(){
        return "Hello!";
    }

    @Override
    public String getName() {
        return "testTransformer";
    }

    @Override
    public boolean applyGlobally(){
        return false;
    }
}
