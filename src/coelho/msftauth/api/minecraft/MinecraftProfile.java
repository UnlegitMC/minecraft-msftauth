package coelho.msftauth.api.minecraft;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class MinecraftProfile {

	@SerializedName("id")
	private String uuid;
	private String name;
	private List<MinecraftProfileSkin> skins;

	public MinecraftProfile(String uuid, String name, List<MinecraftProfileSkin> skins) {
		this.uuid = uuid;
		this.name = name;
		this.skins = skins;
	}

	public String getUuid() {
		return this.uuid;
	}

	public String getName() {
		return this.name;
	}

	public List<MinecraftProfileSkin> getSkins() {
		return Collections.unmodifiableList(this.skins);
	}

}
