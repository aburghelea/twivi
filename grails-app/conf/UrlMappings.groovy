class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/testing")
        "/twivi/testing"(view: "/testing")
		"500"(view:'/error')
	}
}
