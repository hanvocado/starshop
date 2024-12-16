package com.starshop.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "shop.jsp")
				.addDecoratorPath("/admin/*", "admin.jsp")
				.addDecoratorPath("/customer/account/*", "account.jsp")
				.addDecoratorPath("/shipper/*", "shipper.jsp")
				// Exclude few paths from decoration.
				.addExcludedPath("/error")
				.addExcludedPath("/chat")
				.addExcludedPath("/auth/login")
				.addExcludedPath("/auth/register")
				.addExcludedPath("/auth/forgot-password");
	}
}