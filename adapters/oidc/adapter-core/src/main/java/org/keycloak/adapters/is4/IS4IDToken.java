/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.keycloak.representations.IDToken;

public class IS4IDToken extends IDToken {

	@JsonIgnore
	private String tokenString;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
}
