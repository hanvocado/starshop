package com.starshop.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// Assigning default decorator if no path specific decorator found
		builder.addDecoratorPath("/*", "shop.jsp")
				// Map decorators to specific path patterns.
				.addDecoratorPath("/admin/*", "admin.jsp")
				// Exclude few paths from decoration.
				.addExcludedPath("/javadoc/*").addExcludedPath("/brochures/*")
				.addExcludedPath("/auth/login")
				.addExcludedPath("/auth/register");
	}
}