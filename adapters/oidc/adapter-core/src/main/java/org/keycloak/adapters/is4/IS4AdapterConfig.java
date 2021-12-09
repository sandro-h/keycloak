/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.keycloak.representations.adapters.config.AdapterConfig;

public class IS4AdapterConfig extends AdapterConfig {
	@JsonProperty("extra-scopes")
	protected String extraScopes;

	@JsonProperty("token-post-processor")
	private String tokenPostProcessor;

	public String getExtraScopes() {
		return extraScopes;
	}

	public void setExtraScopes(String extraScopes) {
		this.extraScopes = extraScopes;
	}

	public String getTokenPostProcessor() {
		return tokenPostProcessor;
	}

	public void setTokenPostProcessor(String tokenPostProcessor) {
		this.tokenPostProcessor = tokenPostProcessor;
	}
}
