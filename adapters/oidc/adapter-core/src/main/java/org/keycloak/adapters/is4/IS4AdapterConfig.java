/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.keycloak.representations.adapters.config.AdapterConfig;

public class IS4AdapterConfig extends AdapterConfig {
	@JsonProperty("extra-scopes")
	protected String extraScopes;

	public String getExtraScopes() {
		return extraScopes;
	}

	public void setExtraScopes(String extraScopes) {
		this.extraScopes = extraScopes;
	}
}
