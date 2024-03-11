# AntraHw-JwtFilterRestTemplate
## Spring Security Filter and Jwt
1. Build a customized Jwt authentication filter
2. Insert the Jwt filter before the UsernamePasswordAuthenticationFilter on the Spring Security filter chain
3. Authenticate initial login with POST api/v1/login (username and password in requestBody) and return a Jwt token
4. The authentication of /login username and password is invoked in the controller
5. Authenticate all following access to GET api/v1/** with valid Jwt token on the filter chain
6. Create and configure one UserDetail and store it in InMemoryUserDetailsManager.
## RestTempalte Refactor
1. Use UniversityDto instead of String as the response body.
2. Inject an asyncTask Component to the SearchByCountryService to make @Async work properly.
3. Store all resource or secret strings in resources/application.properties.