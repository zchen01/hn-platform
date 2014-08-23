package hn.travel.cms.generic.ajax;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.VisibilityChecker.Std;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

/**
 * A custom Jackson ObjectMapper that installs JSON serialization/deserialization support
 * for properties annotated with Spring format annotations such as @DateTimeFormat and @NumberFormat.
 */
public class ConversionServiceAwareObjectMapper extends ObjectMapper {

	@Autowired
	public ConversionServiceAwareObjectMapper(ConversionService conversionService) {
		AnnotationIntrospector introspector = AnnotationIntrospector.pair(new FormatAnnotationIntrospector(conversionService), DEFAULT_ANNOTATION_INTROSPECTOR);
		SerializationConfig sconfig = new SerializationConfig(DEFAULT_INTROSPECTOR, introspector, Std.defaultInstance());
		DeserializationConfig dconfig = new DeserializationConfig(DEFAULT_INTROSPECTOR, introspector, Std.defaultInstance());
		setSerializationConfig(sconfig);
		setDeserializationConfig(dconfig);
	}
}
