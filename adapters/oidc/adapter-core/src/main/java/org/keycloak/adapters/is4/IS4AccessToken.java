/*
 * Author : AdNovum Informatik AG
 */

package org.keycloak.adapters.is4;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.keycloak.representations.AccessToken;

public class IS4AccessToken extends AccessToken {
	// IS4: shadow the scope field since IS4 returns an array, not a string
	@JsonProperty("scope")
	private Set<String> is4Scope;

	@JsonIgnore
	private String tokenString;

	public Set<String> getIs4Scope() {
		return is4Scope;
	}

	public void setIs4Scope(Set<String> is4Scope) {
		this.is4Scope = is4Scope;
		this.scope = join(is4Scope);
	}

	@Override
	@JsonIgnore
	public void setScope(String scope) {
		super.setScope(scope);
		this.is4Scope = new HashSet<>(Arrays.asList(scope.split(" ")));
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	private static String join(final Iterable<String> values) {
		final StringBuilder b = new StringBuilder();
		Iterator<String> iterator = values.iterator();
		while (iterator.hasNext()) {
			final String s = iterator.next();
			if (s != null) {
				b.append(s);
				if (iterator.hasNext()) b.append(' ');
			}
		}
		return b.toString();
	}
}
