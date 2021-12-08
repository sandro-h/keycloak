/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.keycloak.util.SystemPropertiesJsonParserFactory;

public class IS4KeycloakDeploymentBuilder extends KeycloakDeploymentBuilder {

	protected IS4KeycloakDeploymentBuilder() {
		deployment = new IS4KeycloakDeployment();
	}

	public static KeycloakDeployment build(InputStream is) {
		IS4AdapterConfig adapterConfig = loadAdapterConfig(is);
		IS4KeycloakDeployment builtDeployment =
				(IS4KeycloakDeployment) new IS4KeycloakDeploymentBuilder().internalBuild(adapterConfig);
		builtDeployment.setExtraScopes(adapterConfig.getExtraScopes());
		return builtDeployment;
	}

	public static IS4AdapterConfig loadAdapterConfig(InputStream is) {
		ObjectMapper mapper = new ObjectMapper(new SystemPropertiesJsonParserFactory());
		mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		IS4AdapterConfig adapterConfig;
		try {
			adapterConfig = mapper.readValue(is, IS4AdapterConfig.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return adapterConfig;
	}
}
