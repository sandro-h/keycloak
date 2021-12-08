/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import java.util.Set;

import org.jboss.logging.Logger;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.keycloak.constants.ServiceUrlConstants;
import org.keycloak.protocol.oidc.representations.OIDCConfigurationRepresentation;

public class IS4KeycloakDeployment extends KeycloakDeployment {

	private static final Logger log = Logger.getLogger(IS4KeycloakDeployment.class);

	protected String extraScopes;

	public String getExtraScopes() {
		return extraScopes;
	}

	public void setExtraScopes(String extraScopes) {
		this.extraScopes = extraScopes;
	}

	@Override
	protected void resolveUrls() {
		if (realmInfoUrl == null) {
			synchronized (this) {
				if (realmInfoUrl == null) {
					KeycloakUriBuilder authUrlBuilder = KeycloakUriBuilder
							.fromUri(authServerBaseUrl);

					// IS4: no /realm path
					String discoveryUrl = authUrlBuilder.clone()
							.path("/.well-known/openid-configuration").build().toString();
					try {
						log.debugv("Resolving URLs from {0}", discoveryUrl);

						OIDCConfigurationRepresentation config = getOidcConfiguration(discoveryUrl);

						authUrl = KeycloakUriBuilder.fromUri(config.getAuthorizationEndpoint());
						realmInfoUrl = config.getIssuer();

						tokenUrl = config.getTokenEndpoint();
						logoutUrl = KeycloakUriBuilder.fromUri(config.getLogoutEndpoint());
						accountUrl = KeycloakUriBuilder.fromUri(config.getIssuer()).path("/account")
								.build().toString();
						registerNodeUrl = authUrlBuilder.clone()
								.path(ServiceUrlConstants.CLIENTS_MANAGEMENT_REGISTER_NODE_PATH)
								.build(getRealm()).toString();
						unregisterNodeUrl = authUrlBuilder.clone()
								.path(ServiceUrlConstants.CLIENTS_MANAGEMENT_UNREGISTER_NODE_PATH)
								.build(getRealm()).toString();
						jwksUrl = config.getJwksUri();
						log.infov("Loaded URLs from {0}", discoveryUrl);
					} catch (Exception e) {
						log.warnv(e, "Failed to load URLs from {0}", discoveryUrl);
					}
				}
			}
		}
	}
}
