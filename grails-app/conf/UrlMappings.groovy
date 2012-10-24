class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }
        "/comandante"(view: '/pub/comandante')
        "/gac"(view: '/pub/programmatore')
        "/formazione"(view: '/pub/formazione')
        "/"(view: "/index")
        "500"(view: '/error')
    }

}
