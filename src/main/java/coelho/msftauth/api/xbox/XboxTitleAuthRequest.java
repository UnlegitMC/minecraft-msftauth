package coelho.msftauth.api.xbox;

import coelho.msftauth.api.APIEncoding;
import coelho.msftauth.api.APIRequest;
import com.google.gson.annotations.SerializedName;
import org.apache.http.HttpRequest;

public class XboxTitleAuthRequest extends APIRequest<XboxTitleToken> {

    private static class Properties {
        @SerializedName("AuthMethod")
        public String authMethod;
        @SerializedName("DeviceToken")
        public String deviceToken;
        @SerializedName("SiteName")
        public String siteName;
        @SerializedName("RpsTicket")
        public String rpsTicket;
    }

    @SerializedName("RelyingParty")
    private String relyingParty;
    @SerializedName("TokenType")
    private String tokenType;
    @SerializedName("Properties")
    private XboxTitleAuthRequest.Properties properties = new XboxTitleAuthRequest.Properties();
    private transient XboxDeviceKey deviceKey;

    public XboxTitleAuthRequest(String relyingParty, String tokenType, String authMethod, String siteName, String rpsTicket, XboxToken deviceToken, XboxDeviceKey key) {
        this(relyingParty, tokenType, authMethod, siteName, rpsTicket, deviceToken.getToken(), key);
    }

    public XboxTitleAuthRequest(String relyingParty, String tokenType, String authMethod, String siteName, String rpsTicket, String deviceToken, XboxDeviceKey key) {
        this.relyingParty = relyingParty;
        this.tokenType = tokenType;
        this.properties.authMethod = authMethod;
        this.properties.siteName = siteName;
        this.properties.rpsTicket = rpsTicket;
        this.properties.deviceToken = deviceToken;
        this.deviceKey = key;
    }

    @Override
    public void applyHeader(HttpRequest request) {
        request.setHeader("x-xbl-contract-version", "1");
        this.deviceKey.sign(request);
    }

    @Override
    public String getHttpURL() {
        return "https://title.auth.xboxlive.com/title/authenticate";
    }

    @Override
    public APIEncoding getRequestEncoding() {
        return APIEncoding.JSON;
    }

    @Override
    public APIEncoding getResponseEncoding() {
        return APIEncoding.JSON;
    }

    @Override
    public Class<XboxTitleToken> getResponseClass() {
        return XboxTitleToken.class;
    }
}
