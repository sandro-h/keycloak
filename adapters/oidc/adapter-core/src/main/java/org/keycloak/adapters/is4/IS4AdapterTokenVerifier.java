/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import org.keycloak.TokenVerifier;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.rotation.AdapterTokenVerifier;
import org.keycloak.adapters.rotation.AdapterTokenVerifier.VerifiedTokens;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;

public class IS4AdapterTokenVerifier {

	public static VerifiedTokens verifyTokens(String accessTokenString, String idTokenString,
			KeycloakDeployment deployment) throws VerificationException {
		// Adapters currently do most of the checks including signature etc on the access token
		TokenVerifier<IS4AccessToken> tokenVerifier = AdapterTokenVerifier.createVerifier(accessTokenString, deployment, false,
				IS4AccessToken.class);
		// IS4: Exclude bearer token type check since no "typ" field
		tokenVerifier.withChecks(
				TokenVerifier.SUBJECT_EXISTS_CHECK,
				TokenVerifier.IS_ACTIVE
		);
		AccessToken accessToken = tokenVerifier.verify().getToken();

		if (idTokenString != null) {
			// Don't verify signature again on IDToken
			IDToken idToken = TokenVerifier.create(idTokenString, IDToken.class).getToken();
			TokenVerifier<IDToken> idTokenVerifier = TokenVerifier.createWithoutSignature(idToken);

			// Always verify audience on IDToken
			idTokenVerifier.audience(deployment.getResourceName());
			// IS4: Do not verify azp since not set

			idTokenVerifier.verify();
			return new VerifiedTokens(accessToken, idToken);
		} else {
			return new VerifiedTokens(accessToken, null);
		}
	}
}
