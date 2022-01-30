package coelho.msftauth;

import coelho.msftauth.api.xbox.*;

import java.util.Collections;

public class MsftAuth {

	private static final String MS_ACCESSTOKEN = "";

	public static void main(String[] args) throws Exception {
		// test tokens that will be used in Minecraft:PE login
		XboxDeviceKey key = new XboxDeviceKey();

		XboxToken userToken = new XboxUserAuthRequest("http://auth.xboxlive.com", "JWT", "RPS",
				"user.auth.xboxlive.com", "t="+MS_ACCESSTOKEN).request();
		System.out.println("userToken: "+userToken.getToken());

		XboxDeviceToken deviceToken = new XboxDeviceAuthRequest("http://auth.xboxlive.com", "JWT", "Nintendo",
				"0.0.0.0", key).request();
		System.out.println("deviceToken: "+deviceToken.getToken());

		XboxToken titleToken = new XboxTitleAuthRequest("http://auth.xboxlive.com", "JWT", "RPS",
				"user.auth.xboxlive.com", "t="+MS_ACCESSTOKEN, deviceToken.getToken(), key).request();
		System.out.println("titleToken: "+titleToken.getToken());

		XboxToken xstsToken = new XboxXSTSAuthRequest("https://multiplayer.minecraft.net/", "JWT", "RETAIL",
				Collections.singletonList(userToken), titleToken, new XboxDevice(key, deviceToken)).request();
		System.out.println("xstsToken: "+xstsToken.getToken());

		System.out.println("identityToken: "+xstsToken.toIdentityToken());
	}
}
