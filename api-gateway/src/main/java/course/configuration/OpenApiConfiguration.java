package course.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

	/*
	 * @Bean public RouteLocator gatewayRouter(RouteLocatorBuilder builder) { return
	 * builder.routes() .route(p -> p.path("/get") .filters(f ->
	 * f.addRequestHeader("Hello", "World").addRequestParameter("Hello", "World"))
	 * .uri("http://httpbin.org:80")) .route(p ->
	 * p.path("/cambio-service/**").uri("lb://cambio-service")) .route(p ->
	 * p.path("/book-service/**").uri("lb://book-service")).build(); }
	 */

	@Bean

	@Lazy(false)
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters config, RouteDefinitionLocator locator) {

		var definitions = locator.getRouteDefinitions().collectList().block();

		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
				.forEach(routeDefinition -> {
					String name = routeDefinition.getId();
					config.addGroup(name);
					GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
				});
		return new ArrayList<>();
	}
}
