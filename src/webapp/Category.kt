package dk.w4.webapp

import dk.w4.Repository.CategoryRepository
import dk.w4.model.Category as CategoryEntity
import dk.w4.redirect
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.route

const val CATEGORY = "/category"
const val CATEGORY_SHOW = "/category/{id}"

@Location(CATEGORY)
class Categories

@Location(CATEGORY_SHOW)
class Category(val id: Int)

fun Route.categories(categoryDB: CategoryRepository) {
       get<Category> { input ->
        val category = categoryDB.getById(input.id)
        call.respond(FreeMarkerContent("category.ftl", mapOf("category" to category)))
    }

    post<Categories> {
        val params = call.receiveParameters()
        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")
        when (action) {
            "add" -> {
                val name = params["name"] ?: throw IllegalArgumentException("Missing parameter: Name")
                categoryDB.add(CategoryEntity(0, name))
            }
            "delete" -> {
            }
        }
        call.redirect(GameSetup())
    }
}