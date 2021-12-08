/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import java.io.FileInputStream;
import java.io.IOException;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;

public class IS4KeycloakConfigResolver implements KeycloakConfigResolver {
	@Override
	public KeycloakDeployment resolve(org.keycloak.adapters.spi.HttpFacade.Request request) {
		try (FileInputStream fis = new FileInputStream(System.getProperty("is4.keycloak.config"))) {
			return IS4KeycloakDeploymentBuilder.build(fis);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
