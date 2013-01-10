package twivi.twitter



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TwitterStreamService)
class TwitterStreamServiceTests {

    void testSomething() {
        TwitterStreamService service = new TwitterStreamService()
        service.retrieveStream("Keyword")
    }
}
