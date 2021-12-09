/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

public interface IS4TokenPostProcessor {
	void process(IS4AccessToken accessToken, IS4IDToken idToken);
}
