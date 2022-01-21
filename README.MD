<h3>Running the service</h3>

1. create mongodb container :
```
docker run -d --name mongo-db -e MONGO_INITDB_ROOT_USERNAME=mariel -e MONGO_INITDB_ROOT_PASSWORD=secret -p 27017:27017 mongo
```

2. Login to the mongo db with the root user you specified in th docker command : 
```
$ ./mongosh.exe -u mariel
Enter password: secret
******
Current Mongosh Log ID: 61e2a1b6adc9581b516aa963
Connecting to:          mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000
Using MongoDB:          5.0.5
Using Mongosh:          1.1.8

For mongosh info see: https://docs.mongodb.com/mongodb-shell/

------
   The server generated these startup warnings when booting:
   2022-01-14T22:28:51.499+00:00: Using the XFS filesystem is strongly recommended with the WiredTiger storage engine. See http://dochub.mongodb.org/core/prodnotes-filesystem
   2022-01-14T22:28:52.590+00:00: /sys/kernel/mm/transparent_hugepage/enabled is 'always'. We suggest setting it to 'never'
------

test>
```

3. Create user for app : 
```
mongo> use test
switched to db test

test> db.createUser({user:"test-user", pwd:"secret", roles: [ {role:"readWrite", db: "test"}]})
{ ok: 1 }
```

<h3>Tips</h3>
1. Check what version of spring you are using. The new versions of spring include JUnit v5.
2. Check what verison of junits you are using (4/5) : 
```
// JUnit 4
import org.junit.Test;

// JUnit Jupiter (part of JUnit 5)
import org.junit.jupiter.api.Test;
```

<h3>Test Annotations</h3>
1. `@SpringBootTest` - This annotation is used to load complete application context for end to end integration testing. As part of the background magic, the annotation will scan and <b>start ALL beans </b> in our main spring boot classes.
2. `@RunWith(SpringRunner.class)`/`@RunWith(SpringJUnit4ClassRunner.class)` - `SpringRunner` is only available on spring-test 4.3 and it basically a new name for `SpringJUnit4ClassRunner`. You need this annotation to just enable spring boot features like `@Autowire`, `@MockBean` etc.. during junit testing. From some spring version, the @ExtendWidth(SpringExtension.class) replace these annotations. The big idea here is to add to Junit(v5) some of Spring power (e.g Autowired) without loading all the spring context. You should use this annotation when you really need spring, like when you InjectMocks and Mock annotations.
3. `@TestConfiguration` - It allows us to define additional beans or override existing beans in the Spring application context to add specialized configurations for testing. A common use case is to create specific beans for tests instead of the beans in the real main code (e.g beans that mock db clients).
4. `@Import({SomeConfig.class})` - Any beans configured in a top-level class annotated with `@TestConfiguration` will not be picked up via component scanning. So you have to explicitly register your @TestConfiguration class. You can do that either via `@Import(MyTestConfiguration.class)` or `@ContextConfiguration(classes = MyTestConfiguration.class)` on your test class.
5. `@Mock`/`@InjectMock` - `@Mock` creates a mock. `@InjectMocks` creates an instance of the class and injects the mocks that are created with the `@Mock` (or `@Spy`) annotations into this instance. Please notice that in JUnit4 `@RunWith(MockitoJUnitRunner.class)` or `Mockito.initMocks(this)` are required to initialize the mocks and inject them. In JUnit 5 the `@ExtendWidth` annotation is required for it.
6. `@Profile("!test")` - If you want to make some beans unavailable for testing, you can just add a profile on top of the bean creation (class or method). Remember to make sure that a bean of that type available for testing.
With JUnit 5, you must use @ExtendWith(MockitoExtension.class)
