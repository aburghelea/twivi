grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://xuggle.googlecode.com/svn/trunk/repo/share/java/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
        runtime 'org.apache.httpcomponents:httpclient:4.2'
        runtime 'commons-httpclient:commons-httpclient:3.1'
        runtime 'org.codehaus.jackson:jackson-mapper-lgpl:1.9.7'
        runtime('xuggle:xuggle-xuggler:5.4') {
            excludes('logback-core')
            excludes('logback-classic')
            excludes('logback-parent')
        }
        runtime('xuggle:xuggle-utils:1.22') {
            excludes('logback-core')
            excludes('logback-classic')
            excludes('logback-parent')
        }

        /* runtime ('ch.qos.logback:logback-core:1.0.0'){
            excludes('slf4j-api')
        }
        runtime ('ch.qos.logback:logback-classic:1.0.0'){
            excludes('slf4j-api')
        }*/

    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"
    }

    grails.plugin.location."burning-image"= "inline-plugins/grails-burning-image"
}
